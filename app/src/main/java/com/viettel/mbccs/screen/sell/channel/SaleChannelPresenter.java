package com.viettel.mbccs.screen.sell.channel;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.model.ChannelInfo;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleChannelInitData;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.data.source.SellOrdersRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.BaseRequest;
import com.viettel.mbccs.data.source.remote.request.GetListChannelByOwnerTypeIdRequest;
import com.viettel.mbccs.data.source.remote.request.GetTelecomServiceAndSaleProgramRequest;
import com.viettel.mbccs.data.source.remote.request.GetTotalStockRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.TelecomServiceAndSaleProgramResponse;
import com.viettel.mbccs.screen.sell.retail.adapter.StockAdapter;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by eo_cuong on 5/20/17.
 */

public class SaleChannelPresenter
        implements SaleChannelContract.Presenter, StockAdapter.OnStockListener {

    public ObservableField<String> filterText;
    public ObservableField<String> sellProgram;
    public ObservableField<String> channelText;
    private ArrayAdapter<TeleComService> mAdapter;
    public ObservableField<Boolean> isCollapse;
    private StockAdapter stockAdapter;
    private Context mContext;
    private SaleChannelContract.ViewModel mViewModel;
    private List<ModelSale> mModelSales = new ArrayList<>();
    private List<SaleProgram> mSalePrograms = new ArrayList<>();
    private List<ChannelInfo> mChannelInfos = new ArrayList<>();
    private List<TeleComService> mTeleComServices = new ArrayList<>();
    private SaleProgram currentSaleProgram = new SaleProgram();
    private TeleComService currentTelecomService = new TeleComService();
    private ChannelInfo currentChannel = new ChannelInfo();
    private int currentStockPosition = -1;
    private UserRepository mUserRepository;
    private SellOrdersRepository mSellOrdersRepository;
    private BaseRequest<GetTelecomServiceAndSaleProgramRequest>
            mGetTelecomServiceAndSaleProgramRequest;
    private BaseRequest<GetListChannelByOwnerTypeIdRequest> mGetListChannelByOwnerTypeIdRequest;
    private BaseRequest<GetTotalStockRequest> mGetTotalStockRequest;
    private CompositeSubscription mSubscription;

    public SaleChannelPresenter(Context context, SaleChannelContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mSubscription = new CompositeSubscription();
        mUserRepository = UserRepository.getInstance();
        mSellOrdersRepository = SellOrdersRepository.getInstance();
        init();
        initialData();
    }

    private void loadModelSale() {
        mViewModel.showLoading();
        mGetTotalStockRequest = new BaseRequest<>();
        mGetTotalStockRequest.setWsCode(WsCode.GetStockTotal);
        GetTotalStockRequest request = new GetTotalStockRequest();
        if (currentSaleProgram.getId() != -1) {
            request.setSaleProgameId(currentSaleProgram.getId());
        }
        if (currentTelecomService.getId() != -1) {
            request.setTelecomServiceId(currentTelecomService.getId());
        }
        //TODO set attribute for request
        mGetTotalStockRequest.setRequest(request);
        Subscription subscription = mUserRepository.getModelSales(mGetTotalStockRequest)
                .subscribe(new MBCCSSubscribe<List<ModelSale>>() {
                    @Override
                    public void onSuccess(List<ModelSale> object) {
                        mModelSales.clear();
                        mModelSales.addAll(object);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(BaseException error) {

                        //DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                        fakeModelSale();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mSubscription.add(subscription);
    }

    private void initialData() {
        Observable.zip(getObservableTeleComserviceAndSaleProgram(), getObservaleChannelInfors(),
                new Func2<TelecomServiceAndSaleProgramResponse, List<ChannelInfo>, SaleChannelInitData>() {
                    @Override
                    public SaleChannelInitData call(
                            TelecomServiceAndSaleProgramResponse telecomServiceAndSaleProgramResponse,
                            List<ChannelInfo> channelInfos) {
                        if (telecomServiceAndSaleProgramResponse == null || channelInfos == null) {
                            return null;
                        }
                        return new SaleChannelInitData(telecomServiceAndSaleProgramResponse,
                                channelInfos);
                    }
                }).subscribe(new MBCCSSubscribe<SaleChannelInitData>() {
            @Override
            public void onSuccess(SaleChannelInitData object) {
                if (object != null) {

                    //telecom service and salprogram
                    mTeleComServices.addAll(
                            object.getTelecomServiceAndSaleProgramResponse().getTeleComServices());
                    mSalePrograms.addAll(
                            object.getTelecomServiceAndSaleProgramResponse().getSalePrograms());
                    mAdapter.notifyDataSetChanged();
                    TeleComService defaultTelecomSercie = new TeleComService(-1,
                            mContext.getResources().getString(R.string.all_));
                    mTeleComServices.add(0, defaultTelecomSercie);

                    SaleProgram defaultSaleProgram =
                            new SaleProgram(-1, mContext.getResources().getString(R.string.all_));
                    mSalePrograms.add(0, defaultSaleProgram);

                    currentSaleProgram = mSalePrograms.get(0);
                    currentTelecomService = mTeleComServices.get(0);

                    sellProgram.set(currentSaleProgram.getName());

                    //channel
                    mChannelInfos.clear();
                    mChannelInfos.addAll(object.getChannelInfos());
                    ChannelInfo channel =
                            new ChannelInfo(-1, mContext.getResources().getString(R.string.all_));
                    mChannelInfos.add(0, channel);
                    currentChannel = mChannelInfos.get(0);
                    channelText.set(currentChannel.getChannelName());

                    loadModelSale();
                }
            }

            @Override
            public void onError(BaseException error) {

                fakeLoadChannel();
                //fake
                fakeData();
                loadModelSale();

                //DialogUtils.showDialogError(mContext, null, error.getMessage(), null);

            }
        });
    }

    private Observable<TelecomServiceAndSaleProgramResponse> getObservableTeleComserviceAndSaleProgram() {
        mGetTelecomServiceAndSaleProgramRequest = new BaseRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setWsCode(WsCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        mGetTelecomServiceAndSaleProgramRequest.setRequest(request);
        return mUserRepository.getTelecomserviceAndSaleProgram(
                mGetTelecomServiceAndSaleProgramRequest);
    }

    private Observable<List<ChannelInfo>> getObservaleChannelInfors() {
        mGetListChannelByOwnerTypeIdRequest = new BaseRequest<>();
        mGetListChannelByOwnerTypeIdRequest.setWsCode(WsCode.GetListChannelByOwnerTypeId);
        GetListChannelByOwnerTypeIdRequest request = new GetListChannelByOwnerTypeIdRequest();
        request.setChannelTypeId(1);
        request.setShopId(1);
        request.setStaffId(1);
        request.setLanguage("en");
        mGetListChannelByOwnerTypeIdRequest.setRequest(request);
        return mSellOrdersRepository.getListChannelByOwnerTypeId(
                mGetListChannelByOwnerTypeIdRequest);
    }

    private void loadServiceAndProgram() {
        mViewModel.showLoading();
        mGetTelecomServiceAndSaleProgramRequest = new BaseRequest<>();
        mGetTelecomServiceAndSaleProgramRequest.setWsCode(WsCode.GetTelecomServiceAndSaleProgram);
        GetTelecomServiceAndSaleProgramRequest request =
                new GetTelecomServiceAndSaleProgramRequest();
        mGetTelecomServiceAndSaleProgramRequest.setRequest(request);

        Subscription subscription = mUserRepository.getTelecomserviceAndSaleProgram(
                mGetTelecomServiceAndSaleProgramRequest)
                .subscribe(new MBCCSSubscribe<TelecomServiceAndSaleProgramResponse>() {
                    @Override
                    public void onSuccess(TelecomServiceAndSaleProgramResponse object) {
                        mTeleComServices.addAll(object.getTeleComServices());
                        mSalePrograms.addAll(object.getSalePrograms());
                        mAdapter.notifyDataSetChanged();
                        TeleComService defaultTelecomSercie = new TeleComService(-1,
                                mContext.getResources().getString(R.string.all_));
                        mTeleComServices.add(0, defaultTelecomSercie);

                        SaleProgram defaultSaleProgram = new SaleProgram(-1,
                                mContext.getResources().getString(R.string.all_));
                        mSalePrograms.add(0, defaultSaleProgram);

                        currentSaleProgram = mSalePrograms.get(0);
                        currentTelecomService = mTeleComServices.get(0);

                        sellProgram.set(currentSaleProgram.getName());

                        loadModelSale();
                    }

                    @Override
                    public void onError(BaseException error) {
                        DialogUtils.showDialogError(mContext, null, error.getMessage(), null);
                        fakeData();
                        loadModelSale();
                    }

                    @Override
                    public void onRequestFinish() {
                        super.onRequestFinish();
                        mViewModel.hideLoading();
                    }
                });

        mSubscription.add(subscription);
    }

    private void fakeModelSale() {

        mModelSales.clear();

        ModelSale good1 = new ModelSale();
        good1.setStockMoldeName("Iphone 7 plus");
        good1.setQuantity(12);
        good1.setPrice(5000000);
        good1.setPathImage1("http://didongthongminh"
                + ".vn/images/products/2017/03/31/resized/samsung-galaxy-s8-plus"
                + "-_1490956081.jpg");

        ModelSale good2 = new ModelSale();
        good2.setStockMoldeName("Samsung J5 Prime");
        good2.setQuantity(10);
        good2.setPrice(400000);
        good2.setPathImage1(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/J5-Prime-A.jpg");

        ModelSale good3 = new ModelSale();
        good3.setStockMoldeName("Oppo F1s");
        good3.setQuantity(5);
        good3.setPrice(7000000);
        good3.setPathImage1(
                "https://cdn1.viettelstore.vn/images/Product/ProductImage/small/3211396993674.jpg");
        mModelSales.add(good1);
        mModelSales.add(good2);
        mModelSales.add(good3);
        stockAdapter.notifyDataSetChanged();


    }

    void fakeLoadChannel(){
        ChannelInfo channel1 = new ChannelInfo();
        channel1.setChannelId(1);
        channel1.setChannelName("CTV1");

        ChannelInfo channel2 = new ChannelInfo();
        channel2.setChannelId(2);
        channel2.setChannelName("CTV2");

        ChannelInfo channel3 = new ChannelInfo();
        channel3.setChannelId(3);
        channel3.setChannelName("CTV3");

        ChannelInfo channel4 = new ChannelInfo();
        channel4.setChannelId(4);
        channel4.setChannelName("CTV4");

        mChannelInfos.add(channel1);
        mChannelInfos.add(channel2);
        mChannelInfos.add(channel3);
        mChannelInfos.add(channel4);
        ChannelInfo channel = new ChannelInfo(-1, mContext.getResources().getString(R.string.all_));
        mChannelInfos.add(0, channel);
        currentChannel = mChannelInfos.get(0);
        channelText.set(currentChannel.getChannelName());
    }

    private void fakeData() {

        SaleProgram sell1 = new SaleProgram(1, "0", "khuyen mai 1");
        SaleProgram sell2 = new SaleProgram(1, "1", "khuyen mai 2");
        SaleProgram sell3 = new SaleProgram(1, "2", "khuyen mai 3");
        mSalePrograms.add(sell1);
        mSalePrograms.add(sell2);
        mSalePrograms.add(sell3);

        TeleComService service2 = new TeleComService(1, "Mobile");
        TeleComService service3 = new TeleComService(2, "PC");
        TeleComService service4 = new TeleComService(3, "OK");
        TeleComService service5 = new TeleComService(4, "Phu kien");
        mTeleComServices.add(service2);
        mTeleComServices.add(service3);
        mTeleComServices.add(service4);
        mTeleComServices.add(service5);

        TeleComService defaultTelecomSercie =
                new TeleComService(-1, mContext.getResources().getString(R.string.all_));
        mTeleComServices.add(0, defaultTelecomSercie);

        SaleProgram defaultSaleProgram =
                new SaleProgram(-1, mContext.getResources().getString(R.string.all_));
        mSalePrograms.add(0, defaultSaleProgram);

        currentSaleProgram = mSalePrograms.get(0);
        currentTelecomService = mTeleComServices.get(0);

        sellProgram.set(currentSaleProgram.getName());
        mAdapter.notifyDataSetChanged();
    }

    private void init() {
        filterText = new ObservableField<>();
        sellProgram = new ObservableField<>();
        channelText = new ObservableField<>();
        isCollapse = new ObservableField<>();
        isCollapse.set(false);
        mAdapter = new ArrayAdapter<TeleComService>(mContext, R.layout.item_spinner, R.id.text,
                mTeleComServices);
        stockAdapter = new StockAdapter(mContext, mModelSales);
        stockAdapter.setOnStockListener(this);
    }

    public void onCollapse() {
        isCollapse.set(!isCollapse.get());
        changeSearchFilter();
    }

    public void onExpand() {
        isCollapse.set(false);
        changeSearchFilter();
    }

    public void chooseSellProgram() {
        mViewModel.onChooseSaleProgram(mSalePrograms);
    }

    public void chooseChannel() {
        mViewModel.onChooseChannel(mChannelInfos);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

        mSubscription.clear();
    }

    public ArrayAdapter<TeleComService> getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ArrayAdapter<TeleComService> adapter) {
        mAdapter = adapter;
    }

    public StockAdapter getStockAdapter() {
        return stockAdapter;
    }

    public void setStockAdapter(StockAdapter stockAdapter) {
        this.stockAdapter = stockAdapter;
    }

    @Override
    public void onGetSaleProgramSuccess(SaleProgram saleProgram) {
        if (saleProgram == null) {
            return;
        }
        currentSaleProgram = saleProgram;
        sellProgram.set(currentSaleProgram.getName());
        loadModelSale();
    }

    @Override
    public void onGetChannelSuccess(ChannelInfo channelInfo) {
        if (channelInfo == null) {
            return;
        }
        currentChannel = channelInfo;
        channelText.set(currentChannel.getChannelName());
        loadModelSale();
    }

    @Override
    public void onItemServiceClick(int position) {
        currentTelecomService = mTeleComServices.get(position);
        loadModelSale();
    }

    @Override
    public void onSerialPickerSuccess(List<String> serials) {
        mModelSales.get(currentStockPosition).setSerials(serials);
        stockAdapter.notifyItemChanged(currentStockPosition);
    }

    @Override
    public void refresh() {
        currentSaleProgram = mSalePrograms.get(0);
        currentTelecomService = mTeleComServices.get(0);
        mViewModel.refresh();
        sellProgram.set(currentSaleProgram.getName());
        currentStockPosition = -1;
        changeSearchFilter();
        loadModelSale();
    }

    public void changeSearchFilter() {
        String filter0;
        String filter1;
        String filter2;
        if (currentChannel.getChannelId() == -1) {
            filter0 = mContext.getResources().getString(R.string.all);
        } else {
            filter0 = currentChannel.getChannelName();
        }
        if (currentTelecomService.getId() == -1) {
            filter1 = mContext.getResources().getString(R.string.all);
        } else {
            filter1 = currentTelecomService.getName();
        }
        if (currentSaleProgram.getId() == -1) {
            filter2 = mContext.getResources().getString(R.string.all);
        } else {
            filter2 = currentSaleProgram.getName();
        }

        filterText.set(filter0 + " - " + filter1 + " - " + filter2);
    }

    @Override
    public void onSerialClick(ModelSale item, int position) {
        currentStockPosition = position;
        mViewModel.onSerialPicker(item);
    }

    public void onNext() {
        mViewModel.onNext(mModelSales, currentTelecomService, currentSaleProgram,currentChannel);
    }
}