package com.example.examplesrs

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.examplesrs.joint.OnSchoolSelectedListener
import com.example.examplesrs.joint.SchoolAdapter
import com.example.examplesrs.model.School
import com.example.examplesrs.model.SearchCriteria
import com.example.examplesrs.model.UserInfoHolder
import com.example.examplesrs.process.FindSchoolAsync
import com.example.examplesrs.process.OnSearchSchoolListener
import com.example.examplesrs.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*
import org.json.JSONObject

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnSearchSchoolListener,
    OnSchoolSelectedListener {


    private lateinit var progressDialogUtil: ProgressDialogUtil
    private lateinit var schoolAdapter: SchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

        btnSearch.setOnClickListener {

            /*  var searchCriteria = SearchCriteria()
              searchCriteria.fee = skFee.progress
              searchCriteria.iboard = spnI.selectedItem.toString()
              searchCriteria.distance = skDistance.progress
              searchCriteria.dayCare = chkDayCare.isChecked
              searchCriteria.rtb = chkRTB.isChecked
              searchCriteria.coaided = spnCoaided.selectedItem.toString()
              searchCriteria.topschool = chktop.isChecked
              searchCriteria.tfacility = chkFacitran.isChecked
              searchCriteria.standard = spnStandarth.selectedItem.toString()
              searchCriteria.qualified = chkQuali.isChecked
              UserInfoHolder.getInstance().searchCriteria = searchCriteria*/

            //val intent = Intent(this@DashboardActivity, ShowSchoolActivity::class.java)
            //startActivity(intent)


        }

        btnFilter.setOnClickListener {
            val intent = Intent(this@DashboardActivity, SearchSchoolResult::class.java)
            startActivityForResult(intent, 1001)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        progressDialogUtil = ProgressDialogUtil(this)
        nav_view.setNavigationItemSelectedListener(this)

        lstSchools.layoutManager = LinearLayoutManager(this)

        init()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val findSchoolAsync = FindSchoolAsync()
        findSchoolAsync.setOnFindSchoolListener(this, JSONObject())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profle -> {
                val intent = Intent(this@DashboardActivity, ProfileActivity::class.java)
                startActivity(intent)
                drawer_layout.closeDrawer(GravityCompat.START)
                return true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFindSchool(result: List<School>?) {
        progressDialogUtil.hide()
        if (result!!.isNotEmpty()) {
            UserInfoHolder.getInstance().schools = result
            schoolAdapter = SchoolAdapter(result)

            lstSchools.adapter = schoolAdapter
            //val intent = Intent(this@DashboardActivity, ShowSchoolActivity::class.java)
            //startActivity(intent)
        } else {
            Toast.makeText(this@DashboardActivity, "Error occurred", Toast.LENGTH_LONG).show()
        }

    }

    override fun onSchoolSelected(item: School) {

    }
}
