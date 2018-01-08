package br.edu.ifce.crazyquiz.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Question(
        val id: Int,
        val text: String,
        val difficulty: Int,
        val options: List<QuestionOption>) : Parcelable {

    constructor() : this(0, "", 0, ArrayList())
}

