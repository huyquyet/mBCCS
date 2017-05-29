package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.MBCCSApplication;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BaseRequest<T extends DataRequest> extends DataVTGRequest {
    @Expose
    @SerializedName("username")
    private String userName;

    @Expose
    @SerializedName("wsRequest")
    private T request;

    public BaseRequest() {
        userName = MBCCSApplication.userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
