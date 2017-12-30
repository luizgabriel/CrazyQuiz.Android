package br.edu.ifce.crazyquiz.data

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Player(var name: String = "", var scores: Int = 0) : Parcelable