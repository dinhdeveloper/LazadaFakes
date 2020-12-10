package tcd.project.seller.api.seller;

import androidx.annotation.Nullable;

import b.laixuantam.myaarlibrary.api.ApiRequest;
import b.laixuantam.myaarlibrary.api.BaseApiParams;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tcd.project.seller.helper.Consts;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;

@ApiRequest.ApiName("create_product_export")
public class RequestCreateProductExport extends ApiRequest<RequestCreateProductExport.Service, BaseResponseModel<ProductExportModel>, RequestCreateProductExport.ApiParams> {

    public RequestCreateProductExport() {
        super(RequestCreateProductExport.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<ProductExportModel> result) {

    }

    @Override
    protected Call<BaseResponseModel<ProductExportModel>> call(RequestCreateProductExport.ApiParams params) {
        params.detect = "create_product_export";
        return getService().call(params);
    }

    interface Service {
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<ProductExportModel>> call(@Body RequestCreateProductExport.ApiParams params);
    }

    public static class ApiParams extends BaseApiParams {
        private String detect;
        public String store_id;
        public String product_name;
        public String product_id ;
        public String quantity_export;
        public String customer_name;
        public String customer_phone;
        public String customer_address;
        public String product_type;
        @Nullable
        public String page;

        @Nullable
        public String limit;
    }
}