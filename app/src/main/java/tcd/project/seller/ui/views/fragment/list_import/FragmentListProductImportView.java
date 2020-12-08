package tcd.project.seller.ui.views.fragment.list_import;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import b.laixuantam.myaarlibrary.widgets.cptr.PtrClassicFrameLayout;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;

public class FragmentListProductImportView extends BaseView<FragmentListProductImportView.UIContainer> implements FragmentListProductImportViewInterface{
    HomeActivity activity;
    FragmentListProductImportViewCallback callback;

    @Override
    public void init(HomeActivity activity, FragmentListProductImportViewCallback callback) {
        this.activity =activity;
        this.callback = callback;
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_list_product_import;
    }

    public class UIContainer extends BaseUiContainer {
        @UiElement(R.id.layoutRootView)
        RelativeLayout layoutRootView;

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

        @UiElement(R.id.recycler_view_list)
        RecyclerView recycler_view_list;

        @UiElement(R.id.ptrClassicFrameLayout)
        PtrClassicFrameLayout ptrClassicFrameLayout;


    }
}
