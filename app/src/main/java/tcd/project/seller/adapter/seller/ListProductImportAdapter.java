package tcd.project.seller.adapter.seller;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.List;

import b.laixuantam.myaarlibrary.helper.CurrencyFormater;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;
import tcd.project.seller.R;
import tcd.project.seller.dialog.option.OptionModel;
import tcd.project.seller.model.ProductExportModel;
import tcd.project.seller.model.ProductImportModel;

public class ListProductImportAdapter extends SuperAdapter<OptionModel> {
    ListProductImportListener listener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public interface ListProductImportListener {
        void onClickItem(OptionModel model);
    }

    public void setListener(ListProductImportListener listener) {
        this.listener = listener;
    }

    public ListProductImportAdapter(Context context, List<OptionModel> items) {
        super(context, items, R.layout.dinh_custom_item_product_import);
    }

    @Override
    public void onBind(SuperViewHolder itemView, int viewType, int layoutPosition, OptionModel item) {
        TextView tvSTT = itemView.findViewById(R.id.tvSTT);
        TextView tvStatus = itemView.findViewById(R.id.tvStatus);
        TextView tvProductName = itemView.findViewById(R.id.tvProductName);
        TextView tvEmployeeName = itemView.findViewById(R.id.tvEmployeeName);
        TextView tvEmployeePhone = itemView.findViewById(R.id.tvEmployeePhone);
        TextView tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);

        ProductImportModel model = (ProductImportModel)item.getDtaCustom();

        tvSTT.setText(String.valueOf(layoutPosition));
        if (!TextUtils.isEmpty(model.getProduct_name())) {
            tvProductName.setText(model.getProduct_name());
        }
        if (!TextUtils.isEmpty(model.getEmployee_name())) {
            tvEmployeeName.setText(model.getEmployee_name());
        }
        if (!TextUtils.isEmpty(model.getEmployee_phone())) {
            tvEmployeePhone.setText(model.getEmployee_phone());
        }
        if (!TextUtils.isEmpty(model.getQuantity_import())) {
            tvStatus.setText("Tồn kho: "+CurrencyFormater.formatCurrency(Double.valueOf(model.getQuantity_import())));

            if (Double.valueOf(model.getQuantity_import()) < Double.valueOf(model.getSafe_stock())) {
                tvStatus.setTextColor(Color.parseColor("#e31b23"));
            }else {
                tvStatus.setTextColor(Color.parseColor("#2082F9"));
            }
        }

        if (!TextUtils.isEmpty(model.getProduct_price())) {
            tvPriceProduct.setText(CurrencyFormater.formatCurrency(Double.valueOf(model.getProduct_price())));
        }

//        if (!TextUtils.isEmpty(model.getType_order()) && model.getType_order().equalsIgnoreCase("0")) {
//            tvStatus.setText("Đang giao hàng");
//        } else {
//            tvStatus.setTextColor(Color.parseColor("#27c24c"));
//            tvStatus.setText("Đã giao hàng");
//        }
    }
}
