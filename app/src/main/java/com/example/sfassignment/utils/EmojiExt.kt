package com.example.sfassignment.utils

import android.widget.TextView

fun TextView.male() {
    text = String(Character.toChars(0x1F468))
}

fun TextView.female() {
    text = String(Character.toChars(0x1F469))
}

fun TextView.alien() {
    text = String(Character.toChars(0x1F47D))

}
