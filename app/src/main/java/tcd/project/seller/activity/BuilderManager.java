package tcd.project.seller.activity;

import android.graphics.Color;
import com.nightonke.boommenu.BoomButtons.HamButton;
import tcd.project.seller.R;

public class BuilderManager {

    private static int[] imageResources = new int[]{
            R.drawable.dinh_icon_home_invisible,
            R.drawable.dinh_icon_home_invisible,
            R.drawable.dinh_icon_home_invisible,
            R.drawable.dinh_icon_home_invisible
    };

    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public static HamButton.Builder getHamButtonBuilderWithDifferentPieceColor() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalTextRes(R.string.app_name)
                .subNormalTextRes(R.string.app_name)
                .pieceColor(Color.WHITE);
    }

    private static BuilderManager ourInstance = new BuilderManager();

    public static BuilderManager getInstance() {
        return ourInstance;
    }

    private BuilderManager() {
    }
}

