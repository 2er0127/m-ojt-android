package com.eqstbosim.ojtandroidedu

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Seona Lee
// EQST Bosim OJT Android Edu 2024.
class UserDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "User"
        private const val TABLE_USERS = "Users"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY,"
                + KEY_PASSWORD + " TEXT" + ")")
        db.execSQL(CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME, user.username)
        values.put(KEY_PASSWORD, user.password)
        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    fun getUser(username: String): User? {
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(TABLE_USERS, arrayOf(KEY_USERNAME, KEY_PASSWORD), "$KEY_USERNAME=?", arrayOf(username), null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            val user = User(cursor.getString(0), cursor.getString(1))
            cursor.close()
            return user
        }
        return null
    }
}
