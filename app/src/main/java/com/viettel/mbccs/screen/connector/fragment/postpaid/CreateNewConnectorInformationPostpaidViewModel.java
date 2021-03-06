package com.viettel.mbccs.screen.connector.fragment.postpaid;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.viettel.mbccs.BR;
import com.viettel.mbccs.constance.Data;
import com.viettel.mbccs.data.model.ApDomainByType;
import com.viettel.mbccs.data.model.Bank;
import com.viettel.mbccs.data.model.BusType;
import com.viettel.mbccs.data.model.CurrBillCycle;
import com.viettel.mbccs.data.model.Product;
import com.viettel.mbccs.data.model.Quota;
import com.viettel.mbccs.data.model.RegType;
import com.viettel.mbccs.data.model.SubType;
import com.viettel.mbccs.utils.SpinnerAdapter;
import com.viettel.mbccs.widget.model.AddressApp;
import java.util.Date;

/**
 * Created by HuyQuyet on 7/7/17.
 */

public class CreateNewConnectorInformationPostpaidViewModel extends BaseObservable {
    private int contractLoaiKhachHang;
    private boolean isViewThongTinNganHang;
    private int selectedPosition2HanMuc;
    private int selectedPosition2GoiCuoc;
    private int selectedPosition1CustomerType;


    //region Fragment 1
    /**
     * Fragment 1
     */
    // Customer
    private String customerNameCustomer;
    private String customerPlaceCreatePassport;
    private String customerNumberGPKH;
    private String customerNumberMaSoThue;

    private String customerContractTenNguoiDaiDien;
    private String customerContractChucVu;
    private String customerContractCMTNDHoCHieu;
    private String customerContractInformationDienThoai;

    private String customerNameCustomerError;
    private String customerPlaceCreatePassportError;
    private String customerNumberGPKHError;
    private String customerNumberMaSoThueError;

    private String customerContractTenNguoiDaiDienError;
    private String customerContractChucVuError;
    private String customerContractCMTNDHoCHieuError;
    private String customerContractInformationDienThoaiError;

    private String customerDateCreate;
    private String customerDateBirthday;

    private Date customerMaxDateBirthday;
    private Date customerMinDateCreate;

    private SpinnerAdapter<BusType> customerAdapterSpinnerCustomerType;

    private AddressApp customerArea1;


    private String imageUrlFront;
    private String imageUrlBackside;
    private String imageUrlPortrait;

    //endregion Fragment 1

    //region Fragment 2
    /**
     * Fragment 2
     */
    private String subscriberIsdn;
    private String subscriberSerial;
    private String subscriberInformationStock;
    private String subscriberInformationContract;

    private String subscriberIsdnError;
    private String subscriberSerialError;
    private String subscriberInformationStockError;
    private String subscriberInformationContractError;

    private SpinnerAdapter<Data.DataField> subscriberAdapterSpinner2DichVu;
    private SpinnerAdapter<Product> subscriberAdapterSpinner2GoiCuoc;
    private SpinnerAdapter<SubType> subscriberAdapterSpinner2LoaiThueBao;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2HTHoaMang;
    private SpinnerAdapter<RegType> subscriberAdapterSpinner2KhuyenMai;
    private SpinnerAdapter<ApDomainByType> subscriberAdapterSpinner2DatCoc;
    private SpinnerAdapter<Quota> subscriberAdapterSpinner2HanMuc;

    private AddressApp subscriberArea2;
    //endregion Fragment 2

    //region Fragment 3
    /**
     * Fragment 3
     */
    private String contractNguoiThanhToan;
    private String contractHopDongThu;
    private String contractSoTaiKhoan;
    private String contractTenTaiKhoan;

    private String contractNguoiThanhToanError;
    private String contractHopDongThuError;
    private String contractSoTaiKhoanError;
    private String contractTenTaiKhoanError;

    private SpinnerAdapter<ApDomainByType> contractAdapterSpinner3LoaiHopDong;
    private SpinnerAdapter<ApDomainByType> contractAdapterSpinner3HinhThucThanhToan;
    private SpinnerAdapter<CurrBillCycle> contractAdapterSpinner3ChuKyCuoc;
    private SpinnerAdapter<ApDomainByType> contractAdapterSpinner3ChiTietIn;
    private SpinnerAdapter<Bank> contractAdapterSpinner3TenNganHang;
    private SpinnerAdapter<ApDomainByType> contractAdapterSpinner3HinhThucThongBaoCuoc;

