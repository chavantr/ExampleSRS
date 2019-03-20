package com.example.examplesrs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.examplesrs.process.ForgotPasswordAsync
import com.example.examplesrs.process.OnForgotPasswordListener
import com.example.examplesrs.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_forgot_password.*
import org.json.JSONObject

class ForgotPasswordActivity : AppCompatActivity(), OnForgotPasswordListener {


    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        progressDialogUtil = ProgressDialogUtil(this)

        btnResetPassword.setOnClickListener {
            if (validate()) {
                init()
            } else {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun validate(): Boolean = txtUserName.text!!.isNotEmpty() && txtPassword.text!!.isNotEmpty()

    private fun init() {
        progressDialogUtil.show()
        val forgotPasswordAsync = ForgotPasswordAsync()
        val jRequest = JSONObject()
        val param = JSONObject()
        param.put("Username", txtUserName.text)
        param.put("Password", txtPassword.text)
        jRequest.put("request", param)

        forgotPasswordAsync.setOnForgotPasswordListener(this, jRequest)
    }

    override fun onForgotPasswordSuccess(result: String?) {
        if (!result.isNullOrBlank()) {
            Toast.makeText(this, "Password updated", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG).show()
        }
    }
}
