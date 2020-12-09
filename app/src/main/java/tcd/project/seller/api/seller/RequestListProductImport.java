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

@ApiRequest.ApiName("lazada_list_import")
public class RequestListProductImport extends ApiRequest<RequestListProductImport.Service, BaseResponseModel<ProductImportModel>, RequestListProductImport.ApiParams> {

    public RequestListProductImport() {
        super(RequestListProductImport.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<ProductImportModel> result) {

    }

    @Override
    protected Call<BaseResponseModel<ProductImportModel>> call(RequestListProductImport.ApiParams params) {
        params.detect = "lazada_list_import";
        return getService().call(params);
    }

    interface Service {
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<ProductImportModel>> call(@Body RequestListProductImport.ApiParams params);
    }

    public static class ApiParams extends BaseApiParams {
        private String detect;
        public String store_id;
        @Nullable
        public String page;

        @Nullable
        public String limit;
    }
}