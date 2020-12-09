package tcd.project.seller.ui.fragment.export_product;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;

public class FragmentExportProductView extends BaseView<FragmentExportProductView.UIContainer> implements FragmentExportProductViewInterface{
    HomeActivity activity;
    FragmentExportProductViewCallback callback;
    @Override
    public void init(HomeActivity activity, FragmentExportProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }
    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentExportProductView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_export_product;
    }



    public class UIContainer extends BaseUiContainer {
    }
}
