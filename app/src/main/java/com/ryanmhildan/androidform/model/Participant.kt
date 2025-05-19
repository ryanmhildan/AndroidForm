package com.ryanmhildan.androidform.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Participant(
    val nim: String,
    val nama: String,
    val semester: String,
    val device: String,
    val osDevice: String,
    val versiOs: String,
    val ram: Int,
    val cpu: String,
    val deployment: String,
    val merkHp: String,
    val osHp: String,
    val ukuranHp: Double,
    val internet: String,
    val install: String,
    val versi: String
) : Parcelable
