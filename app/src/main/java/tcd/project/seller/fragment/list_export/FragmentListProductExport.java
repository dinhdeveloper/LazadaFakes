package tcd.project.seller.fragment.list_export;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.ErrorApiResponse;
import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.widgets.dialog.alert.KAlertDialog;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.api.seller.RequestListProductExport;
import tcd.project.seller.api.seller.RequestUpdateProductExport;
import tcd.project.seller.dependency.AppProvider;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.event.FragmentBackFilterEvent;
import tcd.project.seller.event.admin.UpdateOrderStatusEvent;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.ui.fragment.list_base.FragmentAdminManagerListBaseViewCallback;
import tcd.project.seller.ui.fragment.list_base.FragmentAdminManagerListBaseViewInterface;
import tcd.project.seller.ui.fragment.list_export.FragmentListProductExportView;

public class FragmentListProductExport extends BaseFragment<FragmentAdminManagerListBaseViewInterface, BaseParameters> implements FragmentAdminManagerListBaseViewCallback {
    HomeActivity activity;
    private int page = 1;
    private int totalPage = 0;

    @Override
    protected void initialize() {
        activity = (HomeActivity) getActivity();
        view.init(activity, this);

        requestDataListProductExport();
    }

    @Override
    protected FragmentAdminManagerListBaseViewInterface getViewInstance() {
        return new FragmentListProductExportView();
    }

    @Override
    public void doLogout() {
        showConfirmAlert("Đăng xuất", "Bạn có muốn đăng xuất tài khoản?", kAlertDialog -> {
            kAlertDialog.dismiss();
            AppProvider.getPreferences().clear();
            AppProvider.getPreferences().saveUserModel(null);
            AppProvider.getPreferences().saveStatusLogin(false);
            AppProvider.getPreferences().saveFirstInstall(false);
//            Intent intent = new Intent(AdminActivity.this, CustomerReviewActivity.class);
//            startActivity(intent);
//            finish();
        }, Dialog::dismiss, -1);
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
    public void onItemListSelected(OptionModel item) {
        ProductExportModel model = (ProductExportModel) item.getDtaCustom();
        String title = "Cập nhật";
        String message = "Bạn có muốn cập nhật trạng thái giao hàng?";
        //String title, String mess, String titleButtonConfirm, String titleButtonCancel, KAlertDialog.KAlertClickListener actionConfirm, KAlertDialog.KAlertClickListener actionCancel, int type
        activity.showConfirmAlert(title, message, "Đồng ý","Từ chối",new KAlertDialog.KAlertClickListener() {
            @Override
            public void onClick(KAlertDialog kAlertDialog) {
                //confirm
                kAlertDialog.dismiss();
                //request active or lock account
                updateStatusTransport(model);
            }
        }, new KAlertDialog.KAlertClickListener() {
            @Override
            public void onClick(KAlertDialog kAlertDialog) {
                //cancel
                kAlertDialog.dismiss();
            }
        }, KAlertDialog.WARNING_TYPE);
    }

    private void updateStatusTransport(ProductExportModel model) {
        final KAlertDialog mCustomAlert = new KAlertDialog(getContext());
        mCustomAlert.setContentText("Đang xử lý...")
                .showCancelButton(false)
                .setCancelClickListener(null)
                .changeAlertType(KAlertDialog.PROGRESS_TYPE);

        mCustomAlert.setCancelable(false);
        mCustomAlert.setCanceledOnTouchOutside(false);
        mCustomAlert.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!AppProvider.getConnectivityHelper().hasInternetConnection()) {
                    showAlert(getContext().getResources().getString(R.string.error_internet_connection), KAlertDialog.ERROR_TYPE);
                    return;
                }

                showProgress();

                RequestUpdateProductExport.ApiParams params = new RequestUpdateProductExport.ApiParams();
                params.store_id = "CD001";
                params.product_id = model.getExport_id();
                AppProvider.getApiManagement().call(RequestUpdateProductExport.class, params, new ApiRequest.ApiCallback<BaseResponseModel>() {
                    @Override
                    public void onSuccess(BaseResponseModel body) {
                        dismissProgress();
                        mCustomAlert.setContentText(body.getMessage())
                                .setConfirmText("OK")
                                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                                    @Override
                                    public void onClick(KAlertDialog kAlertDialog) {
                                        mCustomAlert.dismiss();
                                        if (activity != null) {
                                            UpdateOrderStatusEvent.post();
                                        }
                                    }
                                })
                                .changeAlertType(KAlertDialog.SUCCESS_TYPE);
                    }

                    @Override
                    public void onError(ErrorApiResponse error) {
                        dismissProgress();
                        showAlert("Không tải được dữ liệu", KAlertDialog.ERROR_TYPE);
                    }

                    @Override
                    public void onFail(ApiRequest.RequestError error) {
                        dismissProgress();
                        showAlert("Không tải được dữ liệu", KAlertDialog.ERROR_TYPE);
                    }
                });

            }
        }, 500);
    }

    @Override
    public void onClickAddItem() {

    }

    @Override
    public void onDeleteItemSelected(OptionModel item) {

    }

    @Override
    public void onClickFilter() {
        if (activity != null) {
            activity.changeToFragmentFilter("export");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.hideRootView();
                }
            }, 700);
        }
    }

    @Override
    public void onRequestSearchWithFilter(String status, String key) {

    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateOrderStatusEvent(UpdateOrderStatusEvent event) {
        if (view != null) {
            view.resetListData();
            requestDataListProductExport();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentBackFilterEvent(FragmentBackFilterEvent event) {
        if (view != null)
            view.showRootView();
    }

}
