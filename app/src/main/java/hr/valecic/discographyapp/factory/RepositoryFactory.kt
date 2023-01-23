package hr.valecic.discographyapp.factory

import android.content.Context
import hr.valecic.discographyapp.dao.DiscogSqlHelper

fun getDiscogRepository(context: Context?) = DiscogSqlHelper(context)
