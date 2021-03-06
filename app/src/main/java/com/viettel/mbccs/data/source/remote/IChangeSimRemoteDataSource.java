package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.source.remote.request.ChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.CheckCalledIsdnsRequest;
import com.viettel.mbccs.data.source.remote.request.CheckDebitChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetRegisterSubRequest;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.GetRegisterSubResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IChangeSimRemoteDataSource {
    Observable<DataResponse> checkDebit(DataRequest<CheckDebitChangeSimRequest> request);

    Observable<DataResponse> checkCalledIsdn(DataRequest<CheckCalledIsdnsRequest> request);

    Observable<DataResponse> changeSim(DataRequest<ChangeSimRequest> request);

    Observable<GetRegisterSubResponse> getRegisterSub(DataRequest<GetRegisterSubRequest> request);
}
