package com.example.examplesrs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examplesrs.model.UserInfoHolder
import com.example.examplesrs.process.OnRateListener
import com.example.examplesrs.process.ProgressDialogUtil
import com.example.examplesrs.process.RateAsync
import kotlinx.android.synthetic.main.activity_rate.*
import org.json.JSONObject

class RateActivity : AppCompatActivity(), OnRateListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)

        progressDialogUtil = ProgressDialogUtil(this)

        btnRate.setOnClickListener {
            init()
        }

    }

    private fun init() {
        progressDialogUtil.show()
        val rateAsync = RateAsync()
        val jRequest = JSONObject()
        val param = JSONObject()
        param.put("Rating", rate.rating)
        param.put("SId", intent.extras.getInt("sid", 0))
        param.put("UId", UserInfoHolder.getInstance().user.id)
        jRequest.put("request", param)
        rateAsync.setOnRateListener(this, jRequest)
    }

    override fun onRateSuccess(result: String?) {
        progressDialogUtil.hide()
        Toast.makeText(this, "Rated successfully.", Toast.LENGTH_LONG).show()
        finish()
    }
}
