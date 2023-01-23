package hr.valecic.discographyapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import hr.valecic.discographyapp.model.Artist

private const val DB_NAME = "artists.db"
private const val DB_VERSION = 1
private const val TABLE_NAME = "artists"
private val CREATE_TABLE = "create table $TABLE_NAME( " +
        "${Artist::_id.name} integer primary key autoincrement, " +
        "${Artist::name.name} text not null, " +
        "${Artist::match.name} text not null, " +
        "${Artist::streamable.name} integer not null, " +
        "${Artist::favorite.name} integer not null " +
        ")"
private const val DROP_TABLE = "drop table $TABLE_NAME"

class DiscogSqlHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION), DiscogRepository {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    override fun delete(selection: String?, selectionArgs: Array<String>?) =
        writableDatabase.delete(TABLE_NAME, selection, selectionArgs)

    override fun insert(values: ContentValues?) =
        writableDatabase.insert(TABLE_NAME, null, values)

    override fun query(projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor = readableDatabase.query(
        TABLE_NAME,
        projection,
        selection,
        selectionArgs,
        null,
        null,
        sortOrder
    )

    override fun update(values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?
    ) = writableDatabase.update(TABLE_NAME, values, selection, selectionArgs)
}