package tcd.project.seller.ui.fragment.settings;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;

import b.laixuantam.myaarlibrary.base.BaseUiContainer;
import b.laixuantam.myaarlibrary.base.BaseView;
import b.laixuantam.myaarlibrary.helper.KeyboardUtils;
import b.laixuantam.myaarlibrary.helper.MyLog;
import b.laixuantam.myaarlibrary.widgets.custompicker.MyCustomPicker;
import b.laixuantam.myaarlibrary.widgets.roundview.RoundTextView;
import tcd.project.seller.R;
import tcd.project.seller.activity.HomeActivity;

public class FragmentFillterOrderView extends BaseView<FragmentFillterOrderView.UIContainer> implements FragmentFillterOrderViewInterface {
    HomeActivity activity;
    FragmentFillterOrderViewCallback callback;

    private static String TAG = "MyCustomPicker";
    private Timer timer = new Timer();
    private final long DELAY = 500; // in ms
    private int positionItemMonthSelected = 1;
    private int positionItemYearSelected = 1;
    private int positionOldYearSelected = 1;
    String[] arr_year = new String[10];
    String[] arr_month = new String[12];

    @Override
    public void init(HomeActivity activity, FragmentFillterOrderViewCallback callback) {
        this.activity = activity;
        this.callback = callback;

        KeyboardUtils.setupUI(getView(), activity);
        ui.tvTitleHeader.setText("Lọc thời gian");

        initScheduleFilter();

        configPickerYear();
        configPickerMonth();
        //configCalendar();

        onClick();

    }

    private void onClick() {
        ui.btnBackHeader.setOnClickListener(v -> {
            if (callback != null)
                callback.onBackHeader();
        });

        //quay lai
        ui.btnCancel.setOnClickListener(v -> {
            if (callback != null)
                callback.onBackHeader();
        });

        //search
        ui.btnSearch.setOnClickListener(v -> {
            if (callback != null) {
                callback.filterDataCalendar(String.valueOf(positionItemMonthSelected), arr_year[positionItemYearSelected]);
            }
        });
    }

    private void initScheduleFilter() {

        for (int i = 1; i < 13; i++) {
            arr_month[i - 1] = "Tháng " + i;
        }

        final Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int nextYear = currentYear + 1;

        int pass1Year = currentYear - 1;
        int pass2Year = currentYear - 2;
        int pass3Year = currentYear - 3;
        int pass4Year = currentYear - 4;
        int pass5Year = currentYear - 5;
        int pass6Year = currentYear - 6;
        int pass7Year = currentYear - 7;
        int pass8Year = currentYear - 8;
        int pass9Year = currentYear - 9;

        arr_year[0] = "" + (nextYear);
        arr_year[1] = "" + (currentYear);
        arr_year[2] = "" + (pass1Year);
        arr_year[3] = "" + (pass2Year);
        arr_year[4] = "" + (pass3Year);
        arr_year[5] = "" + (pass4Year);
        arr_year[6] = "" + (pass5Year);
        arr_year[7] = "" + (pass6Year);
        arr_year[8] = "" + (pass7Year);
        arr_year[9] = "" + (pass8Year);
        positionItemYearSelected = 1;
        positionOldYearSelected = 1;
    }

