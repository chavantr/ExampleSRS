package com.example.examplesrs.process

import com.example.examplesrs.model.User

interface OnLoginListener {
    fun onLoginSuccess(user: User?)
}