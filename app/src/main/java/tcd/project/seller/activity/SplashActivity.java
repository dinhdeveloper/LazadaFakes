package tcd.project.seller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Collections;

import tcd.project.seller.R;
import tcd.project.seller.adapter.SlidingImage_Adapter;
import tcd.project.seller.dependency.AppProvider;


public class SplashActivity extends Activity {

    private View layoutSplash1;
    private View layoutSplash2;
    private View layoutSplash3;
    private View loadingView;

    private final Integer[] IMAGES = {R.drawable.alert_dialog_button_cancel_bg, R.drawable.alert_dialog_button_confirm_bg, R.drawable.alert_dialog_button_cancel_bg};
    private final ArrayList<Integer> ImagesArray = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        checkLogin();
//        checkUpdateAppVersion();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        layoutSplash1 = findViewById(R.id.layoutSplash1);
        layoutSplash2 = findViewById(R.id.layoutSplash2);
        layoutSplash3 = findViewById(R.id.layoutSplash3);
        View btnBegin = findViewById(R.id.btnBegin);
        View btnBegin2 = findViewById(R.id.btnBegin2);
        View btnBegin3 = findViewById(R.id.btnBegin3);
        loadingView = findViewById(R.id.loadingView);
        loadingView.setVisibility(View.GONE);

        Collections.addAll(ImagesArray, IMAGES);

        ViewPager viewPager = findViewById(R.id.viewPager);

        SlidingImage_Adapter adapter = new SlidingImage_Adapter(SplashActivity.this, SlidingImage_Adapter.SliderImageType.RESOURCE);
        adapter.setShowLoading(false);
        adapter.setIMAGE_RESOURCE(ImagesArray);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        layoutSplash1.setVisibility(View.VISIBLE);
                        layoutSplash2.setVisibility(View.GONE);
                        layoutSplash3.setVisibility(View.GONE);
                        break;
                    case 1:
                        layoutSplash1.setVisibility(View.GONE);
                        layoutSplash2.setVisibility(View.VISIBLE);
                        layoutSplash3.setVisibility(View.GONE);
                        break;
                    case 2:
                        layoutSplash1.setVisibility(View.GONE);
                        layoutSplash2.setVisibility(View.GONE);
                        layoutSplash3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        btnBegin.setOnClickListener(view -> {
            viewPager.setCurrentItem(1);
        });
        btnBegin2.setOnClickListener(view -> {
            viewPager.setCurrentItem(2);
        });
//        btnBegin3.setOnClickListener(v -> {
//            loadingView.setVisibility(View.VISIBLE);
//
//            //Intent intent = new Intent(SplashActivity.this, CustomerReviewActivity.class);
//
//            overridePendingTransition(R.anim.slide_left_out, R.anim.slide_right_in);
//
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            startActivity(intent);
//            finish();
//        });
    }
    private void checkLogin() {
        if (AppProvider.getPreferences().checkLoginStatus()) {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);

            overridePendingTransition(R.anim.slide_left_out, R.anim.slide_right_in);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            finish();
        }
    }
}
