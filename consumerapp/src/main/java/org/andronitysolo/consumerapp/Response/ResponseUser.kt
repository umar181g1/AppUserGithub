package org.andronitysolo.consumerapp.Response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: List<DataItem>
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		TODO("items")
	)

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(totalCount)
		parcel.writeValue(incompleteResults)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<ResponseUser> {
		override fun createFromParcel(parcel: Parcel): ResponseUser {
			return ResponseUser(parcel)
		}

		override fun newArray(size: Int): Array<ResponseUser?> {
			return arrayOfNulls(size)
		}
	}
}


