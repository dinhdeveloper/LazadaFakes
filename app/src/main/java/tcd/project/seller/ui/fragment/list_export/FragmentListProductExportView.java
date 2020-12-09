package tcd.project.seller.ui.fragment.list_export;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import b.laixuantam.myaarlibrary.helper.AppUtils;
import b.laixuantam.myaarlibrary.helper.KeyboardUtils;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrClassicFrameLayout;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrDefaultHandler;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrFrameLayout;
import b.laixuantam.myaarlibrary.widgets.cptr.loadmore.OnLoadMoreListener;
import b.laixuantam.myaarlibrary.widgets.cptr.recyclerview.RecyclerAdapterWithHF;
import b.laixuantam.myaarlibrary.widgets.dialog.single_choise.MyCustomSingleChoise;
import b.laixuantam.myaarlibrary.widgets.popupmenu.ActionItem;
import b.laixuantam.myaarlibrary.widgets.popupmenu.MyCustomPopupMenu;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.adapter.seller.ListProductExportAdapter;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.model.BaseResponseModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.ui.fragment.list_base.FragmentAdminManagerListBaseView;
import tcd.project.seller.ui.fragment.list_base.FragmentAdminManagerListBaseViewCallback;
import tcd.project.seller.ui.fragment.list_base.FragmentAdminManagerListBaseViewInterface;

public class FragmentListProductExportView extends BaseView<FragmentListProductExportView.UIContainer> implements FragmentAdminManagerListBaseViewInterface {

    private HomeActivity activity;
    private FragmentAdminManagerListBaseViewCallback callback;
    private ListProductExportAdapter productExportAdapter;
    private RecyclerAdapterWithHF recyclerAdapterWithHF;
    private ArrayList<OptionModel> listDatas = new ArrayList<>();
    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms
    private boolean isRefreshList = false;
    private String order_status, filter_string;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void init(HomeActivity activity, FragmentAdminManagerListBaseViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        KeyboardUtils.setupUI(getView(), activity);
        setGone(ui.actionAdd);
        setVisible(ui.actionFilter);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 12 && hour < 18) {
            ui.tvHello.setText("Chào buổi chiều ");
        } else if (hour > 18 && hour < 24) {
            ui.tvHello.setText("Chào buổi tối ");
        } else {
            ui.tvHello.setText("Chào buổi sáng ");
        }

        ui.edit_filter_.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null)
                    timer.cancel();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 1) {

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            String key = s.toString().trim();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    AppUtils.hideKeyBoard(getView());
                                    listDatas.clear();
//                                    orderManagerAdapter.notifyDataSetChanged();
                                    ui.recycler_view_list_.getRecycledViewPool().clear();
                                    callback.onRequestSearchWithFilter(order_status, key);
                                }
                            });
                        }

                    }, DELAY);
                } else {
                    if (!isRefreshList) {
                        AppUtils.hideKeyBoard(getView());
                        listDatas.clear();
//                        orderManagerAdapter.notifyDataSetChanged();
                        ui.recycler_view_list_.getRecycledViewPool().clear();
                        callback.onRequestSearchWithFilter(order_status, "");
                    }
                }
            }
        });
        setUpListData();
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

    private void setUpListData() {
        ui.recycler_view_list_.getRecycledViewPool().clear();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        ui.recycler_view_list_.setLayoutManager(linearLayoutManager);

        //todo setup list with adapter

        productExportAdapter = new ListProductExportAdapter(getContext(), listDatas);

        recyclerAdapterWithHF = new RecyclerAdapterWithHF(productExportAdapter);

        ui.recycler_view_list_.setAdapter(recyclerAdapterWithHF);

        ui.ptrClassicFrameLayout.setLoadMoreEnable(true);

        ui.ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler(true) {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                AppUtils.hideKeyBoard(getView());
                isRefreshList = true;
//                ui.edit_filter_transaction.setText("");
                listDatas.clear();
//                orderManagerAdapter.notifyDataSetChanged();
                ui.recycler_view_list_.getRecycledViewPool().clear();
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
    public void setDataList(BaseResponseModel arrDatas) {
        ui.recycler_view_list_.getRecycledViewPool().clear();

        if (arrDatas.getData() == null || arrDatas.getData().length == 0) {
            if (listDatas.size() == 0)
                showEmptyList();
            return;
        }

        hideEmptyList();

//        listDatas.addAll(Arrays.asList(arrDatas));
        ProductExportModel[] arrOrder = (ProductExportModel[]) arrDatas.getData();

        for (ProductExportModel itemOrderModel : arrOrder) {
            OptionModel optionModel = new OptionModel();
            optionModel.setDtaCustom(itemOrderModel);
            listDatas.add(optionModel);
//            if (customerSaleAdapter!=null){
//                customerSaleAdapter.getListData().add(optionModel);
//                customerSaleAdapter.getListDataBackup().add(optionModel);
//            }
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
//        orderManagerAdapter.notifyDataSetChanged();
        ui.recycler_view_list_.getRecycledViewPool().clear();
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
        public View layoutRootView;

        @UiElement(R.id.edit_filter)
        public EditText edit_filter_;

        @UiElement(R.id.btnAction1)
        public View actionAdd;

        @UiElement(R.id.btnAction2)
        public View actionFilter;

        @UiElement(R.id.ptrClassicFrameLayout)
        public PtrClassicFrameLayout ptrClassicFrameLayout;

        @UiElement(R.id.recycler_view_list)
        public RecyclerView recycler_view_list_;

        @UiElement(R.id.layoutEmptyList)
        public View layoutEmptyList;

        @UiElement(R.id.tvHello)
        public TextView tvHello;

        @UiElement(R.id.tvCustomerName)
        public TextView tvCustomerName;

        @UiElement(R.id.imvMore)
        public ImageView imvMore;

    }
}
