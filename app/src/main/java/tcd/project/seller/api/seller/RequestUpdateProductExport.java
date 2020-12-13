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

@ApiRequest.ApiName("lazada_update_status_export")
public class RequestUpdateProductExport extends ApiRequest<RequestUpdateProductExport.Service, BaseResponseModel<ProductImportModel>, RequestUpdateProductExport.ApiParams> {

    public RequestUpdateProductExport() {
        super(RequestUpdateProductExport.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<ProductImportModel> result) {

    }

    @Override
    protected Call<BaseResponseModel<ProductImportModel>> call(RequestUpdateProductExport.ApiParams params) {
        params.detect = "lazada_update_status_export";
        return getService().call(params);
    }

    interface Service {
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<ProductImportModel>> call(@Body RequestUpdateProductExport.ApiParams params);
    }

    public static class ApiParams extends BaseApiParams {
        private String detect;
        public String store_id;
        public String product_id;
        @Nullable
        public String page;

        @Nullable
        public String limit;
    }
}