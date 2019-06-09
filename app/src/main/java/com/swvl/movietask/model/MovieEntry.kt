package com.swvl.movietask.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.WriteWith

@RealmClass
@Parcelize
open class MovieEntry(
    var title:String = "",
    var year:Int = 0,
    var cast:@WriteWith<StringRealmListParceler> RealmList<String>? = null,
    var genres:@WriteWith<StringRealmListParceler> RealmList<String>? = null,
    var rating:Int = 0
):RealmObject(),Parcelable{

    object StringRealmListParceler: Parceler<RealmList<String>?> {
        override fun create(parcel: Parcel): RealmList<String>? = parcel.readStringRealmList()

        override fun RealmList<String>?.write(parcel: Parcel, flags: Int) {
            parcel.writeStringRealmList(this)
        }
    }
}

private fun Parcel.writeStringRealmList(realmList: RealmList<String>?) {
    writeInt(
        when (realmList) {
            null -> 0
            else -> 1
        }
    )
    if (realmList != null) {
        writeInt(realmList.size)
        for (t in realmList) {
            writeString(t)
        }
    }
}

private fun Parcel.readStringRealmList(): RealmList<String>?  = when {
    readInt() > 0 -> RealmList<String>().also { list ->
        repeat(readInt()) {
            list.add(readString())
        }
    }
    else -> null
}
