package com.example.examplesrs.model

data class SearchCriteria(
    var fee: Int = 0,
    var iboard: String = "",
    var distance: Int = 0,
    var dayCare: Boolean = false,
    var rtb: Boolean = false,
    var coaided: String = "",
    var topschool: Boolean = false,
    var tfacility: Boolean = false,
    var standard: String = "",
    var qualified: Boolean = false
)