package tcd.project.seller.ui.fragment.notification;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;
import tcd.project.seller.activity.HomeActivity;
import tcd.project.seller.model.NotificationModel;

public interface FragmentNotificationViewInterface extends BaseViewInterface {

    void init(HomeActivity activity, FragmentNotificationViewCallback callback);

    void setDataNotification(NotificationModel[] data);

    void setNoMoreLoading();

    void validateCheckSeenNotifySuccess();

    void hideRootView();

    void showRootView();
}
