package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class UpdateAddressCustomerEvent {

    public static void post() {
        BusHelper.post(new UpdateAddressCustomerEvent());
    }
}
