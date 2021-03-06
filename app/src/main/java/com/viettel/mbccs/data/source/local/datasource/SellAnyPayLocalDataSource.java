package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.ISellAnyPayLocalDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SellAnyPayLocalDataSource implements ISellAnyPayLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static SellAnyPayLocalDataSource instance;

    public SellAnyPayLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    @Override
    public List<KeyValue> getCustTypes() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item = new KeyValue("0", "Nạp khách hàng lẻ");
        result.add(item);

        item = new KeyValue("1", "Kênh phân phối");
        result.add(item);

        return result;
    }

    @Override
    public List<KeyValue> getPayMethods() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item = new KeyValue("1", "Tiền mặt");
        result.add(item);

        item = new KeyValue("10", "Ví điện tử");
        result.add(item);

        item = new KeyValue("2", "Bank Plus");
        result.add(item);

        return result;
    }

    @Override
    public List<KeyValue> getDefaultAmounts() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue(String.valueOf(i+1*1000), (i+1*1000) + "$");

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getBankPlusAmounts() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue(String.valueOf((i+1) * 10000), ((i+1) * 10000) + "$");

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getBranches() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("BRANCH_" + i+1, "Branch " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getManagers() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("STAFF_" + i+1, "Staff " + i+1);

            result.add(item);
        }

        return result;
    }

    @Override
    public List<KeyValue> getChannels() {
        List<KeyValue> result = new ArrayList<>();
        KeyValue item;

        for(int i = 0; i < 10; i++){

            item = new KeyValue("CHANNEL_" + i+1, "Channel " + i+1);

            result.add(item);
        }

        return result;
    }

    public static SellAnyPayLocalDataSource getInstance() {
        if (instance == null) {
            instance = new SellAnyPayLocalDataSource();
        }
        return instance;
    }


}
