package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class FragmentBackFilterEvent  {

    public static void post() {
        BusHelper.post(new FragmentBackFilterEvent());
    }
}
