package tcd.project.seller.fragment.list_import;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.ui.views.fragment.list_import.FragmentListProductImportView;
import tcd.project.seller.ui.views.fragment.list_import.FragmentListProductImportViewCallback;
import tcd.project.seller.ui.views.fragment.list_import.FragmentListProductImportViewInterface;

public class FragmentListProductImport extends BaseFragment<FragmentListProductImportViewInterface, BaseParameters> implements FragmentListProductImportViewCallback {
    HomeActivity activity;

    @Override
    protected void initialize() {
        activity = (HomeActivity)getActivity();
        view.init(activity,this);
    }

    @Override
    protected FragmentListProductImportViewInterface getViewInstance() {
        return new FragmentListProductImportView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
