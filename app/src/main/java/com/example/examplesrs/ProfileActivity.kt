package com.example.examplesrs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.example.examplesrs.process.OnPersonalDetailsListener
import com.example.examplesrs.process.PersonalDetailsAsync
import com.example.examplesrs.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONObject

class ProfileActivity : AppCompatActivity(), OnPersonalDetailsListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        progressDialogUtil = ProgressDialogUtil(this)
        btnSave.setOnClickListener {
            init()
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val personalDetailsAsync = PersonalDetailsAsync()
        var request = JSONObject()
        var param = JSONObject()
        param.put("Name", txtName.text)
        param.put("Gender", spngender.selectedItem.toString())
        param.put("Age", txtage.text)
        param.put("ChildName", txtChildName.text)
        param.put("ChildGender", spnchildgender.selectedItem.toString())
        param.put("Dob", txtdob.text)
        param.put("ChildAge", txtchildage.text)
        param.put("Income", txtIncome.text)
        param.put("Address", txtaddress.text)
        request.put("request", param)
        personalDetailsAsync.setOnPersonalDetailsListener(this, request)
    }

    override fun onPersonalDetailsSuccess(result: String?) {
        progressDialogUtil.hide()
        if (result!!.isNotEmpty()) {
            var snack = Snackbar.make(btnSave, "Updated", Snackbar.LENGTH_INDEFINITE).setAction("Ok") {
                finish()
            }
            snack.show()
        } else {
            Toast.makeText(this@ProfileActivity, "Error occurred", Toast.LENGTH_LONG).show()
        }
    }

}
