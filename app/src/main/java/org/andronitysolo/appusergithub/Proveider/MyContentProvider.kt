package org.andronitysolo.appusergithub.Proveider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.AUTHORITY
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.CONTENT_URI
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.TABLE_NAME
import org.andronitysolo.appusergithub.SQLITE.Mapping.GithubjHelper

class MyContentProvider : ContentProvider() {

    companion object{
        private const val GITHUB = 1
        private const val GITHUB_ID = 2
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var githubHelper: GithubjHelper


        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, GITHUB)

            sUriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", GITHUB_ID)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val delete : Int = when(GITHUB_ID){
            sUriMatcher.match(uri) -> githubHelper.deleteById(uri.lastPathSegment.toString())
            else -> 0
        }

        context!!.contentResolver.notifyChange(CONTENT_URI, null)

        return delete
    }

    override fun getType(uri: Uri): String? {
       return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val added : Long= when(GITHUB){
            sUriMatcher.match(uri) -> githubHelper.insert(values)
            else -> 0
        }
        context?.contentResolver?.notifyChange(CONTENT_URI, null)
        return Uri.parse("$CONTENT_URI/$added")
    }

    override fun onCreate(): Boolean {
        githubHelper = GithubjHelper.getInstance(context as Context)
        githubHelper.open()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor:Cursor?
        when(sUriMatcher.match(uri)){
            GITHUB -> cursor = githubHelper.queryAll()
            GITHUB_ID -> cursor = githubHelper.queryByID(uri.lastPathSegment.toString())
            else -> cursor = null
        }
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val updated : Int = when(GITHUB_ID){
            sUriMatcher.match(uri)-> githubHelper.updateData(DataItem())
           else -> 0
        }

        context?.contentResolver?.notifyChange(CONTENT_URI, null)
       return updated
    }
}