    private Date contractMaxDateNgayKy;
    private Date contractMaxDateFromNgayHieuLuc;
    private Date contractMaxDateToNgayHieuLuc;

    private String contractDateNgayKy;
    private String contractDateFromNgayHieuLuc;
    private String contractDateToNgayHieuLuc;

    private AddressApp area3;
    //endregion Fragment 3

    public CreateNewConnectorInformationPostpaidViewModel() {

    }

    //region Set - Get

    @Bindable
    public int getContractLoaiKhachHang() {
        return contractLoaiKhachHang;
    }

    public void setContractLoaiKhachHang(int contractLoaiKhachHang) {
        this.contractLoaiKhachHang = contractLoaiKhachHang;
        notifyPropertyChanged(BR.contractLoaiKhachHang);
    }

    @Bindable
    public boolean isViewThongTinNganHang() {
        return isViewThongTinNganHang;
    }

    public void setViewThongTinNganHang(boolean viewThongTinNganHang) {
        isViewThongTinNganHang = viewThongTinNganHang;
        notifyPropertyChanged(BR.viewThongTinNganHang);
    }

    @Bindable
    public int getSelectedPosition2HanMuc() {
        return selectedPosition2HanMuc;
    }

    public void setSelectedPosition2HanMuc(int selectedPosition2HanMuc) {
        this.selectedPosition2HanMuc = selectedPosition2HanMuc;
        notifyPropertyChanged(BR.selectedPosition2HanMuc);
    }

    @Bindable
    public int getSelectedPosition2GoiCuoc() {
        return selectedPosition2GoiCuoc;
    }

    public void setSelectedPosition2GoiCuoc(int selectedPosition2GoiCuoc) {
        this.selectedPosition2GoiCuoc = selectedPosition2GoiCuoc;
        notifyPropertyChanged(BR.selectedPosition2GoiCuoc);
    }

    @Bindable
    public int getSelectedPosition1CustomerType() {
        return selectedPosition1CustomerType;
    }

    public void setSelectedPosition1CustomerType(int selectedPosition1CustomerType) {
        this.selectedPosition1CustomerType = selectedPosition1CustomerType;
        notifyPropertyChanged(BR.selectedPosition1CustomerType);
    }

    //region Set - Get Fragment 1
    @Bindable
    public String getCustomerNameCustomer() {
        return customerNameCustomer;
    }

    public void setCustomerNameCustomer(String customerNameCustomer) {
        this.customerNameCustomer = customerNameCustomer;
        notifyPropertyChanged(BR.customerNameCustomer);
    }

    @Bindable
    public String getCustomerPlaceCreatePassport() {
        return customerPlaceCreatePassport;
    }

    public void setCustomerPlaceCreatePassport(String customerPlaceCreatePassport) {
        this.customerPlaceCreatePassport = customerPlaceCreatePassport;
        notifyPropertyChanged(BR.customerPlaceCreatePassport);
    }

    @Bindable
    public String getCustomerNumberGPKH() {
        return customerNumberGPKH;
    }

    public void setCustomerNumberGPKH(String customerNumberGPKH) {
        this.customerNumberGPKH = customerNumberGPKH;
        notifyPropertyChanged(BR.customerNumberGPKH);
    }

    @Bindable
    public String getCustomerNumberMaSoThue() {
        return customerNumberMaSoThue;
    }

    public void setCustomerNumberMaSoThue(String customerNumberMaSoThue) {
        this.customerNumberMaSoThue = customerNumberMaSoThue;
        notifyPropertyChanged(BR.customerNumberMaSoThue);
    }

    @Bindable
    public String getCustomerNameCustomerError() {
        return customerNameCustomerError;
    }

    public void setCustomerNameCustomerError(String customerNameCustomerError) {
        this.customerNameCustomerError = customerNameCustomerError;
        notifyPropertyChanged(BR.customerNameCustomerError);
    }

    @Bindable
    public String getCustomerPlaceCreatePassportError() {
        return customerPlaceCreatePassportError;
    }

