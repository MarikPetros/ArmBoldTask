    package com.marik.armboldtask.model

    import android.os.Parcel
    import android.os.Parcelable

    class Product(
        val itemName: String?, val subcategoryName: String?, val itemImage1: Int, val itemImage2: Int, val price: String?,
        var isFavorite: Boolean = false, val description: Int, val ingredients: Int, val shelfLife: Int, var popularity: Int): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )
        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(itemName)
            parcel.writeString(subcategoryName)
            parcel.writeInt(itemImage1)
            parcel.writeInt(itemImage2)
            parcel.writeString(price)
            parcel.writeByte(if (isFavorite) 1 else 0)
            parcel.writeInt(description)
            parcel.writeInt(ingredients)
            parcel.writeInt(shelfLife)
            parcel.writeInt(popularity)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Product> {
            override fun createFromParcel(parcel: Parcel): Product {
                return Product(parcel)
            }

            override fun newArray(size: Int): Array<Product?> {
                return arrayOfNulls(size)
            }
        }
    }