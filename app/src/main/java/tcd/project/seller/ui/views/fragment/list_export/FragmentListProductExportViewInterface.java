package tcd.project.seller.ui.views.fragment.list_export;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.model.BaseResponseModel;

public interface FragmentListProductExportViewInterface extends BaseViewInterface {

    void init(HomeActivity activity, FragmentListProductExportViewCallback callback);

    void showEmptyList();

    void hideEmptyList();

    void setDataList(BaseResponseModel dataList);

    void setNoMoreLoading();

    void resetListData();

    void hideRootView();

    void showRootView();

    void clearListData();
}
