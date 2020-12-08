package tcd.project.seller.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;

import b.laixuantam.myaarlibrary.base.BaseFragmentActivity;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import b.laixuantam.myaarlibrary.helper.OnKeyboardVisibilityListener;
import tcd.project.seller.R;
import tcd.project.seller.fragment.list_export.FragmentListProductExport;
import tcd.project.seller.ui.views.action_bar.base_main_actionbar.BaseMainActionbarViewCallback;
import tcd.project.seller.ui.views.action_bar.base_main_actionbar.BaseMainActionbarViewInterface;
import tcd.project.seller.ui.views.activity.home_activity.HomeActivityView;
import tcd.project.seller.ui.views.activity.home_activity.HomeActivityViewCallback;
import tcd.project.seller.ui.views.activity.home_activity.HomeActivityViewInterface;

public class HomeActivity extends BaseFragmentActivity<HomeActivityViewInterface, BaseMainActionbarViewInterface, BaseParameters> implements BaseMainActionbarViewCallback, HomeActivityViewCallback, ActivityCompat.OnRequestPermissionsResultCallback, OnKeyboardVisibilityListener {
    private int isShowContainer = 0;
    @Override
    protected void initialize(Bundle savedInstanceState) {
        view.init(this, this);
        changeToFragmentListExport();
    }

    private void changeToFragmentListExport() {
        isShowContainer++;
        replaceFragment(new FragmentListProductExport(), false, Animation.SLIDE_IN_OUT);
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
        return R.id.fragmentAdminDashboard;
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
