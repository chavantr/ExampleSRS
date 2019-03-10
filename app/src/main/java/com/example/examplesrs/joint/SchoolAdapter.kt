package com.example.examplesrs.joint

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.examplesrs.R
import com.example.examplesrs.model.School
import kotlinx.android.synthetic.main.layout_school_row.view.*

class SchoolAdapter(lst: List<School>?) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    var lstSchools = lst

    private lateinit var onSchoolSelectedListener: OnSchoolSelectedListener



    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): SchoolViewHolder {
        return SchoolViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_school_row, parent, false))
    }

    override fun getItemCount(): Int = lstSchools!!.size

    override fun onBindViewHolder(viewHolder: SchoolViewHolder, position: Int) {
        viewHolder.lblName.text = lstSchools!![position]!!.name

        viewHolder.lblName.setOnClickListener {



        }

    }

    fun setOnItemListener(onSchoolSelectedListener: OnSchoolSelectedListener){
        this.onSchoolSelectedListener = onSchoolSelectedListener
    }


    inner class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lblName = itemView.lblName

    }

}