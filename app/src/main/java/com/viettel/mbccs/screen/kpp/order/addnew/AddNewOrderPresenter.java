package com.viettel.mbccs.screen.kpp.order.addnew;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.SaleTranType;
import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.StockModel;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.BanHangKhoTaiChinhRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CreateOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.utils.ActivityUtils;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.StockTotalCompare;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/22/17.
 */

public class AddNewOrderPresenter implements AddNewOrderContract.Presenter {

    public ObservableField<String> titleOrder;
    public ObservableField<String> amount;
    private Context mContext;
    private AddNewOrderContract.ViewModel mViewModel;
    private StockTotalAdapter mAdapter;
    private CompositeSubscription mCompositeSubscription;
    private ArrayList<StockTotal> mStockTotals = new ArrayList<>();
    private BanHangKhoTaiChinhRepository mBanHangKhoTaiChinhRepository;
    private UserRepository mUserRepository;
    private DataRequest<KPPOrderRequest> mKPPOrderRequestBaseRequest;
    private CompositeSubscription mSubscription;

    public AddNewOrderPresenter(Context context, AddNewOrderContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mCompositeSubscription = new CompositeSubscription();
        mBanHangKhoTaiChinhRepository = BanHangKhoTaiChinhRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscription = new CompositeSubscription();
        init();
        search();
    }

    private void init() {
        titleOrder = new ObservableField<>();
        titleOrder.set(String.format(mContext.getString(R.string.order_from_kpp),
                mUserRepository.getUserInfo().getChannelInfo().getChannelName()));
        amount = new ObservableField<>();
        caculatePrice();
        mAdapter = new StockTotalAdapter(mContext, mStockTotals);
        mAdapter.setStockTotalListener(new StockTotalAdapter.StockTotalListener() {
            @Override
            public void onStockQuantityChange() {
                caculatePrice();
            }
        });
    }

    private void caculatePrice() {
        float totalMoney = 0;
        for (StockTotal stockTotal : mStockTotals) {
            totalMoney += stockTotal.getCountChoice() * stockTotal.getPrice();
        }
        amount.set(String.format(mContext.getString(R.string.kpp_order_label_amount),
                Common.formatDouble(totalMoney)));
    }

    public void search() {
        DataRequest<GetListStockModelRequest> mGetListStockModelRequestBaseRequest =
                new DataRequest<>();
        mGetListStockModelRequestBaseRequest.setWsCode(WsCode.GetListStockModel);
        GetListStockModelRequest request = new GetListStockModelRequest();
        //request.setStockTypeId(StockTotalType.TYPE_NEW);
        //request.setStateId(StockTotalType.TYPE_NEW);
        request.setOwnerType(2L);
        request.setSaleTransType(SaleTranType.SALE_CHANNEL);
        request.setOwnerId(
                Long.valueOf(mUserRepository.getUserInfo().getStaffInfo().getStaffOwnerId()));
        mGetListStockModelRequestBaseRequest.setWsRequest(request);
        mViewModel.showLoading();
        Subscription subscription = mBanHangKhoTaiChinhRepository.getListStockModel(
                mGetListStockModelRequestBaseRequest)
                .subscribe(new MBCCSSubscribe<GetListStockModelResponse>() {
                    @Override
                    public void onSuccess(GetListStockModelResponse object) {
                        if (object != null && object.getStockTotalList() != null) {
                            if (object.getStockTotalList().size() == 0) {
                                DialogUtils.showDialog(mContext, R.string.common_msg_no_data);
                            }
                            mStockTotals.clear();
                            mStockTotals.addAll(object.getStockTotalList());
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                        DialogUtils.showDialog(mContext, R.string.common_msg_no_data,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mViewModel.finishScreen();
                                    }
                                });
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                        //fake();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                        ActivityUtils.hideKeyboard((Activity) mContext);
                    }
                });

        mSubscription.add(subscription);
    }

    public void cancelClick() {
        ((Activity) mContext).finish();
    }

    public void orderClick() {
        if (!isValidate()) {
            return;
        }
        DialogUtils.showDialogStyle(mContext, R.string.confirm, R.string.confirm_kpp_order,
                R.string.order2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        createOrder();
                    }
                }, R.string.common_label_close, null);
    }

    private void createOrder() {
        mViewModel.showLoading();
        mKPPOrderRequestBaseRequest = new DataRequest<>();
        mKPPOrderRequestBaseRequest.setWsCode(WsCode.CreateSaleOrders);
        final KPPOrderRequest request = new KPPOrderRequest();
        request.setStaffId(mUserRepository.getUserInfo().getStaffInfo().getStaffId());
        request.setChannelStaffId(mUserRepository.getUserInfo().getChannelInfo().getChannelId());
        List<StockModel> stockModels = Common.convertStockTotalsToStockModels(mStockTotals);
        List<StockModel> filteredStockModel = new ArrayList<>();
        for (StockModel stockModel : stockModels) {
            if (stockModel.getQuantity() > 0) {
                filteredStockModel.add(stockModel);
            }
        }
        request.setListStockModel(filteredStockModel);
        mKPPOrderRequestBaseRequest.setWsRequest(request);
        Subscription subscription =
                mBanHangKhoTaiChinhRepository.createSaleOrders(mKPPOrderRequestBaseRequest)
                        .subscribe(new MBCCSSubscribe<CreateOrderResponse>() {
                            @Override
                            public void onSuccess(CreateOrderResponse object) {
                                if (object != null) {
                                    mViewModel.gotoSuccessScreen(mStockTotals, object.getOrderId(),
                                            mUserRepository.getUserInfo()
                                                    .getChannelInfo()
                                                    .getManagementName());
                                    return;
                                }

                                onError(new Throwable());
                            }

                            @Override
                            public void onError(BaseException error) {
                                DialogUtils.showDialog(mContext, null, error.getMessage(), null);
                                //mViewModel.gotoSuccessScreen(mStockTotals);
                            }

                            @Override
                            public void onRequestFinish() {
                                super.onRequestFinish();
                                mViewModel.hideLoading();
                            }
                        });
        mCompositeSubscription.add(subscription);
    }

    public void addNewStock() {
        mViewModel.goGoStockPicker(mStockTotals);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }

    public void onCancel() {
        ((Activity) mContext).finish();
    }

    public StockTotalAdapter getAdapter() {
        return mAdapter;
    }

    public void mergeStockTotalList(StockTotal stockTotal) {
        for (int i = 0; i < mStockTotals.size(); i++) {
            if (mStockTotals.get(i).equals(stockTotal)) {
                mStockTotals.get(i).setCountChoice(stockTotal.getCountChoice());
                return;
            }
        }
        mStockTotals.add(stockTotal);
    }

    @Override
    public void pickStockTotalListSuccess(List<StockTotal> stockTotals) {
        for (StockTotal stockTotal : stockTotals) {
            mergeStockTotalList(stockTotal);
        }
        Collections.sort(mStockTotals, new StockTotalCompare());
        mAdapter.notifyDataSetChanged();
        caculatePrice();
        //TODO add list stock total
    }

    public boolean isValidate() {

        int count = 0;
        for (StockTotal item : mStockTotals) {
            if (item.getCountChoice() > 0) {
                count++;
                break;
            }
        }
        if (count == 0) {
            DialogUtils.showDialog(mContext, R.string.no_item_order);
            return false;
        }

        return true;
    }
}
