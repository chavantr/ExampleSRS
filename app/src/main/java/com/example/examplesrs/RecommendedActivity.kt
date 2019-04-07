package com.example.examplesrs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.examplesrs.joint.OnSchoolSelectedListener
import com.example.examplesrs.joint.SchoolAdapter
import com.example.examplesrs.model.School
import com.example.examplesrs.model.UserInfoHolder
import kotlinx.android.synthetic.main.activity_recommended.*


class RecommendedActivity : AppCompatActivity(), OnSchoolSelectedListener {


    private lateinit var schoolAdapter: SchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended)

        lstRecommended.layoutManager = LinearLayoutManager(this)
        schoolAdapter = SchoolAdapter(UserInfoHolder.getInstance().recommended)
        schoolAdapter.setOnItemListener(this@RecommendedActivity)
        lstRecommended.adapter = schoolAdapter
    }

    override fun onSchoolSelected(item: School) {
        UserInfoHolder.getInstance().school = item
        val intent = Intent(this@RecommendedActivity, SchoolDetailsActivity::class.java)
        startActivity(intent)
    }
}
