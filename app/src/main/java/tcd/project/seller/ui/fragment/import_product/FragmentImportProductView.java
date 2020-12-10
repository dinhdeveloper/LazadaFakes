package tcd.project.seller.ui.fragment.import_product;

import android.text.TextUtils;
import android.widget.EditText;

import com.canhdinh.lib.edittext.FormattedEditText;
import com.canhdinh.lib.roundview.CurrencyEditText;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import b.laixuantam.myaarlibrary.widgets.roundview.RoundTextView;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.model.ProductImportModel;

public class FragmentImportProductView extends BaseView<FragmentImportProductView.UIContainer> implements FragmentImportProductViewInterface {
    HomeActivity activity;
    FragmentImportProductViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentImportProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        ui.btnSubmit.setOnClickListener(view -> {
            if (checkInput()) {
                ProductImportModel model = new ProductImportModel();
                model.setProduct_name(ui.edtNameProduct.getText().toString());
                model.setProduct_price(ui.edtPriceProduct.getStringValue());
                model.setQuantity_import(ui.edtQuantityProduct.getStringValue());
                if (!TextUtils.isEmpty(ui.edtSafeStock.getStringValue())) {
                    model.setSafe_stock(ui.edtSafeStock.getStringValue());
                }
                model.setEmployee_name(ui.edtNameCustomer.getText().toString());
                model.setEmployee_phone(ui.edtCustomerPhone.getRealText());

                if (callback!=null)
                    callback.createProductImport(model);
            }
        });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(ui.edtNameProduct.getText().toString().trim())) {
            ui.edtNameProduct.setError("Tên sản phẩm");
            return false;
        }
        if (TextUtils.isEmpty(ui.edtPriceProduct.getStringValue().trim())) {
            ui.edtPriceProduct.setError("Giá sản phẩm");
            return false;
        }
        if (TextUtils.isEmpty(ui.edtQuantityProduct.getStringValue().trim())) {
            ui.edtQuantityProduct.setError("Số lượng nhập");
            return false;
        }
        if (TextUtils.isEmpty(ui.edtNameCustomer.getText().toString().trim())) {
            ui.edtNameCustomer.setError("Người nhập");
            return false;
        }
        if (TextUtils.isEmpty(ui.edtCustomerPhone.getRealText().trim())) {
            ui.edtCustomerPhone.setError("SĐT người nhập");
            return false;
        }


        return true;
    }

    @Override
    public void clearDataInsert() {
        ui.edtNameProduct.requestFocus();
        ui.edtNameProduct.setText("");
        ui.edtPriceProduct.setText("");
        ui.edtQuantityProduct.setText("");
        ui.edtSafeStock.setText("");
        ui.edtNameCustomer.setText("");
        ui.edtCustomerPhone.setText("");
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentImportProductView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_import_product;
    }

    public class UIContainer extends BaseUiContainer {

        @UiElement(R.id.edtNameProduct)
        public EditText edtNameProduct;

        @UiElement(R.id.edtPriceProduct)
        public CurrencyEditText edtPriceProduct;

        @UiElement(R.id.edtQuantityProduct)
        public CurrencyEditText edtQuantityProduct;

        @UiElement(R.id.edtSafeStock)
        public CurrencyEditText edtSafeStock;

        @UiElement(R.id.edtNameCustomer)
        public EditText edtNameCustomer;

        @UiElement(R.id.edtCustomerPhone)
        public FormattedEditText edtCustomerPhone;

        @UiElement(R.id.btnSubmit)
        public RoundTextView btnSubmit;


    }
}
