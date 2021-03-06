package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.UserInfo;
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
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.DownloadImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistrictResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListIdImageResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOwneCodeReponse;
import com.viettel.mbccs.data.source.remote.response.GetPrecinctResponse;
import com.viettel.mbccs.data.source.remote.response.GetProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.SendCodeChangePassResponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UploadImageResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserRemoteDataSource {

    Observable<LoginInfo> login(LoginRequest loginRequest);

    Observable<UserInfo> getUserInfo(DataRequest<GetUserInfoRequest> request);

    Observable<SendCodeChangePassResponse> sendCodeChangePass(String phone);

    Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request);

    Observable<GetSerialsResponse> getSerial(DataRequest<GetSerialRequest> request);

    Observable<GetTotalStockResponse> getModelSales(DataRequest<GetTotalStockRequest> request);

    Observable<GetInfoSaleTranResponse> getSaleTransInfo(
            DataRequest<GetInfoSaleTranRequest> request);

    Observable<GetInfoSaleTranResponse> createSaleTransRetail(
            DataRequest<GetInfoSaleTranRequest> request);

    Observable<GetProvinceResponse> getProvince(DataRequest<GetProvinceRequest> request);

    Observable<GetDistrictResponse> getDistrict(DataRequest<GetDistrictRequest> request);

    Observable<GetPrecinctResponse> getPrecinct(DataRequest<GetPrecinctRequest> request);

    Observable<UploadImageResponse> uploadImage(DataRequest<UploadImageRequest> request);

    Observable<GetListIdImageResponse> getListIdImage(DataRequest<GetListIdImageRequest> request);

    Observable<DownloadImageResponse> downloadImage(DataRequest<DownloadImageRequest> request);

    Observable<EmptyObject> resetPassword(PassResetRequest request);

    Observable<GetListOwneCodeReponse> getListOwnerCode(
            DataRequest<GetListOwnerCodeRequest> dataRequest);
}
