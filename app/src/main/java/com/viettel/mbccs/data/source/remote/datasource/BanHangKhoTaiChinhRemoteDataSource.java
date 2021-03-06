package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.model.EmptyObject;
import com.viettel.mbccs.data.source.remote.IBanHangKhoTaiChinhRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.CreateExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteNoCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockNotNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateExpStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportCmdRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportNoteRequest;
import com.viettel.mbccs.data.source.remote.request.CreateImportStockRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransChannelRequest;
import com.viettel.mbccs.data.source.remote.request.CreateSaleTransFromOrderRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.DestroyStockTransRequest;
import com.viettel.mbccs.data.source.remote.request.GetInfoSaleTranRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListExpCmdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListProvinceRequest;
import com.viettel.mbccs.data.source.remote.request.GetListSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetListShopRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockTransDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetListTTKDRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetReasonRequest;
import com.viettel.mbccs.data.source.remote.request.GetSerialRequest;
import com.viettel.mbccs.data.source.remote.request.GetStockTransSerialDetailRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.request.InputOrderRequest;
import com.viettel.mbccs.data.source.remote.request.KPPOrderRequest;
import com.viettel.mbccs.data.source.remote.request.UpdateSaleOrderRequest;
import com.viettel.mbccs.data.source.remote.request.ViewInforSerialRequest;
import com.viettel.mbccs.data.source.remote.response.BaseCreateCmdNoteResponse;
import com.viettel.mbccs.data.source.remote.response.CreateOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransChannelResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransFromOrderResponse;
import com.viettel.mbccs.data.source.remote.response.CreateSaleTransRetailResponse;
import com.viettel.mbccs.data.source.remote.response.GetInfoSaleTranResponse;
import com.viettel.mbccs.data.source.remote.response.GetListChannelByOwnerTypeIdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListExpCmdResponse;
import com.viettel.mbccs.data.source.remote.response.GetListOrderResponse;
import com.viettel.mbccs.data.source.remote.response.GetListProvinceResponse;
import com.viettel.mbccs.data.source.remote.response.GetListSerialResponse;
import com.viettel.mbccs.data.source.remote.response.GetListShopResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelAllResponse;
import com.viettel.mbccs.data.source.remote.response.GetListStockModelResponse;
import com.viettel.mbccs.data.source.remote.response.GetListTTKDResponse;
import com.viettel.mbccs.data.source.remote.response.GetOrderInfoResponse;
import com.viettel.mbccs.data.source.remote.response.GetReasonResponse;
import com.viettel.mbccs.data.source.remote.response.GetSerialsResponse;
import com.viettel.mbccs.data.source.remote.response.GetStockTransSerialDetailResponse;
import com.viettel.mbccs.data.source.remote.response.GetTotalStockResponse;
import com.viettel.mbccs.data.source.remote.response.InputOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ListStockTransDetailsReponse;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.data.source.remote.response.UpdateSaleOrderResponse;
import com.viettel.mbccs.data.source.remote.response.ViewInforSerialResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public class BanHangKhoTaiChinhRemoteDataSource implements IBanHangKhoTaiChinhRemoteDataSource {

    private volatile static BanHangKhoTaiChinhRemoteDataSource instance;

    private BanHangKhoTaiChinhRemoteDataSource() {
    }

    public static BanHangKhoTaiChinhRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new BanHangKhoTaiChinhRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<GetListOrderResponse> getListOrder(DataRequest<GetListOrderRequest> request) {
        return RequestHelper.getRequest()
                .getListOrder(request)
                .flatMap(SchedulerUtils.<GetListOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListChannelByOwnerTypeIdResponse> getListChannelByOwnerTypeId(
            DataRequest<GetListChannelByOwnerTypeIdRequest> request) {
        return RequestHelper.getRequest()
                .getListChannelByOwnerTypeId(request)
                .flatMap(SchedulerUtils.<GetListChannelByOwnerTypeIdResponse>convertDataFlatMap())
                .compose(
                        SchedulerUtils.<GetListChannelByOwnerTypeIdResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetOrderInfoResponse> getOrderInfo(DataRequest<GetOrderInfoRequest> request) {
        return RequestHelper.getRequest()
                .getOrderInfo(request)
                .flatMap(SchedulerUtils.<GetOrderInfoResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetOrderInfoResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetReasonResponse> getReason(DataRequest<GetReasonRequest> request) {
        return RequestHelper.getRequest()
                .getReason(request)
                .flatMap(SchedulerUtils.<GetReasonResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetReasonResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<UpdateSaleOrderResponse> updateSaleOrder(
            DataRequest<UpdateSaleOrderRequest> request) {
        return RequestHelper.getRequest()
                .updateSaleOrder(request)
                .flatMap(SchedulerUtils.<UpdateSaleOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<UpdateSaleOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CreateSaleTransFromOrderResponse> createSaleTransFromOrder(
            DataRequest<CreateSaleTransFromOrderRequest> request) {
        return RequestHelper.getRequest()
                .createSaleTransFromOrder(request)
                .flatMap(SchedulerUtils.<CreateSaleTransFromOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CreateSaleTransFromOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListStockModelResponse> getListStockModel(
            DataRequest<GetListStockModelRequest> request) {
        return RequestHelper.getRequest()
                .getListStockModel(request)
                .flatMap(SchedulerUtils.<GetListStockModelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListStockModelResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListStockModelAllResponse> getListStockModelAll(
            DataRequest<GetListStockModelRequest> request) {
        return RequestHelper.getRequest()
                .getListStockModelAll(request)
                .flatMap(SchedulerUtils.<GetListStockModelAllResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListStockModelAllResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListSerialResponse> getListSerial(
            DataRequest<GetListSerialRequest> request) {
        return RequestHelper.getRequest()
                .getListSerial(request)
                .flatMap(SchedulerUtils.<GetListSerialResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListSerialResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListProvinceResponse> getListProvince(
            DataRequest<GetListProvinceRequest> request) {
        return RequestHelper.getRequest()
                .getListProvince(request)
                .flatMap(SchedulerUtils.<GetListProvinceResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListProvinceResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListTTKDResponse> getListTTKD(DataRequest<GetListTTKDRequest> request) {
        return RequestHelper.getRequest()
                .getListTTKD(request)
                .flatMap(SchedulerUtils.<GetListTTKDResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListTTKDResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListShopResponse> getListShop(DataRequest<GetListShopRequest> request) {
        return RequestHelper.getRequest()
                .getListShop(request)
                .flatMap(SchedulerUtils.<GetListShopResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListShopResponse>applyAsyncSchedulers());
    }

    public Observable<CreateOrderResponse> createSaleOrders(
            DataRequest<KPPOrderRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .createSaleOrders(requestDataRequest)
                .flatMap(SchedulerUtils.<CreateOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CreateOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<TelecomServiceAndSaleProgramResponse> getTelecomserviceAndSaleProgram(
            DataRequest<GetTelecomServiceAndSaleProgramRequest> request) {
        return RequestHelper.getRequest()
                .getTelecomserviceAndSaleProgram(request)
                .flatMap(SchedulerUtils.<TelecomServiceAndSaleProgramResponse>convertDataFlatMap())
                .compose(
                        SchedulerUtils.<TelecomServiceAndSaleProgramResponse>applyAsyncSchedulers
                                ());
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
    public Observable<CreateSaleTransRetailResponse> createSaleTransRetail(
            DataRequest<GetInfoSaleTranRequest> request) {
        return RequestHelper.getRequest()
                .createSaleTransRetail(request)
                .flatMap(SchedulerUtils.<CreateSaleTransRetailResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CreateSaleTransRetailResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<CreateSaleTransChannelResponse> createSaleTransChannel(
            DataRequest<CreateSaleTransChannelRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .createSaleTransChannel(requestDataRequest)
                .flatMap(SchedulerUtils.<CreateSaleTransChannelResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<CreateSaleTransChannelResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<ListStockTransDetailsReponse> getListStockTransDetail(
            DataRequest<GetListStockTransDetailRequest> request) {
        return RequestHelper.getRequest()
                .getListStockTransDetail(request)
                .flatMap(SchedulerUtils.<ListStockTransDetailsReponse>convertDataFlatMap())
                .compose(SchedulerUtils.<ListStockTransDetailsReponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createExpStock(
            DataRequest<CreateExpStockRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .createExpStock(requestDataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<InputOrderResponse> getListInvoice(
            DataRequest<InputOrderRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .getListInvoice(requestDataRequest)
                .flatMap(SchedulerUtils.<InputOrderResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<InputOrderResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<EmptyObject> importInvoiceList(
            DataRequest<InputOrderRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .importInvoiceList(requestDataRequest)
                .flatMap(SchedulerUtils.<EmptyObject>convertDataFlatMap())
                .compose(SchedulerUtils.<EmptyObject>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createExpStockNotNote(
            DataRequest<CreateExpStockNotNoteRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .createExpStockNotNote(requestDataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createImportStock(
            DataRequest<CreateImportStockRequest> requestDataRequest) {
        return RequestHelper.getRequest()
                .createImportStock(requestDataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetListExpCmdResponse> getListExpCmd(
            DataRequest<GetListExpCmdRequest> dataRequest) {
        return RequestHelper.getRequest()
                .getListExpCmd(dataRequest)
                .flatMap(SchedulerUtils.<GetListExpCmdResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetListExpCmdResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<ViewInforSerialResponse> viewInforSerial(
            DataRequest<ViewInforSerialRequest> dataRequest) {
        return RequestHelper.getRequest()
                .viewInfoSerial(dataRequest)
                .flatMap(SchedulerUtils.<ViewInforSerialResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<ViewInforSerialResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createImportCmd(
            DataRequest<CreateImportCmdRequest> dataRequest) {
        return RequestHelper.getRequest()
                .createImportCmd(dataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createImportNote(
            DataRequest<CreateImportNoteRequest> dataRequest) {
        return RequestHelper.getRequest()
                .createImportNote(dataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createImportNoteNoCMD(
            DataRequest<CreateImportNoteRequest> dataRequest) {
        return RequestHelper.getRequest()
                .createImportNoteNoCMD(dataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<EmptyObject> destroyStockTrans(
            DataRequest<DestroyStockTransRequest> dataRequest) {
        return RequestHelper.getRequest()
                .destroyStockTrans(dataRequest)
                .flatMap(SchedulerUtils.<EmptyObject>convertDataFlatMap())
                .compose(SchedulerUtils.<EmptyObject>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createExpCmd(
            DataRequest<CreateExpCmdRequest> dataRequest) {
        return RequestHelper.getRequest()
                .createExpCmd(dataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createExpNote(
            DataRequest<CreateExpNoteRequest> dataRequest) {
        return RequestHelper.getRequest()
                .createExpNote(dataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseCreateCmdNoteResponse> createExpNoteNoCmd(
            DataRequest<CreateExpNoteNoCmdRequest> dataRequest) {
        return RequestHelper.getRequest()
                .createExpNoteNoCmd(dataRequest)
                .flatMap(SchedulerUtils.<BaseCreateCmdNoteResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<BaseCreateCmdNoteResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<GetStockTransSerialDetailResponse> getStockTransDetailSerial(
            DataRequest<GetStockTransSerialDetailRequest> dataRequest) {
        return RequestHelper.getRequest()
                .getStockTransDetailSerial(dataRequest)
                .flatMap(SchedulerUtils.<GetStockTransSerialDetailResponse>convertDataFlatMap())
                .compose(SchedulerUtils.<GetStockTransSerialDetailResponse>applyAsyncSchedulers());
    }
}
