package tcd.project.seller.fragment.import_product;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.ui.fragment.import_product.FragmentImportProductView;
import tcd.project.seller.ui.fragment.import_product.FragmentImportProductViewCallback;
import tcd.project.seller.ui.fragment.import_product.FragmentImportProductViewInterface;

public class FragmentImportProduct  extends BaseFragment<FragmentImportProductViewInterface, BaseParameters> implements FragmentImportProductViewCallback {
    HomeActivity activity;

    @Override
    protected void initialize() {
        activity = (HomeActivity)getActivity();
        view.init(activity,this);
    }

    @Override
    protected FragmentImportProductViewInterface getViewInstance() {
        return new FragmentImportProductView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
