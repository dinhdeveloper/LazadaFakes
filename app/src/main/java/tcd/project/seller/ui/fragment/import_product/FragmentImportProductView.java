package tcd.project.seller.ui.fragment.import_product;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;

public class FragmentImportProductView extends BaseView<FragmentImportProductView.UIContainer> implements FragmentImportProductViewInterface {
    HomeActivity activity;
    FragmentImportProductViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentImportProductViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentImportProductView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_import_product;
    }

    public class UIContainer extends BaseUiContainer {
    }
}
