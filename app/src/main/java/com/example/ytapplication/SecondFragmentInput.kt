package com.example.ytapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SecondFragmentInput(
    val secondFragmentText: String
): Parcelable
