package tcd.project.seller.ui.activity.notify_manager_activity;

import b.laixuantam.myaarlibrary.base.BaseViewInterface;

public interface NotificationManagerActivityViewInterface extends BaseViewInterface {

    void init(NotificationManagerActivityViewCallback callback);

    void setTitleHeader(String title);
}
