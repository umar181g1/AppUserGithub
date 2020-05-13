package org.andronitysolo.appusergithub.SQLITE.Mapping

import android.database.Cursor
import org.andronitysolo.appusergithub.Response.DataItem

object MappingHelper {
    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<DataItem> {
        val notesList = ArrayList<DataItem>()

        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.GithubColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.TiTLE))
                val following = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.FOLLOWING))
                val followers = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.FOLLOWER))
                notesList.add(DataItem(id.toString(), title, following, followers))
            }
        }
        return notesList
    }

    fun mapCursorToObject(notesCursor: Cursor?): DataItem {
        var note = DataItem()
        notesCursor?.apply {
            moveToFirst()
            val id = getInt(getColumnIndexOrThrow(DatabaseContract.GithubColumns._ID))
            val title = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.TiTLE))
            val following = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.FOLLOWING))
            val followers = getString(getColumnIndexOrThrow(DatabaseContract.GithubColumns.FOLLOWER))
            note = DataItem(id.toString(), title, following, followers)
        }
        return note
    }
}