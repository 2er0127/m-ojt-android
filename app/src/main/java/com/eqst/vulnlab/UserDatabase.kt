package com.eqst.vulnlab

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "sqlite_db.db"
        private const val TABLE_USERS = "User"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Ensure the table is created if needed
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_USERS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_USERNAME TEXT NOT NULL,
                $KEY_PASSWORD TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Database upgrade logic if needed
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

    fun getAllUsers(): List<User> {
        val userList: MutableList<User> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_USERS"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val user = User(cursor.getString(0), cursor.getString(1))
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return userList
    }

    fun deleteUser(username: String) {
        val db = this.writableDatabase
        db.delete(TABLE_USERS, "$KEY_USERNAME=?", arrayOf(username))
        db.close()
    }

    fun isUserExists(username: String): Boolean {
        val db = this.readableDatabase
        val cursor: Cursor? = db.query(TABLE_USERS, arrayOf(KEY_USERNAME), "$KEY_USERNAME=?", arrayOf(username), null, null, null, null)
        val exists = cursor != null && cursor.moveToFirst()
        cursor?.close()
        return exists
    }
}