    public void setCustomerPlaceCreatePassportError(String customerPlaceCreatePassportError) {
        this.customerPlaceCreatePassportError = customerPlaceCreatePassportError;
        notifyPropertyChanged(BR.customerPlaceCreatePassportError);
    }

    @Bindable
    public String getCustomerNumberGPKHError() {
        return customerNumberGPKHError;
    }

    public void setCustomerNumberGPKHError(String customerNumberGPKHError) {
        this.customerNumberGPKHError = customerNumberGPKHError;
        notifyPropertyChanged(BR.customerNumberGPKHError);
    }

    @Bindable
    public String getCustomerNumberMaSoThueError() {
        return customerNumberMaSoThueError;
    }

    public void setCustomerNumberMaSoThueError(String customerNumberMaSoThueError) {
        this.customerNumberMaSoThueError = customerNumberMaSoThueError;
        notifyPropertyChanged(BR.customerNumberMaSoThueError);
    }

    @Bindable
    public String getCustomerDateCreate() {
        return customerDateCreate;
    }

    public void setCustomerDateCreate(String customerDateCreate) {
        this.customerDateCreate = customerDateCreate;
        notifyPropertyChanged(BR.customerDateCreate);
    }

    @Bindable
    public String getCustomerDateBirthday() {
        return customerDateBirthday;
    }

    public void setCustomerDateBirthday(String customerDateBirthday) {
        this.customerDateBirthday = customerDateBirthday;
        notifyPropertyChanged(BR.customerDateBirthday);
    }

    @Bindable
    public Date getCustomerMaxDateBirthday() {
        return customerMaxDateBirthday;
    }

    public void setCustomerMaxDateBirthday(Date customerMaxDateBirthday) {
        this.customerMaxDateBirthday = customerMaxDateBirthday;
        notifyPropertyChanged(BR.customerMaxDateBirthday);
    }

    @Bindable
    public Date getCustomerMinDateCreate() {
        return customerMinDateCreate;
    }

    public void setCustomerMinDateCreate(Date customerMinDateCreate) {
        this.customerMinDateCreate = customerMinDateCreate;
        notifyPropertyChanged(BR.customerMinDateCreate);
    }

    @Bindable
    public SpinnerAdapter<BusType> getCustomerAdapterSpinnerCustomerType() {
        return customerAdapterSpinnerCustomerType;
    }

    public void setCustomerAdapterSpinnerCustomerType(
            SpinnerAdapter<BusType> customerAdapterSpinnerCustomerType) {
        this.customerAdapterSpinnerCustomerType = customerAdapterSpinnerCustomerType;
        notifyPropertyChanged(BR.customerAdapterSpinnerCustomerType);
    }

    public AddressApp getCustomerArea1() {
        return customerArea1;
    }

    @Bindable
    public void setCustomerArea1(AddressApp customerArea1) {
        this.customerArea1 = customerArea1;
        notifyPropertyChanged(BR.customerArea1);
    }

    @Bindable
    public String getImageUrlFront() {
        return imageUrlFront;
    }

    public void setImageUrlFront(String imageUrlFront) {
        this.imageUrlFront = imageUrlFront;
        notifyPropertyChanged(BR.imageUrlFront);
    }

    @Bindable
    public String getImageUrlBackside() {
        return imageUrlBackside;
    }

    public void setImageUrlBackside(String imageUrlBackside) {
        this.imageUrlBackside = imageUrlBackside;
        notifyPropertyChanged(BR.imageUrlBackside);
    }

    @Bindable
    public String getImageUrlPortrait() {
        return imageUrlPortrait;
    }

    public void setImageUrlPortrait(String imageUrlPortrait) {
        this.imageUrlPortrait = imageUrlPortrait;
        notifyPropertyChanged(BR.imageUrlPortrait);
    }

    //endregion Set - Get Fragment 1

    //region Set - Get Fragment 2
    @Bindable
    public String getSubscriberIsdn() {
        return subscriberIsdn;
    }

    public void setSubscriberIsdn(String subscriberIsdn) {
        this.subscriberIsdn = subscriberIsdn;
        notifyPropertyChanged(BR.subscriberIsdn);
    }

    @Bindable
    public String getSubscriberSerial() {
        return subscriberSerial;
    }

    public void setSubscriberSerial(String subscriberSerial) {
        this.subscriberSerial = subscriberSerial;
        notifyPropertyChanged(BR.subscriberSerial);
    }

