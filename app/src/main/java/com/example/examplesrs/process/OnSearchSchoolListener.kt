package com.example.examplesrs.process

import com.example.examplesrs.model.School

interface OnSearchSchoolListener {
    fun onFindSchool(result: List<School>?)
}