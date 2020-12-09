package tcd.project.seller.ui.fragment.list_base;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.model.BaseResponseModel;

public interface FragmentAdminManagerListBaseViewInterface extends BaseViewInterface {

    void init(HomeActivity activity, FragmentAdminManagerListBaseViewCallback callback);

    void showEmptyList();

    void hideEmptyList();

    void setDataList(BaseResponseModel dataList);

    void setNoMoreLoading();

    void resetListData();

    void hideRootView();

    void showRootView();

    void clearListData();
}
