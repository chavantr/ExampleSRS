package com.example.examplesrs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.examplesrs.model.UserInfoHolder
import kotlinx.android.synthetic.main.activity_school_details.*

class SchoolDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_details)

        val school = UserInfoHolder.getInstance().school

        lblName.text = "School Name : " + school.name

        lblAddress.text = "Address : " + school.address

        lblSchoolFee.text = "Fee : " + school.fee

        lblSecurity.text = "Security : " + school.security

        lblStaff.text = "Expert Staff : " + school.staff

        lblRTE.text = "RTE : " + school.rtedata

        lblTransport.text = "Transport : " + school.trasport

        lblDaycare.text = "Day care facility : " + school.daycaredata

        lblWebsite.text = "Website : " + school.websitedata

        btnViewMap.setOnClickListener {
            val intent = Intent(this@SchoolDetailsActivity, ShowSchoolActivity::class.java)
            startActivity(intent)
        }

    }
}
