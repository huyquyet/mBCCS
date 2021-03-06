package com.viettel.mbccs.screen.sell.retail.sellprogrampicker;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;

/**
 * Created by eo_cuong on 5/16/17.
 */

public interface SaleProgramContract {

    interface Presenter extends BasePresenter {

        void onTextChange(String s);
    }

    interface ViewModel extends BaseSearchListViewContract.ViewModel {

    }
}
