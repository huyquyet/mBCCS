package com.viettel.mbccs.screen.assigntask.cskpp.create;

import android.content.Context;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.TaskShopManagement;
import com.viettel.mbccs.data.source.CongViecRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.CreateTaskExtendRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetChannelWorkTypeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.CreateTaskExtendResponse;
import com.viettel.mbccs.data.source.remote.response.GetChannelWorkTypeResponse;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Anh Vu Viet on 5/23/2017.
 */

public class CreateCSKPPTaskPresenter implements CreatingCSKPPTaskContract.Presenter {

    private Context mContext;

    private CreatingCSKPPTaskContract.ViewModel mViewModel;

    private CongViecRepository mCongViecRepository;

    private CompositeSubscription mSubscription = new CompositeSubscription();

    private UserRepository mUserRepository;

    public CreateCSKPPTaskPresenter(Context context,
            CreatingCSKPPTaskContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mCongViecRepository = CongViecRepository.getInstance();
        mUserRepository = UserRepository.getInstance();
        mSubscription = new CompositeSubscription();

        mViewModel.showLoading();
        GetChannelWorkTypeRequest request = new GetChannelWorkTypeRequest();

        DataRequest<GetChannelWorkTypeRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(WsCode.GetChannelWorkType);
        dataRequest.setWsRequest(request);

        Subscription subscription = mCongViecRepository.getChannelWorkType(dataRequest)
                .subscribe(new MBCCSSubscribe<GetChannelWorkTypeResponse>() {
                    @Override
                    public void onSuccess(GetChannelWorkTypeResponse object) {
                        mViewModel.hideLoading();
                    }

                    @Override
                    public void onError(BaseException error) {
                        mViewModel.hideLoading();
                        mViewModel.showErrorDialog(error);
                    }
                });
        mSubscription.add(subscription);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public String getToolbarTitle() {
        return mContext.getString(R.string.create_CSKPP_task_presenter_giao_viec_cskpp);
    }

    public void onSelectStaffClick() {
        mViewModel.openStaffPicker();
    }

    public void close() {
        mViewModel.onBackPressed();
    }

    public void assign() {
        mViewModel.showAssignTaskDialog();
    }

    @Override
    public void createTask() {
        long fromDate = mViewModel.getFromDate();
        long toDate = mViewModel.getToDate();
        if (fromDate > toDate) {
            // TODO: 7/2/2017 Show error
            return;
        }

        if (mViewModel.getStaff() == null) {
            // TODO: 10/07/2017 Show error
            return;
        }

        CreateTaskExtendRequest request = new CreateTaskExtendRequest();
        request.setShopId(String.valueOf(mUserRepository.getUserInfo().getShop().getShopId()));
        request.setChannelCode(mUserRepository.getUserInfo().getChannelInfo().getChannelCode());
        request.setFromDate(
                DateUtils.convertDateToString(fromDate, DateUtils.TIMEZONE_FORMAT_SERVER));
        request.setToDate(DateUtils.convertDateToString(toDate, DateUtils.TIMEZONE_FORMAT_SERVER));
        request.setJobDescription("");
        request.setJobName("");
        request.setShopCodeCreate(mUserRepository.getUserInfo().getShop().getShopCode());
        request.setStaffId(String.valueOf(mViewModel.getStaff().getStaffId()));
        request.setType(String.valueOf(TaskShopManagement.TaskType.TYPE_CSKPP));
        request.setUserCreate(mUserRepository.getUserInfo().getStaffInfo().getStaffCode());

        DataRequest<CreateTaskExtendRequest> dataRequest = new DataRequest<>();
        dataRequest.setWsCode(WsCode.CreateTaskExtend);
        dataRequest.setWsRequest(request);

        Subscription subscription = mCongViecRepository.createTaskExtend(dataRequest)
                .subscribe(new MBCCSSubscribe<CreateTaskExtendResponse>() {
                    @Override
                    public void onSuccess(CreateTaskExtendResponse object) {
                        mViewModel.hideLoading();
                        mViewModel.showSuccessDialog();
                    }

                    @Override
                    public void onError(BaseException error) {
                        mViewModel.hideLoading();
                        mViewModel.showErrorDialog(error);
                    }
                });
        mSubscription.add(subscription);
    }
}
