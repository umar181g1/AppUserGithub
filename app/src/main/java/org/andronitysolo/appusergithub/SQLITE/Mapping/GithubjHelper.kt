package org.andronitysolo.appusergithub.SQLITE.Mapping

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import org.andronitysolo.appusergithub.Response.DataItem
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.FOLLOWER
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.FOLLOWING
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.POSTER
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.TABLE_NAME
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion.TiTLE
import org.andronitysolo.appusergithub.SQLITE.Mapping.DatabaseContract.GithubColumns.Companion._ID

class GithubjHelper(context: Context) {

    private lateinit var database: SQLiteDatabase
    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: GithubjHelper? = null

        fun getInstance(context: Context): GithubjHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GithubjHelper(context)
            }

    }


    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"

        )
    }

    fun queryByID(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)

    }

    fun getAllNotes(): ArrayList<DataItem> {
        val arrayList = ArrayList<DataItem>()
        val cursor = database.query(DATABASE_TABLE, null, null, null, null, null, "$_ID ASC", null)
        cursor.moveToFirst()
        var dataItem: DataItem
        if (cursor.count > 0) {
            do {
                dataItem = DataItem(avatarUrl = String())
                dataItem.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID))
                dataItem.avatarUrl = cursor.getString(cursor.getColumnIndex(POSTER))
                dataItem.login = cursor.getString(cursor.getColumnIndexOrThrow(TiTLE))
                dataItem.followingUrl = cursor.getString(cursor.getColumnIndexOrThrow(FOLLOWING))
                dataItem.followersUrl = cursor.getString(cursor.getColumnIndexOrThrow(FOLLOWER))

                arrayList.add(dataItem)
                cursor.moveToNext()
            } while (!cursor.isAfterLast)
        }
        cursor.close()
        return arrayList
    }

    fun insertData(dataItem: DataItem): Long {
        val args = ContentValues()
        args.put(POSTER, dataItem.avatarUrl)
        args.put(TiTLE, dataItem.login)
        args.put(FOLLOWER, dataItem.followersUrl)
        args.put(FOLLOWING, dataItem.followingUrl)
        return database.insert(DATABASE_TABLE, null, args)
    }

    fun updateData(dataItem: DataItem): Int {
        val args = ContentValues()
        args.put(TiTLE, dataItem.login)
        args.put(FOLLOWING, dataItem.followingUrl)
        args.put(FOLLOWER, dataItem.followersUrl)
        return database.update(DATABASE_TABLE, args, _ID + "='" + dataItem.id + "'", null)
    }

    fun deleteData(id: Int): Int {
        return database.delete(TABLE_NAME, "$_ID = '$id'", null)
    }


}