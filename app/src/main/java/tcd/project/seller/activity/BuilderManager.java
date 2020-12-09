package tcd.project.seller.activity;

import android.graphics.Color;
import android.graphics.Rect;

import com.nightonke.boommenu.BoomButtons.HamButton;

import tcd.project.seller.R;

public class BuilderManager {
    private static int[] imageResources = new int[]{
            R.drawable.ic_home_while,
            R.drawable.ic_icon_warehouse_while,
            R.drawable.ic_icon_export_while,
            R.drawable.ic_import_1,
            R.drawable.ic_icon_settings_while
    };
    private static int[] textResources = new int[]{
            R.string.home,
            R.string.list_import,
            R.string.export_,
            R.string.import_,
            R.string.profile
    };
    private static int[] subTextResources = new int[]{
            R.string.home_sub,
            R.string.list_import_sub,
            R.string.export_sub,
            R.string.import_sub,
            R.string.profile_sub
    };
    private static int[] colorResources = new int[]{
            Color.parseColor("#44C13C"),
            Color.parseColor("#10ABFE"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#03DAC5"),
            Color.parseColor("#673AB7")
    };

    private static int imageResourceIndex = 0;
    private static int textResourceIndex = 0;
    private static int subTextResourceIndex = 0;
    private static int colorResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    static int getTextRes() {
        if (textResourceIndex >= textResources.length) textResourceIndex = 0;
        return textResources[textResourceIndex++];
    }

    private static int getSubTextRes() {
        if (subTextResourceIndex >= subTextResources.length) subTextResourceIndex = 0;
        return subTextResources[subTextResourceIndex++];
    }
    private static int getColorRes() {
        if (colorResourceIndex >= colorResources.length) colorResourceIndex = 0;
        return colorResources[colorResourceIndex++];
    }


    public static HamButton.Builder getHamButtonBuilderWithDifferentPieceColor() {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .imagePadding(new Rect(10,20,20,20))
                .normalTextRes(getTextRes())
                .subNormalTextRes(getSubTextRes())
                .normalColor(getColorRes())
                .pieceColor(Color.WHITE);
    }

    private static BuilderManager ourInstance = new BuilderManager();

    public static BuilderManager getInstance() {
        return ourInstance;
    }

    private BuilderManager() {
    }
}