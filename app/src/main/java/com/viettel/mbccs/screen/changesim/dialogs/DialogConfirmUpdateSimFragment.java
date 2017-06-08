package com.viettel.mbccs.screen.changesim.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateRegisterSubRequest;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogConfirmUpdateSimFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.tv_trans)
    CustomTextView tvTrans;

    @BindView(R.id.tv_service_fee)
    CustomTextView tvServiceFee;
    @BindView(R.id.tv_sim_fee)
    CustomTextView tvSimFee;
    @BindView(R.id.tv_total)
    CustomTextView tvTotal;

    private ChangeSimRepository changeSimRepository;
    private DataRequest<UpdateRegisterSubRequest> changeSimBaseRequest;
    private Bundle currentArgs;
    private CompositeSubscription mSubscriptions;
    private ChangeSimItem changeSimItem;

    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void initView() {
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        dismiss();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(),
                LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_confirm_update_sim_fragment;
    }

    @Override
    protected void initData() {
        try {
            currentArgs = getArguments();

            if (currentArgs != null) {

                changeSimItem = GsonUtils.String2Object(currentArgs.getString(Constants.BundleConstant.CUSTOMER_ITEM), ChangeSimItem.class);

                if(changeSimItem != null) {
                    tvTrans.setText(getString(R.string.common_msg_confirm_change_sim, changeSimItem.getCustomer().getCustomerName(), changeSimItem.getChangeSimInfo().getOldSerial(), changeSimItem.getChangeSimInfo().getNewSerial()));
                    tvServiceFee.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.SERVICE_FEE)));
                    tvSimFee.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.SIM_FEE)));
                    tvTotal.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TOTAL)));
                }
            }

            changeSimRepository = ChangeSimRepository.getInstance();
            mSubscriptions = new CompositeSubscription();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @OnClick({R.id.biv_close, R.id.biv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biv_close:
                dismiss();
                break;
            case R.id.biv_done:

                sellAnyPay();
                break;
            default:
                break;
        }
    }

    private void sellAnyPay() {
        try {

            showLoadingDialog();

//            if (CreateTransAnyPayPresenter.CUST_TYPE_CORPORATE.equals(currentArgs.getString(Constants.BundleConstant.TRANS_TYPE))) {
//
//                SellAnypayToChannelRequest request = new SellAnypayToChannelRequest();
//
//                request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
//                request.setChannelId(currentArgs.getInt(Constants.BundleConstant.CHANNEL));
//                request.setIsdnVi(currentArgs.getString(Constants.BundleConstant.ISDN_WALLET));
//                request.setPayMethod(currentArgs.getString(Constants.BundleConstant.PAY_METHOD));
//                request.setStaffId(currentArgs.getInt(Constants.BundleConstant.STAFF));
//
//                sellToChannelBaseRequest = new DataRequest<>();
//                sellToChannelBaseRequest.setApiCode(ApiCode.SellAnyPayToChannel);
//                sellToChannelBaseRequest.setParameterApi(request);
//
//                Subscription subscription =
//                        sellAnyPayRepository.sellAnypayToChannel(sellToChannelBaseRequest)
//                                .subscribe(new MBCCSSubscribe<SellAnypayToChannelResponse>() {
//                                    @Override
//                                    public void onSuccess(SellAnypayToChannelResponse object) {
//                                        try {
//                                            if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
//                                                showSuccessDialog();
//                                            } else {
//                                                DialogUtils.showDialogError(getContext(), null, getString(R.string.common_msg_error_general),
//                                                        null);
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                            DialogUtils.showDialogError(getContext(), null, getString(R.string.common_msg_error_general),
//                                                    null);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError(BaseException error) {
//                                        DialogUtils.showDialogError(getContext(), null, error.getMessage(),
//                                                null);
//                                    }
//
//                                    @Override
//                                    public void onRequestFinish() {
//                                        super.onRequestFinish();
//                                        hideLoadingDialog();
//                                    }
//                                });
//
//                mSubscriptions.add(subscription);
//
//            } else {
//                SellAnypayToCustomerRequest request = new SellAnypayToCustomerRequest();
//
//                request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
//                request.setIsdn(currentArgs.getString(Constants.BundleConstant.ISDN));
//                request.setIsdnVi(currentArgs.getString(Constants.BundleConstant.ISDN_WALLET));
//                request.setPayMethod(currentArgs.getString(Constants.BundleConstant.PAY_METHOD));
//                request.setStaffId(currentArgs.getInt(Constants.BundleConstant.STAFF));
//
//                sellToCustomerBaseRequest = new DataRequest<>();
//                sellToCustomerBaseRequest.setApiCode(ApiCode.SellAnyPayToCustomer);
//                sellToCustomerBaseRequest.setParameterApi(request);
//
//                Subscription subscription =
//                        sellAnyPayRepository.sellAnypayToCustomer(sellToCustomerBaseRequest)
//                                .subscribe(new MBCCSSubscribe<SellAnypayToCustomerResponse>() {
//                                    @Override
//                                    public void onSuccess(SellAnypayToCustomerResponse object) {
//                                        try {
//                                            if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
//                                                showSuccessDialog();
//                                            } else {
//                                                DialogUtils.showDialogError(getContext(), null, getString(R.string.common_msg_error_general),
//                                                        null);
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                            DialogUtils.showDialogError(getContext(), null, getString(R.string.common_msg_error_general),
//                                                    null);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError(BaseException error) {
//                                        DialogUtils.showDialogError(getContext(), null, error.getMessage(),
//                                                null);
//                                    }
//
//                                    @Override
//                                    public void onRequestFinish() {
//                                        super.onRequestFinish();
//                                        hideLoadingDialog();
//                                    }
//                                });
//
//                mSubscriptions.add(subscription);
//            }
            showSuccessDialog();//TEST
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSuccessDialog() {
        try {
            DialogUpdatedSimSuccessfulFragment fragment = new DialogUpdatedSimSuccessfulFragment();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.CUSTOMER_ITEM, currentArgs.getString(Constants.BundleConstant.CUSTOMER_ITEM));
            args.putString(Constants.BundleConstant.SERVICE_FEE, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.SERVICE_FEE)));
            args.putString(Constants.BundleConstant.SIM_FEE, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.SIM_FEE)));
            args.putString(Constants.BundleConstant.TOTAL, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TOTAL)));

            getBaseActivity().goToDialogFragment(fragment, args);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
