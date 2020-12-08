package tcd.project.seller.fragment.list_export;

import android.text.TextUtils;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.widgets.dialog.alert.KAlertDialog;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.api.seller.RequestListProductExport;
import tcd.project.seller.dependency.AppProvider;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.ui.views.fragment.list_export.FragmentListProductExportView;
import tcd.project.seller.ui.views.fragment.list_export.FragmentListProductExportViewCallback;
import tcd.project.seller.ui.views.fragment.list_export.FragmentListProductExportViewInterface;

public class FragmentListProductExport extends BaseFragment<FragmentListProductExportViewInterface, BaseParameters> implements FragmentListProductExportViewCallback {
    HomeActivity activity;
    private int page = 1;
    private int totalPage = 0;

    @Override
    protected void initialize() {
        activity = (HomeActivity)getActivity();
        view.init(activity,this);

        requestDataListProductExport();
    }
    @Override
    protected FragmentListProductExportViewInterface getViewInstance() {
        return new FragmentListProductExportView();
    }

    private void requestDataListProductExport() {
        if (!AppProvider.getConnectivityHelper().hasInternetConnection()) {
            showAlert(getContext().getResources().getString(R.string.error_internet_connection), KAlertDialog.ERROR_TYPE);
            view.setDataList(null);
            return;
        }

        showProgress();

        RequestListProductExport.ApiParams params = new RequestListProductExport.ApiParams();
        params.store_id = "CD001";
        params.page = String.valueOf(page);
        params.limit = "20";

        AppProvider.getApiManagement().call(RequestListProductExport.class, params, new ApiRequest.ApiCallback<BaseResponseModel<ProductExportModel>>() {
            @Override
            public void onSuccess(BaseResponseModel<ProductExportModel> result) {

                dismissProgress();
                if (!TextUtils.isEmpty(result.getSuccess()) && result.getSuccess().equalsIgnoreCase("true")) {

                    if (!TextUtils.isEmpty(result.getTotal_page())) {
                        totalPage = Integer.valueOf(result.getTotal_page());
                        if (page == totalPage) {
                            view.setNoMoreLoading();
                        }
                    } else {
                        view.setNoMoreLoading();
                    }
                    view.setDataList(result);
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
    public void onClickBackHeader() {
    }

    @Override
    public void refreshLoadingList() {
        page = 1;
        totalPage = 0;
        view.resetListData();
        requestDataListProductExport();
    }

    @Override
    public void onRequestLoadMoreList() {
        ++page;

        if (totalPage > 0 && page <= totalPage) {

            requestDataListProductExport();
        } else {
            view.setNoMoreLoading();
            showToast(getString(R.string.error_loadmore));
        }
    }

    @Override
    public void onRequestSearchOrFilter(String key) {

    }

    @Override
    public void onItemListSelected(OptionModel item) {

    }

    @Override
    public void onClickAddItem() {

    }

    @Override
    public void onDeleteItemSelected(OptionModel item) {

    }

    @Override
    public void onClickFilter() {

    }

    @Override
    public void onClickMore(OptionModel item) {

    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
