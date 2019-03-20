package com.example.examplesrs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examplesrs.model.User
import com.example.examplesrs.process.LoginAsync
import com.example.examplesrs.process.OnLoginListener
import com.example.examplesrs.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), OnLoginListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialogUtil = ProgressDialogUtil(this)
        btnLogin.setOnClickListener {
            if (validate()) {
                //val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                //startActivity(intent)
                init()
            } else {
                Toast.makeText(this@MainActivity, "Enter username and password", Toast.LENGTH_LONG).show()
            }
        }
        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnForgotPassword.setOnClickListener {
            val intent = Intent(this@MainActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val loginAsync = LoginAsync()
        var request = JSONObject()
        val param = JSONObject()
        param.put("Email", txtUserName.text)
        param.put("Password", txtPassword.text)
        param.put("Type", "1")
        request.put("request", param)
        loginAsync.setOnLoginListener(this, request)
    }

    override fun onLoginSuccess(user: User?) {
        progressDialogUtil.hide()
        if (null != user) {
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_LONG).show()
        }
    }

    private fun validate(): Boolean = txtUserName.text!!.isNotEmpty() && txtPassword.text!!.isNotEmpty()
}
