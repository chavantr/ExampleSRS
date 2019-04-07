package com.example.examplesrs.model;

import java.util.List;

public class UserInfoHolder {

    private List<School> schools;
    private SearchCriteria searchCriteria;
    private PersonalDetails personalDetails;
    private School school;
    private User user;

    private List<School> recommended;

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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<School> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<School> recommended) {
        this.recommended = recommended;
    }

    public static class UserInfoHolderHelper {
        static UserInfoHolder INSTANCE = new UserInfoHolder();
    }

}
