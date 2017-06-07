package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityCreateOrderSuccessBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListGoodsDetailAdapter;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.widget.CustomDialog;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.viettel.mbccs.utils.DateUtils.CALENDAR_DATE_FORMAT;

/**
 * Created by FRAMGIA\vu.viet.anh on 02/06/2017.
 */

public class CreateOrderSuccessActivity extends
        BaseDataBindActivity<ActivityCreateOrderSuccessBinding, CreateOrderSuccessActivity>
        implements ListGoodsDetailAdapter.OnViewSerialClickListener {

    public static final String SCREEN_TYPE_EXTRA = "SCREEN_TYPE_EXTRA";

    private List<StockTotal> mList = new ArrayList<>();

    public ObservableInt itemCount = new ObservableInt();

    public boolean screenTypeSuccess;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_order_success;
    }

    @Override
    protected void initData() {
        screenTypeSuccess = getIntent().getBooleanExtra(SCREEN_TYPE_EXTRA, false);
        mPresenter = this;
        mBinding.setPresenter(mPresenter);
    }

    public String getToolbarTitle() {
        return screenTypeSuccess ? getString(R.string.activity_create_order_success_thong_bao)
                : getString(R.string.activity_create_order_success_chi_tiet_lenh_nhap);
    }

    public String getOrderCode() {
        return screenTypeSuccess ? getString(
                R.string.activity_create_order_success_lap_phieu_nhap_thanh_cong, "Fake Order")
                : getString(R.string.activity_create_order_success_ma_lenh, "Fake Order");
    }

    public String getImportWarehouseCode() {
        return screenTypeSuccess ? "Import Warehouse" : "";
    }

    public String getExportWarehouseCode() {
        return "Export Warehouse";
    }

    public String getCreatedDate() {
        return screenTypeSuccess ? ""
                : DateUtils.convertToString(new Date(), CALENDAR_DATE_FORMAT, null);
    }

    public RecyclerView.Adapter getAdapter() {
        // TODO: 6/1/2017 Fake data
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        ListGoodsDetailAdapter adapter = new ListGoodsDetailAdapter(this, mList);
        adapter.setOnViewSerialClickListener(this);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemCount.set(mList.size());
            }
        });
        return adapter;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    }

    public void create() {
        new CustomDialog(this, R.string.confirm,
                R.string.activity_create_order_success_ban_co_chac_muon_lap_phieu,
                false, R.string.common_label_close, R.string.activity_create_order_success_lap_phieu, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 5/27/2017 Api call
                        Intent intent = new Intent(CreateOrderSuccessActivity.this,
                                CreateOrderSuccessActivity.class);
                        intent.putExtra(SCREEN_TYPE_EXTRA, true);
                        startActivity(intent);
                        finish();
                    }
                }, null, false).show();
    }

    public void reject() {
        new CustomDialog(this, R.string.activity_create_order_success_tu_choi_lap_phieu,
                R.string.activity_create_order_success_ly_do_tu_choi,
                true, R.string.common_label_close, R.string.activity_create_order_success_tu_choi, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 5/27/2017 Api call
                        finish();
                    }
                }, null, false).setBackgroundAcceptButton(R.color.red_button).show();
    }

    @Override
    public void onViewSerialClickListener(StockTotal item) {
        DialogViewSerial dialog = DialogViewSerial.newInstance(item);  // dialog title
        dialog.show(getSupportFragmentManager(), "");
    }
}
