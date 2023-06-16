package com.examples.mycapstoneproject.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History (
    var name: String,
    var description: String,
    var photo: String
        ):Parcelable