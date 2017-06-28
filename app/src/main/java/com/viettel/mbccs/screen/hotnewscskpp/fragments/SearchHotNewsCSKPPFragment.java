package com.viettel.mbccs.screen.hotnewscskpp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindFragment;
import com.viettel.mbccs.data.model.HotNewsCSKPPItem;
import com.viettel.mbccs.databinding.FragmentSearchHotNewsCsKppBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by minhnx on 5/20/17.
 */

public class SearchHotNewsCSKPPFragment extends BaseDataBindFragment<FragmentSearchHotNewsCsKppBinding, SearchHotNewsCSKPPPresenter>
        implements SearchHotNewsCSKPPContract.ViewModel {

    private AppCompatActivity mActivity;

    public static SearchHotNewsCSKPPFragment newInstance() {
        return new SearchHotNewsCSKPPFragment();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initData() {
        mPresenter = new SearchHotNewsCSKPPPresenter(getContext(), this);
        mBinding.setPresenter(mPresenter);

        initListeners();
    }

    @Override
    protected int getIdLayoutRes() {
        return R.layout.fragment_search_hot_news_cs_kpp;
    }

    @Override
    protected void initView() {

    }

    private void initListeners() {
        try {
            mBinding.srlNewsList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.searchNews();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AppCompatActivity) activity;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSearchCompleted() {
        mBinding.srlNewsList.setRefreshing(false);
    }

    @Override
    public void openHotNewsDetail(HotNewsCSKPPItem item) {
        try {
            ViewHotNewsCSKPPFragment fragment = ViewHotNewsCSKPPFragment.newInstance();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.ITEM_LIST, GsonUtils.Object2String(item));

            fragment.setArguments(args);

            mActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_main, fragment)
                    .addToBackStack(null)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
