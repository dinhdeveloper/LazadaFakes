package tcd.project.seller.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import b.laixuantam.myaarlibrary.base.BaseFragmentActivity;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.helper.OnKeyboardVisibilityListener;
import tcd.project.seller.R;
import tcd.project.seller.fragment.export_product.FragmentExportProduct;
import tcd.project.seller.fragment.import_product.FragmentImportProduct;
import tcd.project.seller.fragment.list_base.FragmentListBase;
import tcd.project.seller.fragment.list_export.FragmentListProductExport;
import tcd.project.seller.fragment.list_import.FragmentListProductImport;
import tcd.project.seller.ui.action_bar.base_main_actionbar.BaseMainActionbarViewCallback;
import tcd.project.seller.ui.action_bar.base_main_actionbar.BaseMainActionbarViewInterface;
import tcd.project.seller.ui.activity.home_activity.HomeActivityView;
import tcd.project.seller.ui.activity.home_activity.HomeActivityViewCallback;
import tcd.project.seller.ui.activity.home_activity.HomeActivityViewInterface;

public class HomeActivity extends BaseFragmentActivity<HomeActivityViewInterface, BaseMainActionbarViewInterface, BaseParameters> implements BaseMainActionbarViewCallback, HomeActivityViewCallback, ActivityCompat.OnRequestPermissionsResultCallback, OnKeyboardVisibilityListener {
    private int isShowContainer = 0;
    HomeActivity activity;

    @Override
    protected void initialize(Bundle savedInstanceState) {
        activity = HomeActivity.this;
        view.init(activity, this);
        changeToFragmentListExport();
    }

    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    @Override
    public void changeToFragmentListExport() {
        isShowContainer = 0;
        replaceFragment(new FragmentListProductExport(), true, Animation.SLIDE_IN_OUT);
    }

    @Override
    public void changeToFragmentListImport() {
        isShowContainer++;
        replaceFragment(new FragmentListProductImport(), true, Animation.SLIDE_IN_OUT);
    }

    @Override
    public void changeToFragmentExportProduct() {
        isShowContainer ++;
        replaceFragment(new FragmentExportProduct(), true, Animation.SLIDE_IN_OUT);
    }

    @Override
    public void changeToFragmentImportProduct() {
        isShowContainer ++;
        replaceFragment(new FragmentImportProduct(), true, Animation.SLIDE_IN_OUT);
    }

    public void checkBack() {

        if (isShowContainer > 0) {
            isShowContainer--;
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            }

            if (isShowContainer == 0) {
//                if (listProductOrder != null) {
//                    int countItemOrder = countOrderProduct();
//                    if (countItemOrder > 0) {
//                        view.setBadgeCart(countItemOrder);
//                    }
//
//
//                }
            }

        } else {
            checkFragment();
        }
    }

    public void doLogout() {
//        showConfirmAlert("Đăng xuất", "Bạn có muốn đăng xuất tài khoản?", kAlertDialog -> {
//            kAlertDialog.dismiss();
//            AppProvider.getPreferences().clear();
//            AppProvider.getPreferences().saveUserModel(null);
//            AppProvider.getPreferences().saveStatusLogin(false);
//            AppProvider.getPreferences().saveFirstInstall(false);
//            Intent intent = new Intent(HomeActivity.this, CustomerReviewActivity.class);
//            startActivity(intent);
//            finish();
//        }, Dialog::dismiss, -1);
    }

    private void checkFragment() {

        FragmentManager fm = getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected HomeActivityViewInterface getViewInstance() {
        return new HomeActivityView();
    }

    @Override
    protected BaseMainActionbarViewInterface getActionbarInstance() {
        return null;
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content_frame;
    }

    @Override
    public void onVisibilityChanged(boolean visible) {

    }

    @Override
    public void onFilterToggle(boolean showFilter) {

    }

    @Override
    public void onFiltering(String keyword) {

    }

    @Override
    public void onClickButtonLeftActionbar() {

    }

    @Override
    public void onClickButtonRightActionbar() {

    }
}
