package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.remote.IUserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DownloadImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetDistrictRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListIdImageRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOwnerCodeRequest;
import com.viettel.mbccs.data.source.remote.request.GetPrecinctRequest;
import com.viettel.mbccs.data.source.remote.request.GetProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.GetUserInfoRequest;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.request.PassResetRequest;
import com.viettel.mbccs.data.source.remote.request.UploadImageRequest;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListIdImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.PassResetResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UploadImageResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRemoteDataSource implements IUserRemoteDataSource {

    public volatile static UserRemoteDataSource instance;

    public UserRemoteDataSource() {

    }

    public static UserRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new UserRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<LoginInfo> login(LoginRequest loginRequest) {
        return RequestHelper.getFakeRequest()
                .login(loginRequest)
                .flatMap(new Func1<LoginInfo, Observable<LoginInfo>>() {
                    @Override
                    public Observable<LoginInfo> call(LoginInfo loginInfo) {
                        // TODO: 23/05/2017 Need error handler
                        if (loginInfo == null) {
                            BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                            baseErrorResponse.setError(Integer.parseInt("000"), "Login failed.");
                            return Observable.error(BaseException.toServerError(baseErrorResponse));
                        } else {
                            return Observable.just(loginInfo);
                        }
                    }
                })
                .compose(SchedulerUtils.<LoginInfo>applyAsyncSchedulers());
    }

    @Override
    public Observable<UserInfo> getUserInfo(DataRequest<GetUserInfoRequest> request) {
        return RequestHelper.getFakeRequest()
                .getUserInfo(request)
                .flatMap(SchedulerUtils.<UserInfo>convertDataFlatMap())
                .compose(SchedulerUtils.<UserInfo>applyAsyncSchedulers());
    }

    @Override
    public Observable<SendCodeChangePassResponse> sendCodeChangePass(String phone) {
        return RequestHelper.getRequest()
                .sendCodeChangePass(phone)
                .flatMap(SchedulerUtils.<SendCodeChangePassResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<SendCodeChangePassResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return RequestHelper.getRequest()
                .getTelecomserviceAndSaleProgram(request)
                .flatMap(SchedulerUtils.<TelecomServiceAndSaleProgramResponse>convertDataFlatMap())
                .compose(
                        SchedulerUtils.<TelecomServiceAndSaleProgramResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetSerialsResponse> getSerial(DataRequest<GetSerialRequest> request) {
        return RequestHelper.getRequest()
                .getSerials(request)
                .flatMap(SchedulerUtils.<GetSerialsResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetSerialsResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetTotalStockResponse> getModelSales(
            DataRequest<GetTotalStockRequest> request) {
        return RequestHelper.getRequest()
                .getModelSales(request)
                .flatMap(SchedulerUtils.<GetTotalStockResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetTotalStockResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetInfoSaleTranResponse> getSaleTransInfo(
            DataRequest<GetInfoSaleTranRequest> request) {
        return RequestHelper.getRequest()
                .getSaleTransInfo(request)
                .flatMap(SchedulerUtils.<GetInfoSaleTranResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetInfoSaleTranResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetInfoSaleTranResponse> createSaleTransRetail(
            DataRequest<GetInfoSaleTranRequest> request) {
        return null;
    }

    @Override
    public Observable<GetProvinceResponse> getProvince(DataRequest<GetProvinceRequest> request) {
        return RequestHelper.getRequest()
                .getProvince(request)
                .flatMap(SchedulerUtils.<GetProvinceResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetProvinceResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetDistrictResponse> getDistrict(DataRequest<GetDistrictRequest> request) {
        return RequestHelper.getRequest()
                .getDistrict(request)
                .flatMap(SchedulerUtils.<GetDistrictResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetDistrictResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetPrecinctResponse> getPrecinct(DataRequest<GetPrecinctRequest> request) {
        return RequestHelper.getRequest()
                .getPrecinct(request)
                .flatMap(SchedulerUtils.<GetPrecinctResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetPrecinctResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<UploadImageResponse> uploadImage(DataRequest<UploadImageRequest> request) {
        return RequestHelper.getRequest()
                .uploadImage(request)
                .flatMap(SchedulerUtils.<UploadImageResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<UploadImageResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListIdImageResponse> getListIdImage(
            DataRequest<GetListIdImageRequest> request) {
        return RequestHelper.getRequest()
                .getListIdImage(request)
                .flatMap(SchedulerUtils.<GetListIdImageResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListIdImageResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<DownloadImageResponse> downloadImage(
            DataRequest<DownloadImageRequest> request) {
        return RequestHelper.getRequest()
                .downloadImage(request)
                .flatMap(SchedulerUtils.<DownloadImageResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<DownloadImageResponse>applySyncSchedulers());
    }

    @Override
    public Observable<EmptyObject> resetPassword(PassResetRequest request) {
        return RequestHelper.getRequest()
                .resetPassword(request)
                .flatMap(SchedulerUtils.<EmptyObject>convertDataFlatMapVTG())
                .compose(SchedulerUtils.<EmptyObject>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListOwneCodeReponse> getListOwnerCode(
            DataRequest<GetListOwnerCodeRequest> dataRequest) {
        return RequestHelper.getRequest()
                .getListOwnerCode(dataRequest)
                .flatMap(SchedulerUtils.<GetListOwneCodeReponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListOwneCodeReponse>applyAsyncSchedulers());
    }
}
