package br.edu.ifce.crazyquiz.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Question(
        val id: Int,
        val text: String,
        val options: ArrayList<QuestionOption>) : Parcelable

