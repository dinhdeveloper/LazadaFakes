package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class FragmentBackAboutEvent {

    public static void post() {
        BusHelper.post(new FragmentBackAboutEvent());
    }
}
