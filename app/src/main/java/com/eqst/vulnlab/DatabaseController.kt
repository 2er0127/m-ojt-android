package com.eqst.vulnlab

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DatabaseController(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val TAG = "DatabaseController"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "sqlite_db.db"
    }

    private val dbPath: String = context.getDatabasePath(DATABASE_NAME).path
    private val myContext: Context = context

    init {
        try {
            createDatabase()
        } catch (ioe: IOException) {
            Log.e(TAG, "Unable to create database", ioe)
            throw Error("Unable to create database", ioe)
        }
    }

    @Throws(IOException::class)
    fun createDatabase() {
        val dbExist = checkDatabase()

        if (dbExist) {
            val dbFile = File(dbPath)
            if (dbFile.delete()) {
                Log.d(TAG, "Existing database file deleted.")
            } else {
                Log.e(TAG, "Failed to delete existing database file.")
            }
        }

        this.readableDatabase.close()
        try {
            copyDatabase()
        } catch (ioe: IOException) {
            Log.e(TAG, "Error copying database", ioe)
            throw Error("Error copying database", ioe)
        }
    }

    private fun checkDatabase(): Boolean {
        var db: SQLiteDatabase? = null
        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
            Log.d(TAG, "Database opened successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Database does not exist yet.", e)
        }

        db?.close()
        return db != null
    }

    @Throws(IOException::class)
    private fun copyDatabase() {
        Log.d(TAG, "Copying database from assets to $dbPath")
        val inputStream: InputStream = myContext.assets.open(DATABASE_NAME)
        val outputFile = File(dbPath)
        val outputStream: OutputStream = FileOutputStream(outputFile)

        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()

        Log.d(TAG, "Database copied successfully to: $dbPath")
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS User (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL,
                password TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    @Synchronized
    override fun close() {
        super.close()
    }
}
