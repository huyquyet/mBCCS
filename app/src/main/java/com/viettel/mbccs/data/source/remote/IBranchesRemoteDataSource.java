package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.source.remote.response.CreateDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistributedChannelResponse;

import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IBranchesRemoteDataSource {

    Observable<GetDistributedChannelResponse> getDistributtedChannelInfo(String isdn, String documentId);

    Observable<CreateDistributedChannelResponse> createDistributtedChannel(BranchItem request);
}
