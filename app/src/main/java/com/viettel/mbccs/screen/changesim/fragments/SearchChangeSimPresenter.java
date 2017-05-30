package com.viettel.mbccs.screen.changesim.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ChangeSimInfo;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.screen.changesim.adapter.ChangeSimListAdapter;
import com.viettel.mbccs.screen.common.adapter.HintArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class SearchChangeSimPresenter implements SearchChangeSimContract.Presenter {

    private Context context;
    private SearchChangeSimContract.ViewModel viewModel;
    private ChangeSimListAdapter changeSimAdapter;

    public ObservableField<String> documentType;
    public ObservableField<String> documentId;
    public ObservableField<String> isdn;
    public ObservableBoolean searchFound;
    public ObservableField<ChangeSimListAdapter> changeSimListAdapter;

    private HintArrayAdapter<String> documentTypeAdapter;

    private List<String> documentTypesList;
    private List<KeyValue> documentTypes;

    private ChangeSimRepository repository;

    public SearchChangeSimPresenter(Context context, final SearchChangeSimContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        documentId = new ObservableField<>();
        documentType = new ObservableField<>();
        isdn = new ObservableField<>();
        changeSimListAdapter = new ObservableField<>();
        searchFound = new ObservableBoolean(true);
        changeSimAdapter = new ChangeSimListAdapter(context, new ArrayList<ChangeSimItem>());
        changeSimAdapter.setOnItemClickListener(new ChangeSimListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, ChangeSimItem item) {
                viewModel.onPrepareChangeSim(item);
            }
        });

        documentTypesList = new ArrayList<>();
        documentTypeAdapter = new HintArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                documentTypesList);

        initListeners();
        initData();
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        try {
            repository = ChangeSimRepository.getInstance();

            documentTypes = repository.getDocumentTypes();

            documentTypesList.add(context.getString(R.string.branches_add_hint_document_type));
            for (KeyValue item : documentTypes) {
                documentTypesList.add(item.getValue());
            }

            documentTypeAdapter.notifyDataSetChanged();

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

    @Override
    public void searchSim() {

        try {

            if (isdn.get() == null || "".equals(isdn.get().trim())) {
                viewModel.showError(context.getString(R.string.change_sim_error_search_isdn_required));
                return;
            } else if (documentType.get() == null || "".equals(documentType.get().trim())) {
                viewModel.showError(context.getString(R.string.change_sim_error_search_documentType_required));
                return;
            } else if (documentId.get() == null || "".equals(documentId.get().trim())) {
                viewModel.showError(context.getString(R.string.change_sim_error_search_documentId_required));
                return;
            }

            List<ChangeSimItem> items = new ArrayList<>();

            ChangeSimItem item = new ChangeSimItem();
            item.setChangeSimInfo(new ChangeSimInfo("123456789", "987654321"));

            items.add(item);

            item = new ChangeSimItem();
            item.setChangeSimInfo(new ChangeSimInfo("1234567899", "9876543211"));

            items.add(item);

            changeSimAdapter.setBranchItems(items);

            changeSimListAdapter.set(changeSimAdapter);

            if (changeSimAdapter.getItemCount() > 0) {
                searchFound.set(true);

                viewModel.onSimFound(isdn.get(), documentType.get(), documentId.get());
            } else {
                searchFound.set(false);

                viewModel.onSimNotFound(isdn.get(), documentType.get(), documentId.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDocumentTypeChanged(int index) {
        try {
            documentType.set(documentTypes.get(index).getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HintArrayAdapter<String> getDocumentTypeAdapter() {
        return documentTypeAdapter;
    }
}
