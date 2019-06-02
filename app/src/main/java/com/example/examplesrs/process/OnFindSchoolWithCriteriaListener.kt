package com.example.examplesrs.process

import com.example.examplesrs.model.School

interface OnFindSchoolWithCriteriaListener {
    fun onSchoolWithCriteria(result: List<School>?)
}