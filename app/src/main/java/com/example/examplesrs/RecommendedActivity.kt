package com.example.examplesrs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.examplesrs.joint.OnSchoolSelectedListener
import com.example.examplesrs.joint.SchoolAdapter
import com.example.examplesrs.model.School
import com.example.examplesrs.model.UserInfoHolder
import com.example.examplesrs.process.GetSchoolsWithRequestRecommended
import com.example.examplesrs.process.OnFindSchoolWithCriteriaListener
import com.example.examplesrs.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_recommended.*

import org.json.JSONObject


class RecommendedActivity : AppCompatActivity(), OnSchoolSelectedListener, OnFindSchoolWithCriteriaListener {


    private lateinit var schoolAdapter: SchoolAdapter
    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended)
        progressDialogUtil = ProgressDialogUtil(this)
        lstRecommended.layoutManager = LinearLayoutManager(this)
        init()
        /* schoolAdapter = SchoolAdapter(UserInfoHolder.getInstance().recommended)
         schoolAdapter.setOnItemListener(this@RecommendedActivity)
         lstRecommended.adapter = schoolAdapter*/
    }

    private fun init() {
        progressDialogUtil.show()
        var searchCriteria = UserInfoHolder.getInstance().searchCriteria
        val getSchoolsWithRequestRecommended = GetSchoolsWithRequestRecommended()
        val jRequest = JSONObject()
        val param = JSONObject()
        param.put("IBoard", searchCriteria.iboard)
        param.put("DayCare", check(searchCriteria.dayCare))
        param.put("RTB", check(searchCriteria.rtb))
        param.put("TransportFacility", check(searchCriteria.tfacility))
        param.put("Qualified", check(searchCriteria.qualified))
        param.put("Security", check(searchCriteria.topschool))
        param.put("Location", searchCriteria.address)
        param.put("Fees", 0)
        jRequest.put("request", param)
        getSchoolsWithRequestRecommended.setOnSchoolListenerWithCriteria(this, jRequest)
    }

    private fun check(value: Boolean): String? {
        return if (value) "yes" else "no"
    }

    override fun onSchoolSelected(item: School) {
        UserInfoHolder.getInstance().school = item
        val intent = Intent(this@RecommendedActivity, SchoolDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onSchoolWithCriteria(result: List<School>?) {
        progressDialogUtil.hide()
        if (null != result && result.isNotEmpty()) {
            UserInfoHolder.getInstance().recommended = result
            schoolAdapter = SchoolAdapter(UserInfoHolder.getInstance().recommended)
            schoolAdapter.setOnItemListener(this@RecommendedActivity)
            lstRecommended.adapter = schoolAdapter
        }
    }
}
