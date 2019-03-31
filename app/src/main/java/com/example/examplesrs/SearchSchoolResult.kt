package com.example.examplesrs

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.examplesrs.model.SearchCriteria
import com.example.examplesrs.model.UserInfoHolder
import kotlinx.android.synthetic.main.activity_search_school_result.*

class SearchSchoolResult : AppCompatActivity() {


    private var fee: Int = 0
    private var distance: Int = 0

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
            var intent = Intent()
            setResult(RESULT_OK)
            finish()
        }
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

}
