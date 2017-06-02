package com.viettel.mbccs.data.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by eo_cuong on 5/30/17.
 */

@Table(name = "District")
public class District extends Model {

    @SerializedName("id")
    @Expose
    @Column(name = "district_id")
    private String districtId;

    @SerializedName("districtName")
    @Expose
    @Column(name = "name")
    private String name;

    @SerializedName("parentId")
    @Expose
    @Column(name = "province_id")
    private String provinceId;

    public District() {
        super();
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
