package com.viettel.mbccs.base.listkho;

import android.content.DialogInterface;
import android.databinding.ObservableInt;
import android.support.v4.widget.SwipeRefreshLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityListOrderWarehouseBinding;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.widget.CustomDatePicker;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public abstract class BaseListOrderActivity
        extends BaseDataBindActivity<ActivityListOrderWarehouseBinding, BaseListOrderPresenter>
        implements BaseListOrderContract.ViewModel {

    public abstract void init();

    public abstract String getWareHouseTitle();

    public ObservableInt itemCount;

    MultiDirectionSlidingDrawer mDrawer;
    CustomDatePicker fromDate;
    CustomDatePicker toDate;
    SwipeRefreshLayout swipeLayout;

    public UserRepository mUserRepository;
    public BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_list_order_warehouse;
    }

    public String getFromDateString() {
        return fromDate.getStringDate();
    }

    public String getToDateString() {
        return toDate.getStringDate();
    }

    @Override
    public List<String> getWareHouseData() {
        return mPresenter.getWareHouseData();
    }

    @Override
    public List<String> getStatus() {
        return mPresenter.getStatus();
    }

    @Override
    protected void initData() {
        mUserRepository = UserRepository.getInstance();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mDrawer = mBinding.drawer;
        mDrawer.animateOpen();
        fromDate = mBinding.searchForm.fromDate;
        toDate = mBinding.searchForm.toDate;
        swipeLayout = mBinding.swipeLayout;
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doSearch();
            }
        });
        mPresenter = new BaseListOrderPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        itemCount = mPresenter.itemCount;
        init();
    }

    public CustomDatePicker getFromDate() {
        return fromDate;
    }

    public CustomDatePicker getToDate() {
        return toDate;
    }

    public void showErrorDialog(BaseException e) {
        DialogUtils.showDialogError(this, e);
    }

    public void showErrorDialog(BaseException e, DialogInterface.OnClickListener onClickListener) {
        DialogUtils.showDialogError(this, e, onClickListener);
    }

    @Override
    public int getPositionStatus() {
        return mPresenter.positionStatus;
    }

    public void closeForm() {
        if (mDrawer.isOpened()) {
            mDrawer.animateClose();
        }
        mPresenter.updateTextFilter();
    }

    @Override
    public void onSearchSuccess() {
        mPresenter.onSearchSuccess();
        closeForm();
        swipeLayout.setRefreshing(false);
    }

    @Override
    public int getPositionWareHouser() {
        return mPresenter.positionWareHouse;
    }

    @Override
    public void setDataSearch(List<StockTrans> stockTranses) {
        mPresenter.setDataSearch(stockTranses);
    }

    @Override
    public void onAddClick() {

    }

    @Override
    public void setWareHouseData(List<String> wareHouseData) {
        mPresenter.setWareHouseData(wareHouseData);
    }

    @Override
    public void setStatus(List<String> status) {
        mPresenter.setStatus(status);
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }
}
