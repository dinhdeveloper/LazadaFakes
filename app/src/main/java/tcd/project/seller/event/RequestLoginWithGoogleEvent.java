package tcd.project.seller.event;

import b.laixuantam.myaarlibrary.helper.BusHelper;

public class RequestLoginWithGoogleEvent
{
    public static void post()
    {
        BusHelper.post(new RequestLoginWithGoogleEvent());
    }
}
