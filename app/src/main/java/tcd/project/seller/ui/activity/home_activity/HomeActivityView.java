package tcd.project.seller.ui.activity.home_activity;

import android.graphics.PointF;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

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

        configFloatActionBar();

    }

    private void configFloatActionBar() {
        ui.bubbleTabBar.addBubbleListener(i -> {
            switch (i) {
                case R.id.list_export:
                    if (callback != null)
                        callback.changeToFragmentListExport();
                    break;
                case R.id.list_import:
                    if (callback != null)
                        callback.changeToFragmentListImport();
                    break;
                case R.id.import_product:
                    if (callback != null)
                        callback.changeToFragmentImportProduct();
                    break;
                case R.id.export_product:
                    if (callback != null)
                        callback.changeToFragmentExportProduct();
                    break;
                default:
                    if (callback != null)
                        callback.changeToFragmentListExport();
                    break;
            }
        });

//        ui.bmb2.setDuration(1009);
//        for (int i = 0; i < ui.bmb2.getPiecePlaceEnum().pieceNumber(); i++)
//            ui.bmb2.addBuilder(BuilderManager.getHamButtonBuilderWithDifferentPieceColor());
//        ui.bmb2.setOnBoomListener(new OnBoomListener() {
//            @Override
//            public void onClicked(int index, BoomButton boomButton) {
//                switch (index) {
//                    case 0:
//                        if (callback != null)
//                            callback.changeToFragmentListExport();
//                        break;
//                    case 1:
//                        if (callback != null)
//                            callback.changeToFragmentListImport();
//                        break;
//                    case 2:
//                        if (callback != null)
//                            callback.changeToFragmentExportProduct();
//                        break;
//                    case 3:
//                        if (callback != null)
//                            callback.changeToFragmentImportProduct();
//                        break;
//                    case 4:
//                        if (callback != null)
//                            callback.changeToFragmentSettingProfile();
//                        break;
//                }
//            }
//
//            @Override
//            public void onBackgroundClick() {
//
//            }
//
//            @Override
//            public void onBoomWillHide() {
//
//            }
//
//            @Override
//            public void onBoomDidHide() {
//
//            }
//
//            @Override
//            public void onBoomWillShow() {
//
//            }
//
//            @Override
//            public void onBoomDidShow() {
//
//            }
//        });
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
        @UiElement(R.id.bubbleTabBar)
        BubbleTabBar bubbleTabBar = (BubbleTabBar) findViewById(R.id.bubbleTabBar);
    }
}
