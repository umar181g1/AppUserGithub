package org.andronitysolo.appusergithub.SQLITE.Mapping

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.FOLLOWER
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.FOLLOWING
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.TABLE_NAME
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.TiTLE

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DataBASE_VERSION)  {
    companion object{
        private const val DATABASE_NAME = "dbgithub"
        private const val DataBASE_VERSION = 1

        private val SQL_CREATE_TABLE_GITHUB = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.GithubColumns._ID} INTEGER PRIMARY KEY," +
                " ${DatabaseContract.GithubColumns.POSTER} TEXT," +
                " ${TiTLE} TEXT," +
                " ${FOLLOWING} TEXT,"+
                " ${FOLLOWER} TEXT)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_GITHUB)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
       onCreate(db)
    }
}