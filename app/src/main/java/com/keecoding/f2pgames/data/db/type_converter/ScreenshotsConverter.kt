package com.keecoding.f2pgames.data.db.type_converter

import androidx.room.TypeConverter


class ScreenshotsConverter {
    @TypeConverter
    fun stringToArr(str: String): ArrayList<String> {
        val arr = arrayListOf<String>()
        str.split(',').forEach {
            arr.add(it)
        }
        return arr
    }

    @TypeConverter
    fun arrToString(arr: ArrayList<String>): String{
        val strBuilder = StringBuilder()
        arr.forEach {
            strBuilder.append("$it,")
        }
        return strBuilder.toString()
    }
}