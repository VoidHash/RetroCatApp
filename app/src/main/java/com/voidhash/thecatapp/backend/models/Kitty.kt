package com.voidhash.thecatapp.backend.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

//model class using classic Parcelable interface
@Parcelize
data class Kitty(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("origin")
    val origin: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("life_span")
    val lifeSpan: String,

    @field:SerializedName("temperament")
    val temperament: String,

    @field:SerializedName("image")
    val image: Image?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable<Image>(Image.CREATOR::class.java.classLoader)
    )

    companion object : Parceler<Kitty> {

        override fun Kitty.write(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(name)
            parcel.writeString(origin)
            parcel.writeString(description)
            parcel.writeString(lifeSpan)
            parcel.writeString(temperament)
            parcel.writeParcelable(image,flags)
        }

        override fun create(parcel: Parcel): Kitty {
            return Kitty(parcel)
        }
    }

}

data class Image(

    @field:SerializedName("width")
    val width: Int,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("height")
    val height: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(width)
        parcel.writeString(id)
        parcel.writeString(url)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }

}
