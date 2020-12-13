package tcd.project.seller.ui.fragment.list_base;

import tcd.project.seller.dialog.option.OptionModel;

public interface FragmentAdminManagerListBaseViewCallback {
    void onClickBackHeader();

    void refreshLoadingList();

    void onRequestLoadMoreList();

    void onRequestSearchWithFilter(String status, String key);

    void onItemListSelected(OptionModel item);

    void onClickAddItem();

    void onDeleteItemSelected(OptionModel item);

    void onClickFilter();

    void doLogout();
}
