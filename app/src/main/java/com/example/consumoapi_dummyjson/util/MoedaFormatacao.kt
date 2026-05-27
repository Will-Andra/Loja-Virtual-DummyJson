package com.example.consumoapi_dummyjson.util

import java.text.NumberFormat
import java.util.Locale

class MoedaFormatacao {

    fun formatToBRL(value: Double): String {

        val formatoBR = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return formatoBR.format(value)  //retorna o value formatado

    }
}