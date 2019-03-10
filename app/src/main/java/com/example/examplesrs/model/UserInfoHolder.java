package com.example.examplesrs.model;

import java.util.List;

public class UserInfoHolder {

    private List<School> schools;
    private SearchCriteria searchCriteria;
    private PersonalDetails personalDetails;

    public static UserInfoHolder getInstance() {
        return UserInfoHolderHelper.INSTANCE;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public static class UserInfoHolderHelper {

        static UserInfoHolder INSTANCE = new UserInfoHolder();

    }

}
