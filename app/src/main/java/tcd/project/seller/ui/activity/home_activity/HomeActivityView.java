package tcd.project.seller.ui.activity.home_activity;

import android.widget.FrameLayout;

import com.nightonke.boommenu.BoomMenuButton;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import tcd.project.seller.R;
import tcd.project.seller.activity.BuilderManager;
import tcd.project.seller.activity.HomeActivity;

public class HomeActivityView extends BaseView<HomeActivityView.UIContainer> implements HomeActivityViewInterface {
    HomeActivity activity;
    HomeActivityViewCallback callback;

    @Override
    public void init(HomeActivity activity, HomeActivityViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        ui.bmb2.setDuration(1000);
        for (int i = 0; i < ui.bmb2.getPiecePlaceEnum().pieceNumber(); i++)
            ui.bmb2.addBuilder(BuilderManager.getHamButtonBuilderWithDifferentPieceColor());
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.dinh_admin_layout_activity_home;
    }

    public class UIContainer extends BaseUiContainer {
        @UiElement(R.id.bmb2)
        BoomMenuButton bmb2 = (BoomMenuButton)findViewById(R.id.bmb2);
    }
}
