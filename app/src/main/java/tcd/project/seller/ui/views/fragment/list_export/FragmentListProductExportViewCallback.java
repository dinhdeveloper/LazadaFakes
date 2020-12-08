package tcd.project.seller.ui.views.fragment.list_export;


import tcd.project.seller.dialog.option.OptionModel;

public interface FragmentListProductExportViewCallback {
    void onClickBackHeader();

    void refreshLoadingList();

    void onRequestLoadMoreList();

    void onRequestSearchOrFilter(String key);

    void onItemListSelected(OptionModel item);

    void onClickAddItem();

    void onDeleteItemSelected(OptionModel item);

    void onClickFilter();

    void onClickMore(OptionModel item);
}
