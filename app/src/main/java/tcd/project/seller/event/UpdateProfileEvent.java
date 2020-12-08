package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class UpdateProfileEvent {

    public static void post() {
        BusHelper.post(new UpdateProfileEvent());
    }
}
