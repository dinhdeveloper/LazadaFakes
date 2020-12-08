package tcd.project.seller.adapter.seller;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;
import tcd.project.seller.R;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.model.ProductExportModel;

public class ListProductExportAdapter extends SuperAdapter<OptionModel> {
    ListProductExportListener listener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public interface ListProductExportListener {
        void onClickItem(OptionModel model);
    }

    public void setListener(ListProductExportListener listener) {
        this.listener = listener;
    }

    public ListProductExportAdapter(Context context, List<OptionModel> items) {
        super(context, items, R.layout.dinh_custom_item_product_export);
    }

    @Override
    public void onBind(SuperViewHolder itemView, int viewType, int layoutPosition, OptionModel item) {
        TextView tvSTT = itemView.findViewById(R.id.tvSTT);
        TextView tvStatus = itemView.findViewById(R.id.tvStatus);
        TextView tvChange = itemView.findViewById(R.id.tvChange);
        TextView tvProductName = itemView.findViewById(R.id.tvProductName);
        TextView tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
        TextView tvCustomerPhone = itemView.findViewById(R.id.tvCustomerPhone);
        TextView tvCustomerAddress = itemView.findViewById(R.id.tvCustomerAddress);

        ProductExportModel model = (ProductExportModel)item.getDtaCustom();

        tvSTT.setText(String.valueOf(layoutPosition));
        if (!TextUtils.isEmpty(model.getProduct_name())) {
            tvProductName.setText(model.getProduct_name());
        }
        if (!TextUtils.isEmpty(model.getCustomer_name())) {
            tvCustomerName.setText("KH: " + model.getCustomer_name());
        }
        if (!TextUtils.isEmpty(model.getCustomer_phone())) {
            tvCustomerPhone.setText("SĐT: " + model.getCustomer_phone());
        }
        if (!TextUtils.isEmpty(model.getCustomer_address())) {
            tvCustomerAddress.setText("ĐC: " + model.getCustomer_address());
        }
        if (!TextUtils.isEmpty(model.getType_order()) && model.getType_order().equalsIgnoreCase("0")) {
            tvStatus.setText("Đang giao hàng");
        } else {
            tvStatus.setTextColor(Color.parseColor("#27c24c"));
            tvStatus.setText("Đã giao hàng");
        }

        tvChange.setText("Thay đổi");
        tvChange.setOnClickListener(view -> {
            if (listener != null)
                listener.onClickItem(item);
        });
    }
}
