package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class OnReloadEvent {

    public static void post() {
        BusHelper.post(new OnReloadEvent());
    }
}