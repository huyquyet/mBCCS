package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.Reason;
import com.viettel.mbccs.data.model.SaleOrders;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetListOrderRequest;
import com.viettel.mbccs.data.source.remote.request.GetListStockModelRequest;
import com.viettel.mbccs.data.source.remote.request.GetOrderInfoRequest;
import com.viettel.mbccs.data.source.remote.request.GetResonRequest;
import com.viettel.mbccs.data.source.remote.response.OrderInfoResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by HuyQuyet on 5/22/17.
 */

public interface IBanHangKhoTaiChinhRemoteDataSource {
    Observable<List<SaleOrders>> searchSellOrders(BaseRequest<GetListOrderRequest> request);

    Observable<List<ChannelInfo>> getListChannelByOwnerTypeId(
            BaseRequest<GetListChannelByOwnerTypeIdRequest> request);

    Observable<OrderInfoResponse> getOrderInfo(BaseRequest<GetOrderInfoRequest> request);

    Observable<List<Reason>> getListReason(BaseRequest<GetResonRequest> request);

    Observable<List<StockTotal>> getListStockModel(BaseRequest<GetListStockModelRequest> request);
}
