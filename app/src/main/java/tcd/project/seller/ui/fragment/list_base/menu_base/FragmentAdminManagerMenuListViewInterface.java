package tcd.project.seller.ui.fragment.list_base.menu_base;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;
import tcd.project.seller.activity.HomeActivity;

public interface FragmentAdminManagerMenuListViewInterface extends BaseViewInterface {

    void init(HomeActivity activity, FragmentAdminManagerMenuListViewCallback callback);

    void hideRootView();

    void showRootView();
}
