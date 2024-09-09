package com.example.a30diasdebienestar.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Sugerencia(
    @StringRes val frase: Int,
    @StringRes val descripcion: Int,
    @DrawableRes val image: Int
)