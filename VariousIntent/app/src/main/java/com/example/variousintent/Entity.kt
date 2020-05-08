package com.example.variousintent

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Course(
    val name: String,
    val point: Int,
    val grade: Double
)

data class SerializablePerson(
    val name: String,
    val age: Int,
    val email: String
) : Serializable {
    val nickname = email.substringBefore("@")
}

class ParcelableCourse() : Parcelable {
    var name: String? = null
    var point: Int = 0
    var grade: Double = 0.0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        point = parcel.readInt()
        grade = parcel.readDouble()
    }

    constructor(name: String, point: Int, grade: Double) : this() {
        this.name = name
        this.point = point
        this.grade = grade
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(point)
        parcel.writeDouble(grade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableCourse> {
        override fun createFromParcel(parcel: Parcel): ParcelableCourse {
            return ParcelableCourse(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableCourse?> {
            return arrayOfNulls(size)
        }
    }

}

