package com.voidhash.thecatapp.ui.listeners

import com.voidhash.thecatapp.backend.models.Kitty

interface CatClickListener {
    fun onCatClickListener(model: Kitty)
}