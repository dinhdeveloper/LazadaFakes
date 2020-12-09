package tcd.project.seller.fragment.export_product;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.ui.fragment.export_product.FragmentExportProductView;
import tcd.project.seller.ui.fragment.export_product.FragmentExportProductViewCallback;
import tcd.project.seller.ui.fragment.export_product.FragmentExportProductViewInterface;

public class FragmentExportProduct extends BaseFragment<FragmentExportProductViewInterface, BaseParameters> implements FragmentExportProductViewCallback {
    HomeActivity activity;

    @Override
    protected void initialize() {
        activity = (HomeActivity) getActivity();
        view.init(activity, this);
    }

    @Override
    protected FragmentExportProductViewInterface getViewInstance() {
        return new FragmentExportProductView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
