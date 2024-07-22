package com.eqstbosim.ojtandroidedu

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DatabaseController(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "sqlite_db.db"
        private const val DATABASE_PATH = "/data/data/com.eqstbosim.ojtandroidedu/databases/"
    }

    private val dbPath = DATABASE_PATH + DATABASE_NAME
    private val myContext: Context = context

    init {
        try {
            createDatabase()
        } catch (ioe: IOException) {
            throw Error("Unable to create database")
        }
    }

    @Throws(IOException::class)
    fun createDatabase() {
        val dbExist = checkDatabase()

        if (!dbExist) {
            this.readableDatabase
            this.close()
            try {
                copyDatabase()
            } catch (ioe: IOException) {
                throw Error("Error copying database")
            }
        }
    }

    private fun checkDatabase(): Boolean {
        var db: SQLiteDatabase? = null
        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: Exception) {
            // Database does not exist yet
        }

        if (db != null) {
            db.close()
        }

        return db != null
    }

    @Throws(IOException::class)
    private fun copyDatabase() {
        val myInput: InputStream = myContext.assets.open(DATABASE_NAME)
        val outFileName = dbPath
        val myOutput: OutputStream = FileOutputStream(outFileName)

        val buffer = ByteArray(1024)
        var length: Int
        while (myInput.read(buffer).also { length = it } > 0) {
            myOutput.write(buffer, 0, length)
        }

        myOutput.flush()
        myOutput.close()
        myInput.close()

        Log.d("DatabaseController", "Database copied successfully")
    }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    @Synchronized
    override fun close() {
        super.close()
    }
}
