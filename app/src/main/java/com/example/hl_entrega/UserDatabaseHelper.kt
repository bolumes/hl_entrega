package com.example.hl_entrega

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "entrega_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allUsers"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FULLNAME = "fullname"
        private const val COLUMN_PHONENUMBER = "phonenumber"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_FULLNAME TEXT, $COLUMN_PHONENUMBER INTEGER, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    
    fun InsertionUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, user.id)
            put(COLUMN_FULLNAME, user.fullname)
            put(COLUMN_PHONENUMBER, user.phonenumber)
            put(COLUMN_EMAIL, user.email)
            put(COLUMN_PASSWORD, user.password)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

}