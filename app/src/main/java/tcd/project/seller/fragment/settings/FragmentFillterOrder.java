package tcd.project.seller.fragment.settings;

import android.os.Bundle;

import b.laixuantam.myaarlibrary.base.BaseFragment;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.event.FragmentBackFilterEvent;
import tcd.project.seller.ui.fragment.settings.FragmentFillterOrderView;
import tcd.project.seller.ui.fragment.settings.FragmentFillterOrderViewCallback;
import tcd.project.seller.ui.fragment.settings.FragmentFillterOrderViewInterface;

public class FragmentFillterOrder extends BaseFragment<FragmentFillterOrderViewInterface, BaseParameters> implements FragmentFillterOrderViewCallback {
    HomeActivity activity;

    public static FragmentFillterOrder newInstance(String type) {

        Bundle args = new Bundle();
        args.putString("type", type);
        FragmentFillterOrder fragment = new FragmentFillterOrder();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initialize() {
        activity = (HomeActivity) getActivity();
        view.init(activity, this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String type = (String) bundle.getString("type");

        }
    }

    @Override
    public void onBackHeader() {
        if (activity != null)
            activity.checkBack();
            FragmentBackFilterEvent.post();
    }

    @Override
    public void filterDataCalendar(String thang, String nam) {

    }

    @Override
    protected FragmentFillterOrderViewInterface getViewInstance() {
        return new FragmentFillterOrderView();
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }
}
