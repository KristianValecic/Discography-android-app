package hr.valecic.discographyapp

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.valecic.discographyapp.dao.DiscogRepository
import hr.valecic.discographyapp.dao.DiscogSqlHelper
import hr.valecic.discographyapp.factory.getDiscogRepository
import hr.valecic.discographyapp.model.Artist

private const val AUTHORITY = "hr.valecic.discographyapp.api.provider"
private const val PATH = "artists"
val DISCOG_PROVIDER_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")

private const val ITEMS = 10
private const val ITEM_ID = 20
private val URI_MATHCER = with(UriMatcher(UriMatcher.NO_MATCH)){
    addURI(AUTHORITY, PATH, ITEMS)
    addURI(AUTHORITY, "$PATH/#", ITEM_ID)
    this
}

class DiscogProvider : ContentProvider() {

    private lateinit var discogRepository: DiscogRepository

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when(URI_MATHCER.match(uri)){
            ITEMS -> return discogRepository.delete(selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    return discogRepository.delete("${Artist::_id.name}=?", arrayOf(it))
                }
            }
        }
        throw IllegalAccessException("NO such uri")
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = discogRepository.insert(values)
        return ContentUris.withAppendedId(DISCOG_PROVIDER_CONTENT_URI, id)
    }

    override fun onCreate(): Boolean {
        discogRepository = getDiscogRepository(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = discogRepository.query(
        projection,
        selection,
        selectionArgs,
        sortOrder
    )

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when(URI_MATHCER.match(uri)){
            ITEMS -> return discogRepository.update(values, selection, selectionArgs)
            ITEM_ID -> {
                uri.lastPathSegment?.let {
                    return discogRepository.update(values, "${Artist::_id.name}=?", arrayOf(it))
                }
            }
        }
        throw IllegalAccessException("NO such uri")
    }
}