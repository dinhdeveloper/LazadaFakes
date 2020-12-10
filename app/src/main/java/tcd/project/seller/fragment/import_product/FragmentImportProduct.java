package tcd.project.seller.fragment.import_product;

import android.text.TextUtils;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.widgets.dialog.alert.KAlertDialog;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.api.seller.RequestCreateProductImport;
import tcd.project.seller.dependency.AppProvider;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductImportModel;
import tcd.project.seller.ui.fragment.import_product.FragmentImportProductView;
import tcd.project.seller.ui.fragment.import_product.FragmentImportProductViewCallback;
import tcd.project.seller.ui.fragment.import_product.FragmentImportProductViewInterface;

public class FragmentImportProduct  extends BaseFragment<FragmentImportProductViewInterface, BaseParameters> implements FragmentImportProductViewCallback {
    HomeActivity activity;

    @Override
    protected void initialize() {
        activity = (HomeActivity)getActivity();
        view.init(activity,this);
    }

    @Override
    public void createProductImport(ProductImportModel model) {
        if (!AppProvider.getConnectivityHelper().hasInternetConnection()) {
            showAlert(getContext().getResources().getString(R.string.error_internet_connection), KAlertDialog.ERROR_TYPE);
            return;
        }

        showProgress();
        RequestCreateProductImport.ApiParams params = new RequestCreateProductImport.ApiParams();
        params.store_id = "CD001";
        params.product_name = model.getProduct_name();
        params.product_price = model.getProduct_price();
        params.quantity_import = model.getQuantity_import();
        if (!TextUtils.isEmpty(model.getSafe_stock())) {
            params.safe_stock = model.getSafe_stock();
        }
        params.employee_name = model.getEmployee_name();
        params.employee_phone = model.getEmployee_phone();

        AppProvider.getApiManagement().call(RequestCreateProductImport.class, params, new ApiRequest.ApiCallback<BaseResponseModel>() {
            @Override
            public void onSuccess(BaseResponseModel body) {
                dismissProgress();
                if (!TextUtils.isEmpty(body.getSuccess()) && body.getSuccess().equalsIgnoreCase("true")) {
                    showAlert(body.getMessage(),KAlertDialog.SUCCESS_TYPE);
                    view.clearDataInsert();
                }else {
                    showAlert(body.getMessage(),KAlertDialog.ERROR_TYPE);
                }
            }

            @Override
            public void onError(ErrorApiResponse error) {
                dismissProgress();
                showAlert("Lỗi không tải được dữ liệu",KAlertDialog.ERROR_TYPE);
            }

            @Override
            public void onFail(ApiRequest.RequestError error) {
                dismissProgress();
                showAlert("Lỗi không tải được dữ liệu",KAlertDialog.ERROR_TYPE);
            }
        });
    }

    @Override
    protected FragmentImportProductViewInterface getViewInstance() {
        return new FragmentImportProductView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
