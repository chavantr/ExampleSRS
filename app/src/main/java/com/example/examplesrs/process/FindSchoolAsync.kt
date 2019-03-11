package com.example.examplesrs.process

import android.os.AsyncTask
import com.example.examplesrs.model.School
import org.json.JSONArray
import org.json.JSONObject

class FindSchoolAsync : AsyncTask<JSONObject, Void, List<School>?>() {
    private lateinit var onSearchSchoolListener: OnSearchSchoolListener
    override fun doInBackground(vararg params: JSONObject?): List<School>? {
        val response = HttpConnectionUtil().requestGet(Constants.URL + Constants.FIND_SCHOOL)
        return if (response.isNullOrBlank()) null else {
            var lst = ArrayList<School>()
            val jNodes = JSONArray(response)
            for (i in 0..(jNodes.length() - 1)) {
                val jNode = jNodes.getJSONObject(i)
                var school = School()
                school.id = jNode.getInt("Id")
                school.name = jNode.getString("Name")
                school.address = jNode.getString("Address")
                school.lat = jNode.getString("Lat")
                school.lng = jNode.getString("Lng")
                school.address = jNode.getString("Address")
                school.fee = jNode.getString("SchoolFee")
                school.security = jNode.getString("Security")
                school.staff = jNode.getString("Staff")
                school.rtedata = jNode.getString("RTE")
                school.trasport = jNode.getString("Transport")
                school.daycaredata = jNode.getString("Daycare")
                school.websitedata = jNode.getString("Websitedata")
                lst.add(school)
            }
            lst
        }
    }

    override fun onPostExecute(result: List<School>?) {
        super.onPostExecute(result)
        onSearchSchoolListener.onFindSchool(result)
    }

    fun setOnFindSchoolListener(onSearchSchoolListener: OnSearchSchoolListener, request: JSONObject) {
        this.onSearchSchoolListener = onSearchSchoolListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }

}