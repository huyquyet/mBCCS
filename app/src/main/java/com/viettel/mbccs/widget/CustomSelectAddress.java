package com.viettel.mbccs.widget;

import android.content.Context;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.LayoutSelectAddressBinding;
import com.viettel.mbccs.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuyQuyet on 5/30/17.
 */
@BindingMethods({
        @BindingMethod(type = CustomSelectAddress.class, attribute = "idProvince", method = "setCurrentProvince"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "idDistrict", method = "setCurrentDistrict"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "idPrecinct", method = "setCurrentPrecinct"),
        @BindingMethod(type = CustomSelectAddress.class, attribute = "address", method = "setCurrentAddress")
})
public class CustomSelectAddress extends LinearLayout
        implements AdapterView.OnItemSelectedListener {
    private Context context;
    private LayoutSelectAddressBinding binding;
    private UserRepository repository;
    private List<Province> provinceList;
    private List<District> districtList;
    private List<Precinct> precinctList;

    private List<String> dataProvince;
    private List<String> dataDistrict;
    private List<String> dataPrecinct;

    private String setProvinceId;
    private String setDistrictId;
    private String setPrecinctId;
    private String setAddress;

    private int positionProvince;
    private int positionDistrict;
    private int positionPrecinct;

    public ObservableField<ArrayAdapter<String>> adapterProvince;
    public ObservableField<ArrayAdapter<String>> adapterDistrict;
    public ObservableField<ArrayAdapter<String>> adapterPrecinct;
    public ObservableField<String> txtAddressDetail;

    public CustomSelectAddress(@NonNull Context context) {
        this(context, null);
        this.context = context;
    }

    public CustomSelectAddress(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelectAddress(@NonNull Context context, @Nullable AttributeSet attrs,
            @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        repository = UserRepository.getInstance();
        initView(context, attrs);
    }

    private void initView(final Context context, AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.layout_select_address, this, true);
        binding.setPresenter(this);

        binding.spinnerLayoutSelectProvince.setOnItemSelectedListener(this);
        binding.spinnerLayoutSelectDistrict.setOnItemSelectedListener(this);
        binding.spinnerLayoutSelectPrecinct.setOnItemSelectedListener(this);

        adapterProvince = new ObservableField<>();
        adapterDistrict = new ObservableField<>();
        adapterPrecinct = new ObservableField<>();
        txtAddressDetail = new ObservableField<>();

        dataProvince = new ArrayList<>();
        dataDistrict = new ArrayList<>();
        dataPrecinct = new ArrayList<>();

        provinceList = new ArrayList<>();
        districtList = new ArrayList<>();
        precinctList = new ArrayList<>();

        adapterProvince.set(
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataProvince));
        adapterProvince.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterDistrict.set(
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataDistrict));
        adapterDistrict.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterPrecinct.set(
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dataPrecinct));
        adapterPrecinct.get()
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getDataProvince();
    }

    private void getDataProvince() {
        provinceList = repository.getListProvince();
        for (Province p : provinceList) {
            dataProvince.add(p.getName());
        }
        adapterProvince.get().notifyDataSetChanged();
        if (StringUtils.isEmpty(setProvinceId)) return;

        int position = 0;
        for (int i = 0; i < provinceList.size(); i++) {
            if (!setProvinceId.equals(provinceList.get(i).getProvinceId())) continue;
            position = i;
            break;
        }
        binding.spinnerLayoutSelectProvince.setSelection(position);
    }

    private void getDataDistrict(String idProvince) {
        districtList = repository.getListDistrictByProvinceId(idProvince);
        for (District d : districtList) {
            dataDistrict.add(d.getName());
        }
        adapterDistrict.get().notifyDataSetChanged();

        if (StringUtils.isEmpty(setDistrictId)) return;

        int position = 0;
        for (int i = 0; i < districtList.size(); i++) {
            if (!setDistrictId.equals(districtList.get(i).getProvinceId())) continue;
            position = i;
            break;
        }
        binding.spinnerLayoutSelectDistrict.setSelection(position);
    }

    private void getDataPrecinct(String idDistrict) {
        precinctList = repository.getListPrecinctByDistrictId(idDistrict);
        for (Precinct p : precinctList) {
            dataPrecinct.add(p.getName());
        }
        adapterPrecinct.get().notifyDataSetChanged();

        if (!StringUtils.isEmpty(setPrecinctId)) {
            int position = 0;
            for (int i = 0; i < precinctList.size(); i++) {
                if (!setPrecinctId.equals(precinctList.get(i).getProvinceId())) continue;
                position = i;
                break;
            }
            binding.spinnerLayoutSelectPrecinct.setSelection(position);
        }

        if (StringUtils.isEmpty(setAddress)) {
            txtAddressDetail.set(setAddress
                    + " - "
                    + precinctList.get(positionPrecinct).getName()
                    + " - "
                    + districtList.get(positionDistrict).getName()
                    + " - "
                    + provinceList.get(positionProvince).getName());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner_layout_select_province:
                if (positionProvince == position) return;

                positionProvince = position;

                districtList.clear();
                adapterDistrict.get().notifyDataSetChanged();
                precinctList.clear();
                adapterPrecinct.get().notifyDataSetChanged();

                getDataDistrict(provinceList.get(position).getProvinceId());

                break;
            case R.id.spinner_layout_select_district:
                if (positionDistrict == position) return;

                positionDistrict = position;

                precinctList.clear();
                adapterPrecinct.get().notifyDataSetChanged();

                getDataPrecinct(districtList.get(position).getDistrictId());
                break;
            case R.id.spinner_layout_select_precinct:
                if (positionPrecinct == position) return;
                positionPrecinct = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setCurrentProvince(String idProvince) {
        setProvinceId = idProvince;
    }

    public void setCurrentDistrict(String idDistrict) {
        setDistrictId = idDistrict;
    }

    public void setCurrentPrecinct(String idPrecinct) {
        setPrecinctId = idPrecinct;
    }

    public void setCurrentAddress(String address) {
        setAddress = address;
    }

    public Province getProvince() {
        return provinceList.size() != 0 ? provinceList.get(positionProvince) : null;
    }

    public District getDistrict() {
        return districtList.size() != 0 ? districtList.get(positionDistrict) : null;
    }

    public Precinct getPrecinct() {
        return districtList.size() != 0 ? precinctList.get(positionPrecinct) : null;
    }

    public String getAddress() {
        return txtAddressDetail.get();
    }
}
