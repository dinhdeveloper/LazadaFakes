package tcd.project.seller.api.seller;

import androidx.annotation.Nullable;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.BaseApiParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcd.project.seller.helper.Consts;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductImportModel;

@ApiRequest.ApiName("create_product_import")
public class RequestCreateProductImport extends ApiRequest<RequestCreateProductImport.Service, BaseResponseModel<ProductImportModel>, RequestCreateProductImport.ApiParams> {

    public RequestCreateProductImport() {
        super(RequestCreateProductImport.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<ProductImportModel> result) {

    }

    @Override
    protected Call<BaseResponseModel<ProductImportModel>> call(RequestCreateProductImport.ApiParams params) {
        params.detect = "create_product_import";
        return getService().call(params);
    }

    interface Service {
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<ProductImportModel>> call(@Body RequestCreateProductImport.ApiParams params);
    }

    public static class ApiParams extends BaseApiParams {
        private String detect;
        public String store_id;
        public String product_name;
        public String product_price ;
        public String quantity_import;
        public String safe_stock;
        public String employee_name;
        public String employee_phone;
        @Nullable
        public String page;

        @Nullable
        public String limit;
    }
}