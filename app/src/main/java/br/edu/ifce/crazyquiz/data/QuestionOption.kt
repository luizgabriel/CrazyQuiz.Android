package br.edu.ifce.crazyquiz.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class QuestionOption(val text: String, val answer: Boolean) : Parcelable {
    constructor() : this("", false)
}