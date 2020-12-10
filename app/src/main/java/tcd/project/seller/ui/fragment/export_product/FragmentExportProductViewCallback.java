package tcd.project.seller.ui.fragment.export_product;

import tcd.project.seller.model.ProductExportModel;

public interface FragmentExportProductViewCallback {
    void getListProduct();

    void refreshLoadingList();

    void onRequestLoadMoreList();

    void createProductExport(ProductExportModel model);
}
