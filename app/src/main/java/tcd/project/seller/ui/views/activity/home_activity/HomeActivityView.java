package tcd.project.seller.ui.views.activity.home_activity;

import android.widget.FrameLayout;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;

public class HomeActivityView extends BaseView<HomeActivityView.UIContainer> implements HomeActivityViewInterface {
    HomeActivity activity;
    HomeActivityViewCallback callback;

    @Override
    public void init(HomeActivity activity, HomeActivityViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.dinh_admin_layout_activity_home;
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.fragmentAdminDashboard)
        FrameLayout fragmentAdminDashboard;
    }
}
