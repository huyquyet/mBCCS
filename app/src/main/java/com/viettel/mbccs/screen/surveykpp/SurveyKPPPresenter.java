package com.viettel.mbccs.screen.surveykpp;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class SurveyKPPPresenter implements SurveyKPPContract.Presenter {

    private Context context;
    private SurveyKPPContract.ViewModel viewModel;
    public ObservableField<String> title;

    public SurveyKPPPresenter(Context context, SurveyKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            title = new ObservableField<>(context.getString(R.string.survey_kpp_title));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {
    }

    public void onCancel() {
        viewModel.onCancel();
    }
}
