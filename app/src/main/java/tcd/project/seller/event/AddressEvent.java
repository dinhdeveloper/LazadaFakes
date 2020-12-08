package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class AddressEvent {

    public static void post() {
        BusHelper.post(new AddressEvent());
    }
}