package com.wl.web.common.model;

import java.util.Date;

public class WlBCountries {
    private Integer countriesId;

    private String countriesName;

    private String countriesName2;

    private String countriesNameEn;

    private String countiresLetter;

    private String countriesImgPath;

    private String telCode;

    private Integer phoneNum;

    private String phoneJs;

    private String phoneTemplate;

    private Integer sort;

    private String remark;

    private Date logTime;

    private String countriesAbbreviation;

    private Integer isShow;

    public Integer getCountriesId() {
        return countriesId;
    }

    public void setCountriesId(Integer countriesId) {
        this.countriesId = countriesId;
    }

    public String getCountriesName() {
        return countriesName;
    }

    public void setCountriesName(String countriesName) {
        this.countriesName = countriesName == null ? null : countriesName.trim();
    }

    public String getCountriesName2() {
        return countriesName2;
    }

    public void setCountriesName2(String countriesName2) {
        this.countriesName2 = countriesName2 == null ? null : countriesName2.trim();
    }

    public String getCountriesNameEn() {
        return countriesNameEn;
    }

    public void setCountriesNameEn(String countriesNameEn) {
        this.countriesNameEn = countriesNameEn == null ? null : countriesNameEn.trim();
    }

    public String getCountiresLetter() {
        return countiresLetter;
    }

    public void setCountiresLetter(String countiresLetter) {
        this.countiresLetter = countiresLetter == null ? null : countiresLetter.trim();
    }

    public String getCountriesImgPath() {
        return countriesImgPath;
    }

    public void setCountriesImgPath(String countriesImgPath) {
        this.countriesImgPath = countriesImgPath == null ? null : countriesImgPath.trim();
    }

    public String getTelCode() {
        return telCode;
    }

    public void setTelCode(String telCode) {
        this.telCode = telCode == null ? null : telCode.trim();
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneJs() {
        return phoneJs;
    }

    public void setPhoneJs(String phoneJs) {
        this.phoneJs = phoneJs == null ? null : phoneJs.trim();
    }

    public String getPhoneTemplate() {
        return phoneTemplate;
    }

    public void setPhoneTemplate(String phoneTemplate) {
        this.phoneTemplate = phoneTemplate == null ? null : phoneTemplate.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getCountriesAbbreviation() {
        return countriesAbbreviation;
    }

    public void setCountriesAbbreviation(String countriesAbbreviation) {
        this.countriesAbbreviation = countriesAbbreviation == null ? null : countriesAbbreviation.trim();
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}