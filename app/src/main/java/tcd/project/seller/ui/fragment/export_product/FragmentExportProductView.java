package tcd.project.seller.ui.fragment.export_product;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canhdinh.lib.edittext.FormattedEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import b.laixuantam.myaarlibrary.helper.AppUtils;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrClassicFrameLayout;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrDefaultHandler;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrFrameLayout;
import b.laixuantam.myaarlibrary.widgets.cptr.loadmore.OnLoadMoreListener;
import b.laixuantam.myaarlibrary.widgets.cptr.recyclerview.RecyclerAdapterWithHF;
import b.laixuantam.myaarlibrary.widgets.currencyedittext.CurrencyEditText;
import b.laixuantam.myaarlibrary.widgets.dialog.alert.KAlertDialog;
import b.laixuantam.myaarlibrary.widgets.roundview.RoundTextView;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.adapter.seller.ListProductImportAdapter;
import tcd.project.seller.adapter.seller.SelectProductImportAdapter;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.model.ProductImportModel;

public class FragmentExportProductView extends BaseView<FragmentExportProductView.UIContainer> implements FragmentExportProductViewInterface {
    HomeActivity activity;
    FragmentExportProductViewCallback callback;
    SelectProductImportAdapter productImportAdapter;
    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms
    private boolean isRefreshList = false;

    List<ProductImportModel> listDatas = new ArrayList<>();

    EditText edit_filter;
    RecyclerView recycler_view_list_product;
    View layoutEmptyList;
    TextView btnCancel;
    TextView btnOk;
    View popupView;
    LayoutInflater layoutInflater;

    String id_product_import;

    @Override
    public void init(HomeActivity activity, FragmentExportProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        onClickItemLayout();
    }

    String checkStatus = "0";

    private void onClickItemLayout() {
        ui.tvProductName.setOnClickListener(view -> {
            if (callback != null)
                callback.getListProduct();
        });

        ui.tvDangGiao.setOnClickListener(view -> {
            checkStatus = "0";
            ui.tvDangGiao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_stick_successfull, 0, 0, 0);
            ui.tvDaGiao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_stick__nosuccessfull_, 0, 0, 0);
        });

        ui.tvDaGiao.setOnClickListener(view -> {
            checkStatus = "1";
            ui.tvDaGiao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_stick_successfull, 0, 0, 0);
            ui.tvDangGiao.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_stick__nosuccessfull_, 0, 0, 0);
        });

        ui.btnSubmit.setOnClickListener(view -> {
            if (checkInput()) {
                ProductExportModel model = new ProductExportModel();
                if (!TextUtils.isEmpty(id_product_import)) {
                    model.setProduct_import_id(id_product_import);
                } else {
                    activity.showAlert("Vui lòng chọn sản phẩm cần xuất", KAlertDialog.WARNING_TYPE);
                }
                model.setProduct_name(ui.tvProductName.getText().toString());
                model.setQuantity_export(ui.edtProductQuantity.getStringValue());
                model.setCustomer_name(ui.edtCustomerName.getText().toString());
                if (!TextUtils.isEmpty(ui.edtCustomerPhone.getRealText())) {
                    model.setCustomer_phone(ui.edtCustomerPhone.getRealText());
                }
                if (!TextUtils.isEmpty(ui.edtCustomerAddress.getText().toString())) {
                    model.setCustomer_phone(ui.edtCustomerAddress.getText().toString());
                }
                model.setType_order(checkStatus);

                if (callback != null) {
                    callback.createProductExport(model);
                }

            }
        });
    }

    @Override
    public void clearDataInsert() {
        ui.tvProductName.setHint("Chọn sản phẩm");
        ui.tvProductName.setText("");
        ui.edtCustomerAddress.setText("");
        ui.edtCustomerPhone.setText("");
        ui.edtProductQuantity.setText("");
        ui.edtCustomerName.setText("");
        checkStatus = "0";
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(ui.tvProductName.getText().toString().trim())) {
            activity.showAlert("Vui lòng chọn sản phẩm cần xuất", KAlertDialog.WARNING_TYPE);
            return false;
        }
        if (TextUtils.isEmpty(ui.edtProductQuantity.getStringValue().trim())) {
            ui.edtProductQuantity.setError("Nhập số lượng");
            return false;
        }
        if (TextUtils.isEmpty(ui.edtCustomerName.getText().toString().trim())) {
            ui.edtCustomerName.setError("Nhập tên khách hàng");
            return false;
        }
        return true;
    }

    @Override
    public void setDataList(ProductImportModel[] result) {
        layoutInflater = activity.getLayoutInflater();
        popupView = layoutInflater.inflate(R.layout.dinh_custom_popup_choose_product, null);

        edit_filter = popupView.findViewById(R.id.edit_filter);
        recycler_view_list_product = popupView.findViewById(R.id.recycler_view_list);
        layoutEmptyList = popupView.findViewById(R.id.layoutEmptyList);
        btnCancel = popupView.findViewById(R.id.btnCancel);
        btnOk = popupView.findViewById(R.id.btnOk);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setView(popupView);
        AlertDialog dialogChoose = alert.create();
        dialogChoose.setCanceledOnTouchOutside(true);
        dialogChoose.show();

        btnCancel.setOnClickListener(view -> {
            dialogChoose.dismiss();
        });
        recycler_view_list_product.getRecycledViewPool().clear();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler_view_list_product.setLayoutManager(linearLayoutManager);

        //todo setup list with adapter
        listDatas.addAll(Arrays.asList(result));

        productImportAdapter = new SelectProductImportAdapter(getContext(), listDatas);

        recycler_view_list_product.setAdapter(productImportAdapter);

        productImportAdapter.setListener(model -> {
            btnOk.setOnClickListener(view -> {
                id_product_import = model.getImport_id();
                ui.tvProductName.setText(model.getProduct_name());
                dialogChoose.dismiss();
            });
            btnCancel.setOnClickListener(view -> {
                dialogChoose.dismiss();
            });
        });
    }

    @Override
    public void resetListData() {
        if (listDatas != null && productImportAdapter != null) {
            listDatas.clear();
            recycler_view_list_product.getRecycledViewPool().clear();
            productImportAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentExportProductView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_export_product;
    }


    public class UIContainer extends BaseUiContainer {
        @UiElement(R.id.tvProductName)
        public TextView tvProductName;

        @UiElement(R.id.edtProductQuantity)
        public CurrencyEditText edtProductQuantity;

        @UiElement(R.id.edtCustomerName)
        public EditText edtCustomerName;

        @UiElement(R.id.edtCustomerAddress)
        public EditText edtCustomerAddress;

        @UiElement(R.id.edtCustomerPhone)
        public FormattedEditText edtCustomerPhone;

        @UiElement(R.id.tvDangGiao)
        public TextView tvDangGiao;

        @UiElement(R.id.tvDaGiao)
        public TextView tvDaGiao;

        @UiElement(R.id.btnSubmit)
        public RoundTextView btnSubmit;


    }
}
