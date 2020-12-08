package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class BackTwoEvent {

    public static void post() {
        BusHelper.post(new BackTwoEvent());
    }
}
