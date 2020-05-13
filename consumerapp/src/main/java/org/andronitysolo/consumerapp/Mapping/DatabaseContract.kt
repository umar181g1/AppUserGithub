package org.andronitysolo.consumerapp.SQLITE.Mapping

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "org.andronitysolo.consumerapp"
    const val SCHEME = "content"
    internal class GithubColumns : BaseColumns{
        companion object{
            const val TABLE_NAME = "github"
            const val _ID = "_id"
            const val TiTLE = "title"
            const val FOLLOWER = "follower"
            const val FOLLOWING = "following"


            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}