package com.example.examplesrs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.example.examplesrs.process.OnRegistrationListener
import com.example.examplesrs.process.ProgressDialogUtil
import com.example.examplesrs.process.RegistrationAsync


import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity(), OnRegistrationListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        progressDialogUtil = ProgressDialogUtil(this)
        btnRegister.setOnClickListener {
            if (validate()) {
                init()
            } else {
                Toast.makeText(this@RegistrationActivity, "Enter fields with correct format", Toast.LENGTH_LONG).show()
            }
        }
        btnCancel.setOnClickListener { finish() }
    }

    private fun init() {
        progressDialogUtil.show()
        val registrationAsync = RegistrationAsync()
        var request = JSONObject()
        var param = JSONObject()
        param.put("Name", txtName.text)
        param.put("Email", txtEmail.text)
        param.put("Number", txtPhone.text)
        param.put("Password", txtPassword.text)
        param.put("Type", "1")
        request.put("request", param)
        registrationAsync.setOnRegistrationListener(this, request)
    }

    private fun validate(): Boolean =
        txtPassword.text!!.isNotEmpty() && txtEmail.text!!.isNotEmpty() && txtPhone.text!!.isNotEmpty() && txtPhone.text!!.isNotEmpty() && txtPhone.text!!.toString().length == 10

    override fun onRegistrationComplete(result: String?) {
        progressDialogUtil.hide()
        if (result!!.isNotEmpty()) {
            var snack = Snackbar.make(btnCancel, "Registration complete", Snackbar.LENGTH_INDEFINITE).setAction("Ok") {
                finish()
            }
            snack.show()
        } else {
            Toast.makeText(this@RegistrationActivity, "Error occurred", Toast.LENGTH_LONG).show()
        }
    }
}