    @Bindable
    public String getSubscriberInformationStock() {
        return subscriberInformationStock;
    }

    public void setSubscriberInformationStock(String subscriberInformationStock) {
        this.subscriberInformationStock = subscriberInformationStock;
        notifyPropertyChanged(BR.subscriberInformationStock);
    }

    @Bindable
    public String getSubscriberInformationContract() {
        return subscriberInformationContract;
    }

    public void setSubscriberInformationContract(String subscriberInformationContract) {
        this.subscriberInformationContract = subscriberInformationContract;
        notifyPropertyChanged(BR.subscriberInformationContract);
    }

    @Bindable
    public String getSubscriberIsdnError() {
        return subscriberIsdnError;
    }

    public void setSubscriberIsdnError(String subscriberIsdnError) {
        this.subscriberIsdnError = subscriberIsdnError;
        notifyPropertyChanged(BR.subscriberIsdnError);
    }

    @Bindable
    public String getSubscriberSerialError() {
        return subscriberSerialError;
    }

    public void setSubscriberSerialError(String subscriberSerialError) {
        this.subscriberSerialError = subscriberSerialError;
        notifyPropertyChanged(BR.subscriberSerialError);
    }

    @Bindable
    public String getSubscriberInformationStockError() {
        return subscriberInformationStockError;
    }

    public void setSubscriberInformationStockError(String subscriberInformationStockError) {
        this.subscriberInformationStockError = subscriberInformationStockError;
        notifyPropertyChanged(BR.subscriberInformationStockError);
    }

    @Bindable
    public String getSubscriberInformationContractError() {
        return subscriberInformationContractError;
    }

    public void setSubscriberInformationContractError(String subscriberInformationContractError) {
        this.subscriberInformationContractError = subscriberInformationContractError;
        notifyPropertyChanged(BR.subscriberInformationContractError);
    }

    @Bindable
    public SpinnerAdapter<Data.DataField> getSubscriberAdapterSpinner2DichVu() {
        return subscriberAdapterSpinner2DichVu;
    }

