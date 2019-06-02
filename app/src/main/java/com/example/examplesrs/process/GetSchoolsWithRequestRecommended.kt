package com.example.examplesrs.process

import android.os.AsyncTask
import com.example.examplesrs.model.School
import org.json.JSONArray
import org.json.JSONObject

class GetSchoolsWithRequestRecommended : AsyncTask<JSONObject, Void, List<School>?>() {

    private lateinit var onFindSchoolWithCriteriaListener: OnFindSchoolWithCriteriaListener

    override fun doInBackground(vararg params: JSONObject?): List<School>? {

        var response: String? =
            HttpConnectionUtil().requestPost(Constants.URL + Constants.FIND_SCHOOLS_WITH_RECOMMENDED, params[0])

        if (response!!.isNullOrEmpty()) return null else {

            var lst = ArrayList<School>()

            var jSchools = JSONArray(response)

            for (i in 0..(jSchools.length() - 1)) {
                val jNode = jSchools.getJSONObject(i)

                var school = School();
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

            return lst

        }


    }

    override fun onPostExecute(result: List<School>?) {
        super.onPostExecute(result)
        onFindSchoolWithCriteriaListener.onSchoolWithCriteria(result)
    }

    fun setOnSchoolListenerWithCriteria(
        onFindSchoolWithCriteriaListener: OnFindSchoolWithCriteriaListener,
        request: JSONObject?
    ) {
        this.onFindSchoolWithCriteriaListener = onFindSchoolWithCriteriaListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }

}