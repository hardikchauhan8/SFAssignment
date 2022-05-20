package com.example.sfassignment.utils

object UnitConverterUtil {
    fun getFeet(height: String?): String? {
        return try {
            val cm = height?.toFloat()
            String.format("%.2f", (cm?.div(30.45)))
        } catch (e: Throwable) {
            height
        }
    }
}
