package tcd.project.seller.fragment.export_product;

import android.text.TextUtils;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.widgets.dialog.alert.KAlertDialog;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.api.seller.RequestCreateProductExport;
import tcd.project.seller.api.seller.RequestCreateProductImport;
import tcd.project.seller.api.seller.RequestListProductImport;
import tcd.project.seller.dependency.AppProvider;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.model.ProductImportModel;
import tcd.project.seller.ui.fragment.export_product.FragmentExportProductView;
import tcd.project.seller.ui.fragment.export_product.FragmentExportProductViewCallback;
import tcd.project.seller.ui.fragment.export_product.FragmentExportProductViewInterface;

public class FragmentExportProduct extends BaseFragment<FragmentExportProductViewInterface, BaseParameters> implements FragmentExportProductViewCallback {
    HomeActivity activity;
    private int page = 1;
    private int totalPage = 0;

    @Override
    protected void initialize() {
        activity = (HomeActivity) getActivity();
        view.init(activity, this);
    }

    @Override
    public void createProductExport(ProductExportModel model) {
        if (!AppProvider.getConnectivityHelper().hasInternetConnection()) {
            showAlert(getContext().getResources().getString(R.string.error_internet_connection), KAlertDialog.ERROR_TYPE);
            return;
        }

        showProgress();
        RequestCreateProductExport.ApiParams params = new RequestCreateProductExport.ApiParams();
        params.store_id = "CD001";
        params.product_name = model.getProduct_name();
        params.product_id = model.getProduct_import_id();
        params.quantity_export = model.getQuantity_export();
        if (!TextUtils.isEmpty(model.getCustomer_address())) {
            params.customer_address = model.getCustomer_address();
        }
        if (!TextUtils.isEmpty(model.getCustomer_phone())) {
            params.customer_phone = model.getCustomer_phone();
        }

        params.customer_name = model.getCustomer_name();

        AppProvider.getApiManagement().call(RequestCreateProductExport.class, params, new ApiRequest.ApiCallback<BaseResponseModel>() {
            @Override
            public void onSuccess(BaseResponseModel body) {
                dismissProgress();
                if (!TextUtils.isEmpty(body.getSuccess()) && body.getSuccess().equalsIgnoreCase("true")) {
                    showAlert(body.getMessage(), KAlertDialog.SUCCESS_TYPE);
                    view.clearDataInsert();
                } else {
                    showAlert(body.getMessage(), KAlertDialog.ERROR_TYPE);
                }
            }

            @Override
            public void onError(ErrorApiResponse error) {
                dismissProgress();
                showAlert("Lỗi không tải được dữ liệu", KAlertDialog.ERROR_TYPE);
            }

            @Override
            public void onFail(ApiRequest.RequestError error) {
                dismissProgress();
                showAlert("Lỗi không tải được dữ liệu", KAlertDialog.ERROR_TYPE);
            }
        });
    }

    @Override
    public void getListProduct() {
        if (!AppProvider.getConnectivityHelper().hasInternetConnection()) {
            showAlert(getContext().getResources().getString(R.string.error_internet_connection), KAlertDialog.ERROR_TYPE);
            view.setDataList(null);
            return;
        }

        showProgress();
        view.resetListData();
        RequestListProductImport.ApiParams params = new RequestListProductImport.ApiParams();
        params.store_id = "CD001";
        params.page = String.valueOf(page);
        params.limit = "20";

        AppProvider.getApiManagement().call(RequestListProductImport.class, params, new ApiRequest.ApiCallback<BaseResponseModel<ProductImportModel>>() {
            @Override
            public void onSuccess(BaseResponseModel<ProductImportModel> result) {

                dismissProgress();
                if (!TextUtils.isEmpty(result.getSuccess()) && result.getSuccess().equalsIgnoreCase("true")) {
//                    if (!TextUtils.isEmpty(result.getTotal_page())) {
//                        totalPage = Integer.valueOf(result.getTotal_page());
//                        if (page == totalPage) {
//                            view.setNoMoreLoading();
//                        }
//                    } else {
//                        view.setNoMoreLoading();
//                    }
                    view.setDataList(result.getData());
                } else {
                    if (!TextUtils.isEmpty(result.getMessage()))
                        showAlert(result.getMessage(), KAlertDialog.ERROR_TYPE);
                    else
                        showAlert("Không thể tải dữ liệu.", KAlertDialog.ERROR_TYPE);
                }

            }

            @Override
            public void onError(ErrorApiResponse error) {
                dismissProgress();
                showAlert("Không thể tải dữ liệu.", KAlertDialog.ERROR_TYPE);
            }

            @Override
            public void onFail(ApiRequest.RequestError error) {
                dismissProgress();
                showAlert("Không thể tải dữ liệu.", KAlertDialog.ERROR_TYPE);
            }
        });
    }

    @Override
    public void refreshLoadingList() {
        page = 1;
        totalPage = 0;
        getListProduct();
    }

    @Override
    public void onRequestLoadMoreList() {
        ++page;

        if (totalPage > 0 && page <= totalPage) {

            getListProduct();
        } else {
            showToast(getString(R.string.error_loadmore));
        }
    }

    @Override
    protected FragmentExportProductViewInterface getViewInstance() {
        return new FragmentExportProductView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
