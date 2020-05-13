package org.andronitysolo.appusergithub.Response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataItem(

    @field:SerializedName("gists_url")
    val gistsUrl: String? = null,

    @field:SerializedName("repos_url")
    val reposUrl: String? = null,

    @field:SerializedName("following_url")
    var followingUrl: String? = null,

    @field:SerializedName("starred_url")
    val starredUrl: String? = null,

    @field:SerializedName("login")
    var login: String? = null,

    @field:SerializedName("followers_url")
    var followersUrl: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,

    @field:SerializedName("score")
    val score: Double? = null,

    @field:SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,

    @field:SerializedName("avatar_url")
    var avatarUrl: String? = null,

    @field:SerializedName("events_url")
    val eventsUrl: String? = null,

    @field:SerializedName("html_url")
    val htmlUrl: String? = null,

    @field:SerializedName("site_admin")
    val siteAdmin: Boolean? = null,

    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @field:SerializedName("node_id")
    val nodeId: String? = null,

    @field:SerializedName("organizations_url")
    val organizationsUrl: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gistsUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(followingUrl)
        parcel.writeString(starredUrl)
        parcel.writeString(login)
        parcel.writeString(followersUrl)
        parcel.writeString(type)
        parcel.writeString(url)
        parcel.writeString(subscriptionsUrl)
        parcel.writeValue(score)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(avatarUrl)
        parcel.writeString(eventsUrl)
        parcel.writeString(htmlUrl)
        parcel.writeValue(siteAdmin)
        parcel.writeValue(id)
        parcel.writeString(gravatarId)
        parcel.writeString(nodeId)
        parcel.writeString(organizationsUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataItem> {
        override fun createFromParcel(parcel: Parcel): DataItem {
            return DataItem(parcel)
        }

        override fun newArray(size: Int): Array<DataItem?> {
            return arrayOfNulls(size)
        }
    }
}