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

@ApiRequest.ApiName("lazada_list_export")
public class RequestListProductExport extends ApiRequest<RequestListProductExport.Service, BaseResponseModel<ProductExportModel>, RequestListProductExport.ApiParams> {

    public RequestListProductExport() {
        super(RequestListProductExport.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<ProductExportModel> result) {

    }

    @Override
    protected Call<BaseResponseModel<ProductExportModel>> call(RequestListProductExport.ApiParams params) {
        params.detect = "lazada_list_export";
        return getService().call(params);
    }

    interface Service {
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<ProductExportModel>> call(@Body RequestListProductExport.ApiParams params);
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