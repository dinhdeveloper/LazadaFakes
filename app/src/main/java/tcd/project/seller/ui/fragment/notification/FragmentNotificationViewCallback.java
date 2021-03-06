package tcd.project.seller.ui.fragment.notification;

public interface FragmentNotificationViewCallback {
    void onClickBackHeader();

    void onRequestRefreshListNotification();

    void onRequestLoadMoreListNotification();

    void onRequestCheckViewNotify(String id);

    void onClickShowShoppingCart();

    void onClickFilterProduct();
}
