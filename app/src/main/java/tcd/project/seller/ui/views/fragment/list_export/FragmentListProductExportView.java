package tcd.project.seller.ui.views.fragment.list_export;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Timer;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import b.laixuantam.myaarlibrary.helper.AppUtils;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrClassicFrameLayout;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrDefaultHandler;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrFrameLayout;
import b.laixuantam.myaarlibrary.widgets.cptr.loadmore.OnLoadMoreListener;
import b.laixuantam.myaarlibrary.widgets.cptr.recyclerview.RecyclerAdapterWithHF;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.adapter.seller.ListProductExportAdapter;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;

public class FragmentListProductExportView extends BaseView<FragmentListProductExportView.UIContainer> implements FragmentListProductExportViewInterface {

    HomeActivity activity;
    FragmentListProductExportViewCallback callback;

    ListProductExportAdapter listProductExportAdapter;
    private RecyclerAdapterWithHF recyclerAdapterWithHF;
    private ArrayList<OptionModel> listDatas = new ArrayList<>();
    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms
    private boolean isRefreshList = false;


    @Override
    public void init(HomeActivity activity, FragmentListProductExportViewCallback callback) {
        this.activity = activity;
        this.callback = callback;
        setUpListData();
    }

    private void setUpListData() {
        ui.recycler_view_list_product.getRecycledViewPool().clear();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ui.recycler_view_list_product.setLayoutManager(linearLayoutManager);

        //todo setup list with adapter

        listProductExportAdapter = new ListProductExportAdapter(getContext(), listDatas);

        listProductExportAdapter.setListener(item -> {
            if (callback != null)
                callback.onItemListSelected(item);
        });

        recyclerAdapterWithHF = new RecyclerAdapterWithHF(listProductExportAdapter);

        ui.recycler_view_list_product.setAdapter(recyclerAdapterWithHF);

        ui.ptrClassicFrameLayout.setLoadMoreEnable(true);

        ui.ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler(true) {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                AppUtils.hideKeyBoard(getView());
                isRefreshList = true;
                ui.edit_filter.setText("");
                listDatas.clear();
                listProductExportAdapter.notifyDataSetChanged();
                ui.recycler_view_list_product.getRecycledViewPool().clear();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ui.ptrClassicFrameLayout.refreshComplete();

                        if (callback != null) {
                            callback.refreshLoadingList();
                            isRefreshList = false;
                        }
                    }
                }, 100);


            }
        });

        ui.ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        if (callback != null)
                            callback.onRequestLoadMoreList();

                    }
                }, 100);
            }
        });
    }

    @Override
    public void showEmptyList() {
        setVisible(ui.layoutEmptyList);
        setGone(ui.ptrClassicFrameLayout);
    }

    @Override
    public void hideEmptyList() {
        setGone(ui.layoutEmptyList);
        setVisible(ui.ptrClassicFrameLayout);
    }

    @Override
    public void setDataList(BaseResponseModel dataList) {
        ui.recycler_view_list_product.getRecycledViewPool().clear();

        if (dataList.getData() == null || dataList.getData().length == 0) {
            if (listDatas.size() == 0)
                showEmptyList();
            return;
        }

        hideEmptyList();

//        listDatas.addAll(Arrays.asList(arrDatas));
        ProductExportModel[] arrOrder = (ProductExportModel[]) dataList.getData();

        for (ProductExportModel itemOrderModel : arrOrder) {
            OptionModel optionModel = new OptionModel();
            optionModel.setDtaCustom(itemOrderModel);
            listDatas.add(optionModel);
        }

        recyclerAdapterWithHF.notifyDataSetChanged();
        ui.ptrClassicFrameLayout.loadMoreComplete(true);
        ui.ptrClassicFrameLayout.setLoadMoreEnable(true);
    }

    @Override
    public void setNoMoreLoading() {
        ui.ptrClassicFrameLayout.loadMoreComplete(true);
        ui.ptrClassicFrameLayout.setLoadMoreEnable(false);
    }

    @Override
    public void resetListData() {
        listDatas.clear();
        listProductExportAdapter.notifyDataSetChanged();
        ui.recycler_view_list_product.getRecycledViewPool().clear();
    }

    @Override
    public void hideRootView() {
        setGone(ui.layoutRootView);
    }

    @Override
    public void showRootView() {
        setVisible(ui.layoutRootView);
    }

    @Override
    public void clearListData() {
        listDatas.clear();
        listProductExportAdapter.notifyDataSetChanged();
        ui.recycler_view_list_product.getRecycledViewPool().clear();
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentListProductExportView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_list_product_export;
    }

    public static class UIContainer extends BaseUiContainer {
        @UiElement(R.id.layoutRootView)
        LinearLayout layoutRootView;

        @UiElement(R.id.tvHello)
        TextView tvHello;

        @UiElement(R.id.tvCustomerName)
        TextView tvCustomerName;

        @UiElement(R.id.imvMore)
        ImageView imvMore;

        @UiElement(R.id.edit_filter)
        EditText edit_filter;

        @UiElement(R.id.imvFilter)
        ImageView imvFilter;

        @UiElement(R.id.recycler_view_list_)
        RecyclerView recycler_view_list_product;

        @UiElement(R.id.ptrClassicFrameLayout)
        PtrClassicFrameLayout ptrClassicFrameLayout;

        @UiElement(R.id.layoutEmptyList)
        public View layoutEmptyList;
    }
}
