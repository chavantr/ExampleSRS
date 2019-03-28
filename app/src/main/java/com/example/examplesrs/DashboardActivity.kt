package com.example.examplesrs

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
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

    private var tsc = ArrayList<School>()

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

        txtSearch.addTextChangedListener(filterSchool)

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

    private val filterSchool = object : TextWatcher {

        override fun afterTextChanged(input: Editable?) {

            if (input.toString().isNotEmpty()) {

                tsc.clear()

                for (i in UserInfoHolder.getInstance().schools.indices) {
                    if (UserInfoHolder.getInstance().schools[i].name.contains(input.toString(), true)) {
                        tsc.add(UserInfoHolder.getInstance().schools[i])
                    }
                }

                schoolAdapter = SchoolAdapter(tsc)
                schoolAdapter.setOnItemListener(this@DashboardActivity)
                lstSchools.adapter = schoolAdapter

            } else {
                schoolAdapter = SchoolAdapter(UserInfoHolder.getInstance().schools)
                schoolAdapter.setOnItemListener(this@DashboardActivity)
                lstSchools.adapter = schoolAdapter
            }

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profle -> {
                val intent = Intent(this@DashboardActivity, ProfileActivity::class.java)
                startActivity(intent)
                drawer_layout.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_change_pass -> {
                val intent = Intent(this@DashboardActivity, ForgotPasswordActivity::class.java)
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
            schoolAdapter.setOnItemListener(this)
            lstSchools.adapter = schoolAdapter
        } else {
            Toast.makeText(this@DashboardActivity, "Error occurred", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                if (UserInfoHolder.getInstance().schools.isNotEmpty()) {
                    var lst = ArrayList<School>()
                    for (i in UserInfoHolder.getInstance().schools.indices) {
                        if (checkFalse(i)) {
                            lst.add(UserInfoHolder.getInstance().schools[i])
                        } else if (checkTrue(i)) {
                            if (checkFee(i)) {
                                lst.add(UserInfoHolder.getInstance().schools[i])
                            }
                        }
                    }
                    schoolAdapter = SchoolAdapter(lst)
                    schoolAdapter.setOnItemListener(this@DashboardActivity)
                    lstSchools.adapter = schoolAdapter
                }
            }
        }
    }

    private fun checkTrue(i: Int): Boolean {
        return UserInfoHolder.getInstance().schools[i].daycaredata.equals(
            "yes",
            false
        ) || UserInfoHolder.getInstance().schools[i].staff.equals(
            "yes",
            false
        ) || UserInfoHolder.getInstance().schools[i].security.equals(
            "yes",
            false
        ) || UserInfoHolder.getInstance().schools[i].rtedata.equals(
            "yes",
            false
        ) || UserInfoHolder.getInstance().schools[i].daycaredata.equals("yes", false)
    }

    private fun checkFalse(i: Int): Boolean {
        return UserInfoHolder.getInstance().schools[i].daycaredata.equals(
            "no",
            false
        ) && UserInfoHolder.getInstance().schools[i].staff.equals(
            "no",
            false
        ) && UserInfoHolder.getInstance().schools[i].security.equals(
            "no",
            false
        ) && UserInfoHolder.getInstance().schools[i].rtedata.equals(
            "no",
            false
        ) && UserInfoHolder.getInstance().schools[i].daycaredata.equals("no", false)
    }

    private fun checkFee(i: Int) =
        UserInfoHolder.getInstance().schools[i].fee.toInt() <= UserInfoHolder.getInstance().searchCriteria.fee


    override fun onSchoolSelected(item: School) {
        UserInfoHolder.getInstance().school = item
        val intent = Intent(this@DashboardActivity, SchoolDetailsActivity::class.java)
        startActivity(intent)
    }
}
