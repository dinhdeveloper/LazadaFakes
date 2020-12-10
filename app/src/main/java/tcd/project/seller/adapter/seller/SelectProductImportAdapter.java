package tcd.project.seller.adapter.seller;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;
import tcd.project.seller.R;
import tcd.project.seller.model.ProductImportModel;

public class SelectProductImportAdapter extends SuperAdapter<ProductImportModel> {
    SelectProductImportListener listener;

    public interface SelectProductImportListener {
        void onClickItem(ProductImportModel model);
    }

    public void setListener(SelectProductImportListener listener) {
        this.listener = listener;
    }

    public SelectProductImportAdapter(Context context, List<ProductImportModel> items) {
        super(context, items, R.layout.dinh_custom_item_choose_product);
    }

    int position_index = -1;

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ProductImportModel model) {
        LinearLayout layoutName = holder.findViewById(R.id.layoutName);
        TextView tvName = holder.findViewById(R.id.tvName);
        ImageView imgChooseName = holder.findViewById(R.id.imgChooseName);

        if (!TextUtils.isEmpty(model.getProduct_name())) {
            tvName.setText(model.getProduct_name());
        }

        layoutName.setOnClickListener(view -> {
            position_index = layoutPosition;
            notifyDataSetChanged();
        });

        if (position_index == layoutPosition) {
            imgChooseName.setVisibility(View.VISIBLE);
            if (listener!=null)
                listener.onClickItem(model);
        }else {
            imgChooseName.setVisibility(View.GONE);
        }
    }
}
