package tcd.project.seller.ui.activity.home_activity;

import android.widget.FrameLayout;

import com.nightonke.boommenu.BoomMenuButton;

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
//        @UiElement(R.id.bmb)
//        BoomMenuButton menuButton;
    }
}
