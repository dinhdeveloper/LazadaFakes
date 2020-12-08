package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class FragmentNotificationBackEvent {

    public static void post() {
        BusHelper.post(new FragmentNotificationBackEvent());
    }
}
