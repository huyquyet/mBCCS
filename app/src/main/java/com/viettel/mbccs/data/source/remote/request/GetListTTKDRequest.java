package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.constance.ShopLevel;

/**
 * Created by HuyQuyet on 5/23/17.
 */

public class GetListTTKDRequest  extends BaseRequest   {
    @Expose
    @SerializedName("shopId")
    private long shopId;

    @Expose
    @SerializedName("shopLevel")
    @ShopLevel
    private long shopLevel;

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getShopLevel() {
        return shopLevel;
    }

    public void setShopLevel(long shopLevel) {
        this.shopLevel = shopLevel;
    }

    public GetListTTKDRequest() {
        super();
    }
}
