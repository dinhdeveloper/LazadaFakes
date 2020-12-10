package tcd.project.seller.ui.fragment.export_product;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.model.ProductImportModel;

public interface FragmentExportProductViewInterface extends BaseViewInterface {

    void init(HomeActivity activity,FragmentExportProductViewCallback callback);

    void setDataList(ProductImportModel[] data);

    void resetListData();

    void clearDataInsert();
}