    private void configPickerYear() {

        ui.number_picker_year.setDividerColor(ContextCompat.getColor(getContext(), R.color.transparent));
        ui.number_picker_year.setSelectedTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        ui.number_picker_year.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        ui.number_picker_year.setMinValue(1);
        ui.number_picker_year.setMaxValue(arr_year.length);
        ui.number_picker_year.setDisplayedValues(arr_year);
        ui.number_picker_year.setValue(positionOldYearSelected + 1);

        // Set fading edge enabled
        ui.number_picker_year.setFadingEdgeEnabled(true);

        // Set scroller enabled
        ui.number_picker_year.setScrollerEnabled(true);

        // Set wrap selector wheel
        ui.number_picker_year.setWrapSelectorWheel(false);

        // Set accessibility description enabled
        ui.number_picker_year.setAccessibilityDescriptionEnabled(true);

        // OnClickListener
        ui.number_picker_year.setOnClickListener(view -> MyLog.e(TAG, "Click on current value"));

        // OnValueChangeListener
        ui.number_picker_year.setOnValueChangedListener((picker, oldVal, newVal) -> {
            MyLog.e(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            positionItemYearSelected = newVal - 1;

//                if (ui.layoutPickerCalendar.getVisibility() != View.VISIBLE)
//                    return;
//
//                if (timer != null)
//                    timer.cancel();
//
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                //reloadCalendarView();
//                            }
//                        });
//                    }
//
//                }, DELAY);
        });

        // OnScrollListener
        ui.number_picker_year.setOnScrollListener((picker, scrollState) -> {
            if (scrollState == MyCustomPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                MyLog.e(TAG, String.format(Locale.US, "newVal: %d", picker.getValue()));
            }
        });
    }

    private void configPickerMonth() {

        ui.custom_picker_month.setDividerColor(ContextCompat.getColor(getContext(), R.color.transparent));
        ui.custom_picker_month.setSelectedTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        ui.custom_picker_month.setTextColor(ContextCompat.getColor(getContext(), R.color.gray));

        String[] data = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        ui.custom_picker_month.setMinValue(1);
        ui.custom_picker_month.setMaxValue(data.length);
        ui.custom_picker_month.setDisplayedValues(data);

        final Calendar now = Calendar.getInstance();
        int currentMonth = now.get(Calendar.MONTH);
        positionItemMonthSelected = currentMonth + 1;
        ui.custom_picker_month.setValue(positionItemMonthSelected);

        // Set fading edge enabled
        ui.custom_picker_month.setFadingEdgeEnabled(true);

        // Set scroller enabled
        ui.custom_picker_month.setScrollerEnabled(true);

        // Set wrap selector wheel
        ui.custom_picker_month.setWrapSelectorWheel(false);

        // Set accessibility description enabled
        ui.custom_picker_month.setAccessibilityDescriptionEnabled(true);

        // OnClickListener
        ui.custom_picker_month.setOnClickListener(view -> MyLog.e(TAG, "Click on current value"));

        // OnValueChangeListener
        ui.custom_picker_month.setOnValueChangedListener((picker, oldVal, newVal) -> {
            MyLog.e(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            positionItemMonthSelected = newVal;

//                if (ui.layoutPickerCalendar.getVisibility() != View.VISIBLE)
//                    return;
//                if (timer != null)
//                    timer.cancel();
//
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                //reloadCalendarView();
//                            }
//                        });
//                    }
//
//                }, DELAY);
        });

        // OnScrollListener
        ui.custom_picker_month.setOnScrollListener((picker, scrollState) -> {
            if (scrollState == MyCustomPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                MyLog.e(TAG, String.format(Locale.US, "newVal: %d", picker.getValue()));
            }
        });
    }

    @Override
    public BaseUiContainer getUiContainer() {
        return new FragmentFillterOrderView.UIContainer();
    }

    @Override
    public int getViewId() {
        return R.layout.layout_fragment_filter_order;
    }

    public class UIContainer extends BaseUiContainer {
        @UiElement(R.id.btnBackHeader)
        public ImageView btnBackHeader;

        @UiElement(R.id.tvTitleHeader)
        public TextView tvTitleHeader;

        @UiElement(R.id.layoutPickerYear)
        public LinearLayout layoutPickerYear;

        @UiElement(R.id.layoutPickerMonth)
        public LinearLayout layoutPickerMonth;

        @UiElement(R.id.layoutPickerCalendar)
        public LinearLayout layoutPickerCalendar;

        @UiElement(R.id.custom_picker_year)
        public MyCustomPicker number_picker_year;

        @UiElement(R.id.custom_picker_month)
        public MyCustomPicker custom_picker_month;

        @UiElement(R.id.btnCancel)
        public RoundTextView btnCancel;

        @UiElement(R.id.btnSearch)
        public RoundTextView btnSearch;
    }
}
