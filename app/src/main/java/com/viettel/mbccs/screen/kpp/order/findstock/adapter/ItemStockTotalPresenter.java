package com.viettel.mbccs.screen.kpp.order.findstock.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.FileUtils;
import java.io.File;

/**
 * Created by eo_cuong on 5/23/17.
 */

public class ItemStockTotalPresenter {

    public ObservableField<Integer> countChoice;

    public ObservableField<Boolean> deleteAble;

    public ObservableField<Boolean> showChooseSerial;

    public StockTotal mStockTotal;

    public Context mContext;

    public ItemStockTotalPresenter(Context context, StockTotal stockTotal) {
        mStockTotal = stockTotal;
        countChoice = new ObservableField<>();
        deleteAble = new ObservableField<>();
        showChooseSerial = new ObservableField<>(true);
        deleteAble.set(false);
        countChoice.set(mStockTotal.getCountChoice());
        mContext = context;
    }

    public void setDeleteAble(boolean deleteAble) {
        this.deleteAble.set(deleteAble);
    }

    public void setShowChooseSerial(boolean showChooseSerial) {
        if (showChooseSerial) {
            this.showChooseSerial.set(showChooseSerial && mStockTotal.getCheckSerial() == 1);
        }else{
            this.showChooseSerial.set(showChooseSerial);
        }

    }

    public void addChoice() {
        mStockTotal.addChoice();
        countChoice.set(mStockTotal.getCountChoice());
    }

    public void subtract() {
        mStockTotal.subtract();
        countChoice.set(mStockTotal.getCountChoice());
    }

    public String getPrice() {
        return String.format(mContext.getString(R.string.kpp_order_label_price),
                Common.formatDouble(mStockTotal.getPrice()));
    }

    public String getStockModelId() {
        return String.format(mContext.getString(R.string.kpp_order_label_stock_id),
                String.valueOf(mStockTotal.getStockModelCode()));
    }

    public String getImage() {

        File file = FileUtils.getImageFileByIdName(mContext,
                String.valueOf(mStockTotal.getStockModelId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public StockTotal getStockTotal() {
        return mStockTotal;
    }

    public void setStockTotal(StockTotal stockTotal) {
        mStockTotal = stockTotal;
    }
}
