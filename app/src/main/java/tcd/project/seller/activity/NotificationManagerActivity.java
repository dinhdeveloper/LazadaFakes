package tcd.project.seller.activity;

import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import b.laixuantam.myaarlibrary.base.BaseFragmentActivity;
import b.laixuantam.myaarlibrary.base.BaseParameters;
import tcd.project.seller.R;
import tcd.project.seller.event.FragmentNotificationBackEvent;
import tcd.project.seller.fragment.notification.FragmentNotification;
import tcd.project.seller.ui.action_bar.base_main_actionbar.BaseMainActionbarViewInterface;
import tcd.project.seller.ui.activity.notify_manager_activity.NotificationManagerActivityView;
import tcd.project.seller.ui.activity.notify_manager_activity.NotificationManagerActivityViewCallback;
import tcd.project.seller.ui.activity.notify_manager_activity.NotificationManagerActivityViewInterface;

public class NotificationManagerActivity extends BaseFragmentActivity<NotificationManagerActivityViewInterface, BaseMainActionbarViewInterface, BaseParameters> implements NotificationManagerActivityViewCallback {


    @Override
    protected void initialize(Bundle savedInstanceState) {
        view.init(this);
        view.setTitleHeader("Thông báo");
        changeToFragmentNotification();
    }

    @Override
    protected NotificationManagerActivityViewInterface getViewInstance() {
        return new NotificationManagerActivityView();
    }

    @Override
    protected BaseMainActionbarViewInterface getActionbarInstance() {
        return null;
    }

    @Override
    protected BaseParameters getParametersContainer() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.LayoutBaseMainFragmentActivity;
    }

    public void changeToFragmentNotification() {
        replaceFragment(new FragmentNotification(), false, Animation.SLIDE_IN_OUT);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFragmentNotificationBackEvent(FragmentNotificationBackEvent event) {
        if (view != null) {
            Intent refresh = new Intent(NotificationManagerActivity.this, HomeActivity.class);
            refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(refresh);
            this.finish();
        }
    }
}
