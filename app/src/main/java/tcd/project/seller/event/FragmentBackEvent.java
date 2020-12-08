package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class FragmentBackEvent {

    public static void post() {
        BusHelper.post(new FragmentBackEvent());
    }
}
