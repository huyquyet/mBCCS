package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.local.IBranchesLocalDataSource;
import com.viettel.mbccs.data.source.local.datasource.BranchesLocalDataSource;
import com.viettel.mbccs.data.source.remote.IBranchesRemoteDataSource;
import com.viettel.mbccs.data.source.remote.datasource.BranchesRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.CreateDistributedChannelRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.response.CreateDistributedChannelResponse;
import com.viettel.mbccs.data.source.remote.response.GetDistributedChannelResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class BranchesRepository implements IBranchesLocalDataSource, IBranchesRemoteDataSource {

    private volatile static BranchesRepository instance;
    private BranchesLocalDataSource localDataSource;
    private BranchesRemoteDataSource remoteDataSource;

    public BranchesRepository(BranchesLocalDataSource localDataSource,
                              BranchesRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static BranchesRepository getInstance() {
        if (instance == null) {
            instance = new BranchesRepository(BranchesLocalDataSource.getInstance(),
                    BranchesRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public Observable<GetDistributedChannelResponse> getDistributtedChannelInfo(String isdn, String documentId) {
        return remoteDataSource.getDistributtedChannelInfo(isdn, documentId);
    }

    @Override
    public Observable<CreateDistributedChannelResponse> createDistributtedChannel(DataRequest<CreateDistributedChannelRequest> request) {
        return remoteDataSource.createDistributtedChannel(request);
    }

    @Override
    public List<KeyValue> getChannelTypes() {
        return localDataSource.getChannelTypes();
    }

    @Override
    public List<KeyValue> getDocumentTypes() {
        return localDataSource.getDocumentTypes();
    }

    @Override
    public List<KeyValue> getStaffs() {
        return localDataSource.getStaffs();
    }

    @Override
    public List<KeyValue> getBtses() {
        return localDataSource.getBtses();
    }
}