    public void setSubscriberAdapterSpinner2DichVu(
            SpinnerAdapter<Data.DataField> subscriberAdapterSpinner2DichVu) {
        this.subscriberAdapterSpinner2DichVu = subscriberAdapterSpinner2DichVu;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2DichVu);
    }

    @Bindable
    public SpinnerAdapter<Product> getSubscriberAdapterSpinner2GoiCuoc() {
        return subscriberAdapterSpinner2GoiCuoc;
    }

    public void setSubscriberAdapterSpinner2GoiCuoc(
            SpinnerAdapter<Product> subscriberAdapterSpinner2GoiCuoc) {
        this.subscriberAdapterSpinner2GoiCuoc = subscriberAdapterSpinner2GoiCuoc;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2GoiCuoc);
    }

    @Bindable
    public SpinnerAdapter<SubType> getSubscriberAdapterSpinner2LoaiThueBao() {
        return subscriberAdapterSpinner2LoaiThueBao;
    }

    public void setSubscriberAdapterSpinner2LoaiThueBao(
            SpinnerAdapter<SubType> subscriberAdapterSpinner2LoaiThueBao) {
        this.subscriberAdapterSpinner2LoaiThueBao = subscriberAdapterSpinner2LoaiThueBao;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2LoaiThueBao);
    }

    @Bindable
    public SpinnerAdapter<RegType> getSubscriberAdapterSpinner2HTHoaMang() {
        return subscriberAdapterSpinner2HTHoaMang;
    }

    public void setSubscriberAdapterSpinner2HTHoaMang(
            SpinnerAdapter<RegType> subscriberAdapterSpinner2HTHoaMang) {
        this.subscriberAdapterSpinner2HTHoaMang = subscriberAdapterSpinner2HTHoaMang;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2HTHoaMang);
    }

    @Bindable
    public SpinnerAdapter<RegType> getSubscriberAdapterSpinner2KhuyenMai() {
        return subscriberAdapterSpinner2KhuyenMai;
    }

    public void setSubscriberAdapterSpinner2KhuyenMai(
            SpinnerAdapter<RegType> subscriberAdapterSpinner2KhuyenMai) {
        this.subscriberAdapterSpinner2KhuyenMai = subscriberAdapterSpinner2KhuyenMai;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2KhuyenMai);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getSubscriberAdapterSpinner2DatCoc() {
        return subscriberAdapterSpinner2DatCoc;
    }

    public void setSubscriberAdapterSpinner2DatCoc(
            SpinnerAdapter<ApDomainByType> subscriberAdapterSpinner2DatCoc) {
        this.subscriberAdapterSpinner2DatCoc = subscriberAdapterSpinner2DatCoc;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2DatCoc);
    }

    @Bindable
    public SpinnerAdapter<Quota> getSubscriberAdapterSpinner2HanMuc() {
        return subscriberAdapterSpinner2HanMuc;
    }

    public void setSubscriberAdapterSpinner2HanMuc(
            SpinnerAdapter<Quota> subscriberAdapterSpinner2HanMuc) {
        this.subscriberAdapterSpinner2HanMuc = subscriberAdapterSpinner2HanMuc;
        notifyPropertyChanged(BR.subscriberAdapterSpinner2HanMuc);
    }

    @Bindable
    public AddressApp getSubscriberArea2() {
        return subscriberArea2;
    }

    public void setSubscriberArea2(AddressApp subscriberArea2) {
        this.subscriberArea2 = subscriberArea2;
        notifyPropertyChanged(BR.subscriberArea2);
    }

    //endregion Set - Get Fragment 2

    //region Set - Get Fragment 3

    public String getContractNguoiThanhToan() {
        return contractNguoiThanhToan;
    }

    public void setContractNguoiThanhToan(String contractNguoiThanhToan) {
        this.contractNguoiThanhToan = contractNguoiThanhToan;
    }

    @Bindable
    public String getContractHopDongThu() {
        return contractHopDongThu;
    }

    public void setContractHopDongThu(String contractHopDongThu) {
        this.contractHopDongThu = contractHopDongThu;
        notifyPropertyChanged(BR.contractHopDongThu);
    }

    @Bindable
    public String getContractSoTaiKhoan() {
        return contractSoTaiKhoan;
    }

    public void setContractSoTaiKhoan(String contractSoTaiKhoan) {
        this.contractSoTaiKhoan = contractSoTaiKhoan;
        notifyPropertyChanged(BR.contractSoTaiKhoan);
    }

    @Bindable
    public String getContractTenTaiKhoan() {
        return contractTenTaiKhoan;
    }

    public void setContractTenTaiKhoan(String contractTenTaiKhoan) {
        this.contractTenTaiKhoan = contractTenTaiKhoan;
        notifyPropertyChanged(BR.contractTenTaiKhoan);
    }

    @Bindable
    public String getCustomerContractTenNguoiDaiDien() {
        return customerContractTenNguoiDaiDien;
    }

    public void setCustomerContractTenNguoiDaiDien(String customerContractTenNguoiDaiDien) {
        this.customerContractTenNguoiDaiDien = customerContractTenNguoiDaiDien;
        notifyPropertyChanged(BR.customerContractTenNguoiDaiDien);
    }

    @Bindable
    public String getCustomerContractChucVu() {
        return customerContractChucVu;
    }

    public void setCustomerContractChucVu(String customerContractChucVu) {
        this.customerContractChucVu = customerContractChucVu;
        notifyPropertyChanged(BR.customerContractChucVu);
    }

    @Bindable
    public String getCustomerContractCMTNDHoCHieu() {
        return customerContractCMTNDHoCHieu;
    }

    public void setCustomerContractCMTNDHoCHieu(String customerContractCMTNDHoCHieu) {
        this.customerContractCMTNDHoCHieu = customerContractCMTNDHoCHieu;
        notifyPropertyChanged(BR.customerContractCMTNDHoCHieu);
    }

    @Bindable
    public String getCustomerContractInformationDienThoai() {
        return customerContractInformationDienThoai;
    }

    public void setCustomerContractInformationDienThoai(String customerContractInformationDienThoai) {
        this.customerContractInformationDienThoai = customerContractInformationDienThoai;
        notifyPropertyChanged(BR.customerContractInformationDienThoai);
    }

    public String getContractNguoiThanhToanError() {
        return contractNguoiThanhToanError;
    }

    public void setContractNguoiThanhToanError(String contractNguoiThanhToanError) {
        this.contractNguoiThanhToanError = contractNguoiThanhToanError;
    }

    @Bindable
    public String getContractHopDongThuError() {
        return contractHopDongThuError;
    }

    public void setContractHopDongThuError(String contractHopDongThuError) {
        this.contractHopDongThuError = contractHopDongThuError;
        notifyPropertyChanged(BR.contractHopDongThuError);
    }

    @Bindable
    public String getContractSoTaiKhoanError() {
        return contractSoTaiKhoanError;
    }

    public void setContractSoTaiKhoanError(String contractSoTaiKhoanError) {
        this.contractSoTaiKhoanError = contractSoTaiKhoanError;
        notifyPropertyChanged(BR.contractSoTaiKhoanError);
    }

    @Bindable
    public String getContractTenTaiKhoanError() {
        return contractTenTaiKhoanError;
    }

    public void setContractTenTaiKhoanError(String contractTenTaiKhoanError) {
        this.contractTenTaiKhoanError = contractTenTaiKhoanError;
        notifyPropertyChanged(BR.contractTenTaiKhoanError);
    }

    @Bindable
    public String getCustomerContractTenNguoiDaiDienError() {
        return customerContractTenNguoiDaiDienError;
    }

    public void setCustomerContractTenNguoiDaiDienError(String customerContractTenNguoiDaiDienError) {
        this.customerContractTenNguoiDaiDienError = customerContractTenNguoiDaiDienError;
        notifyPropertyChanged(BR.customerContractTenNguoiDaiDienError);
    }

    @Bindable
    public String getCustomerContractChucVuError() {
        return customerContractChucVuError;
    }

    public void setCustomerContractChucVuError(String customerContractChucVuError) {
        this.customerContractChucVuError = customerContractChucVuError;
        notifyPropertyChanged(BR.customerContractChucVuError);
    }

    @Bindable
    public String getCustomerContractCMTNDHoCHieuError() {
        return customerContractCMTNDHoCHieuError;
    }

    public void setCustomerContractCMTNDHoCHieuError(String customerContractCMTNDHoCHieuError) {
        this.customerContractCMTNDHoCHieuError = customerContractCMTNDHoCHieuError;
        notifyPropertyChanged(BR.customerContractCMTNDHoCHieuError);
    }

    @Bindable
    public String getCustomerContractInformationDienThoaiError() {
        return customerContractInformationDienThoaiError;
    }

    public void setCustomerContractInformationDienThoaiError(String customerContractInformationDienThoaiError) {
        this.customerContractInformationDienThoaiError = customerContractInformationDienThoaiError;
        notifyPropertyChanged(BR.customerContractInformationDienThoaiError);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getContractAdapterSpinner3LoaiHopDong() {
        return contractAdapterSpinner3LoaiHopDong;
    }

    public void setContractAdapterSpinner3LoaiHopDong(
            SpinnerAdapter<ApDomainByType> contractAdapterSpinner3LoaiHopDong) {
        this.contractAdapterSpinner3LoaiHopDong = contractAdapterSpinner3LoaiHopDong;
        notifyPropertyChanged(BR.contractAdapterSpinner3LoaiHopDong);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getContractAdapterSpinner3HinhThucThanhToan() {
        return contractAdapterSpinner3HinhThucThanhToan;
    }

    public void setContractAdapterSpinner3HinhThucThanhToan(
            SpinnerAdapter<ApDomainByType> contractAdapterSpinner3HinhThucThanhToan) {
        this.contractAdapterSpinner3HinhThucThanhToan = contractAdapterSpinner3HinhThucThanhToan;
        notifyPropertyChanged(BR.contractAdapterSpinner3HinhThucThanhToan);
    }

    @Bindable
    public SpinnerAdapter<CurrBillCycle> getContractAdapterSpinner3ChuKyCuoc() {
        return contractAdapterSpinner3ChuKyCuoc;
    }

    public void setContractAdapterSpinner3ChuKyCuoc(
            SpinnerAdapter<CurrBillCycle> contractAdapterSpinner3ChuKyCuoc) {
        this.contractAdapterSpinner3ChuKyCuoc = contractAdapterSpinner3ChuKyCuoc;
        notifyPropertyChanged(BR.contractAdapterSpinner3ChuKyCuoc);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getContractAdapterSpinner3ChiTietIn() {
        return contractAdapterSpinner3ChiTietIn;
    }

    public void setContractAdapterSpinner3ChiTietIn(
            SpinnerAdapter<ApDomainByType> contractAdapterSpinner3ChiTietIn) {
        this.contractAdapterSpinner3ChiTietIn = contractAdapterSpinner3ChiTietIn;
        notifyPropertyChanged(BR.contractAdapterSpinner3ChiTietIn);
    }

    @Bindable
    public SpinnerAdapter<Bank> getContractAdapterSpinner3TenNganHang() {
        return contractAdapterSpinner3TenNganHang;
    }

    public void setContractAdapterSpinner3TenNganHang(
            SpinnerAdapter<Bank> contractAdapterSpinner3TenNganHang) {
        this.contractAdapterSpinner3TenNganHang = contractAdapterSpinner3TenNganHang;
        notifyPropertyChanged(BR.contractAdapterSpinner3TenNganHang);
    }

    @Bindable
    public SpinnerAdapter<ApDomainByType> getContractAdapterSpinner3HinhThucThongBaoCuoc() {
        return contractAdapterSpinner3HinhThucThongBaoCuoc;
    }

    public void setContractAdapterSpinner3HinhThucThongBaoCuoc(
            SpinnerAdapter<ApDomainByType> contractAdapterSpinner3HinhThucThongBaoCuoc) {
        this.contractAdapterSpinner3HinhThucThongBaoCuoc =
                contractAdapterSpinner3HinhThucThongBaoCuoc;
        notifyPropertyChanged(BR.contractAdapterSpinner3HinhThucThongBaoCuoc);
    }

    @Bindable
    public Date getContractMaxDateNgayKy() {
        return contractMaxDateNgayKy;
    }

    public void setContractMaxDateNgayKy(Date contractMaxDateNgayKy) {
        this.contractMaxDateNgayKy = contractMaxDateNgayKy;
        notifyPropertyChanged(BR.contractMaxDateNgayKy);
    }

    @Bindable
    public Date getContractMaxDateFromNgayHieuLuc() {
        return contractMaxDateFromNgayHieuLuc;
    }

    public void setContractMaxDateFromNgayHieuLuc(Date contractMaxDateFromNgayHieuLuc) {
        this.contractMaxDateFromNgayHieuLuc = contractMaxDateFromNgayHieuLuc;
        notifyPropertyChanged(BR.contractMaxDateFromNgayHieuLuc);
    }

    @Bindable
    public Date getContractMaxDateToNgayHieuLuc() {
        return contractMaxDateToNgayHieuLuc;
    }

    public void setContractMaxDateToNgayHieuLuc(Date contractMaxDateToNgayHieuLuc) {
        this.contractMaxDateToNgayHieuLuc = contractMaxDateToNgayHieuLuc;
        notifyPropertyChanged(BR.contractMaxDateToNgayHieuLuc);
    }

    @Bindable
    public String getContractDateNgayKy() {
        return contractDateNgayKy;
    }

    public void setContractDateNgayKy(String contractDateNgayKy) {
        this.contractDateNgayKy = contractDateNgayKy;
        notifyPropertyChanged(BR.contractDateNgayKy);
    }

    @Bindable
    public String getContractDateFromNgayHieuLuc() {
        return contractDateFromNgayHieuLuc;
    }

    public void setContractDateFromNgayHieuLuc(String contractDateFromNgayHieuLuc) {
        this.contractDateFromNgayHieuLuc = contractDateFromNgayHieuLuc;
        notifyPropertyChanged(BR.contractDateFromNgayHieuLuc);
    }

    @Bindable
    public String getContractDateToNgayHieuLuc() {
        return contractDateToNgayHieuLuc;
    }

    public void setContractDateToNgayHieuLuc(String contractDateToNgayHieuLuc) {
        this.contractDateToNgayHieuLuc = contractDateToNgayHieuLuc;
        notifyPropertyChanged(BR.contractDateToNgayHieuLuc);
    }

    @Bindable
    public AddressApp getArea3() {
        return area3;
    }

    public void setArea3(AddressApp area3) {
        this.area3 = area3;
        notifyPropertyChanged(BR.area3);
    }
    //endregion Set - Get Fragment 3

    //endregion Set - Get
}
