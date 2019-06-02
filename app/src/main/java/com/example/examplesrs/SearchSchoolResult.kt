package com.example.examplesrs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.example.examplesrs.model.School
import com.example.examplesrs.model.SearchCriteria
import com.example.examplesrs.model.UserInfoHolder
import com.example.examplesrs.process.GetSchoolsWithRequest
import com.example.examplesrs.process.OnFindSchoolWithCriteriaListener
import com.example.examplesrs.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_search_school_result.*
import org.json.JSONObject

class SearchSchoolResult : AppCompatActivity(), OnFindSchoolWithCriteriaListener {


    private var fee: Int = 0
    private var distance: Int = 0
    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_school_result)


        skDistance.incrementProgressBy(100)
        skDistance.max = 100000

        skDistance.setOnSeekBarChangeListener(distanceListener)
        btnSearch.setOnClickListener {
            var searchCriteria = SearchCriteria()
            searchCriteria.fee = if (txtFees.text!!.isEmpty()) 0 else txtFees.text.toString().toInt()
            searchCriteria.iboard = spnI.selectedItem.toString()
            searchCriteria.distance = skDistance.progress
            searchCriteria.dayCare = chkDayCare.isChecked
            searchCriteria.rtb = chkRTB.isChecked
            searchCriteria.coaided = spnCoaided.selectedItem.toString()
            searchCriteria.topschool = chktop.isChecked
            searchCriteria.tfacility = chkFacitran.isChecked
            searchCriteria.standard = spnStandarth.selectedItem.toString()
            searchCriteria.qualified = chkQuali.isChecked
            searchCriteria.address = txtaddress.text.toString()
            UserInfoHolder.getInstance().searchCriteria = searchCriteria
            progressDialogUtil.show()
            val getSchoolWithRequest = GetSchoolsWithRequest()
            val jRequest = JSONObject()
            val param = JSONObject()
            param.put("IBoard", searchCriteria.iboard)
            param.put("DayCare", check(searchCriteria.dayCare))
            param.put("RTB", check(searchCriteria.rtb))
            param.put("TransportFacility", check(searchCriteria.tfacility))
            param.put("Qualified", check(searchCriteria.qualified))
            param.put("Security", check(searchCriteria.topschool))
            param.put("Location", searchCriteria.address)
            param.put("Fees", if (txtFees.text!!.isEmpty()) 0 else txtFees.text.toString().toInt())
            jRequest.put("request", param)
            getSchoolWithRequest.setOnSchoolListenerWithCriteria(this, jRequest)

            /*var intent = Intent()
            setResult(RESULT_OK)
            finish()*/
        }

        progressDialogUtil = ProgressDialogUtil(this)

    }

    private fun check(value: Boolean): String? {
        return if (value) "yes" else "no"
    }

    private val feeListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            fee = p1

            //lblFee.text = "Fee $fee (Rs)"

        }

        override fun onStartTrackingTouch(p0: SeekBar?) {

        }

        override fun onStopTrackingTouch(p0: SeekBar?) {

        }

    }

    private val distanceListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {


            distance = p1

            lblDistance.text = "Distance ${distance / 1000} (km)"

        }

        override fun onStartTrackingTouch(p0: SeekBar?) {

        }

        override fun onStopTrackingTouch(p0: SeekBar?) {

        }

    }


    override fun onSchoolWithCriteria(result: List<School>?) {
        progressDialogUtil.hide()
        UserInfoHolder.getInstance().schools = result
        var intent = Intent()
        setResult(RESULT_OK)
        finish()
    }

}
