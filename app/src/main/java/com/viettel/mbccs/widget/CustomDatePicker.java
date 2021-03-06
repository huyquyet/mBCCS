package com.viettel.mbccs.widget;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.LayoutDatePickerBinding;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.utils.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anh Vu Viet on 5/13/2017.
 */

@BindingMethods({
        @BindingMethod(type = CustomDatePicker.class, attribute = "setDate", method = "setDateToCalendar"),
        @BindingMethod(type = CustomDatePicker.class, attribute = "maxDate", method = "setMaxDate"),
        @BindingMethod(type = CustomDatePicker.class, attribute = "minDate", method = "setMinDate")
})
public class CustomDatePicker extends LinearLayout {

    private Calendar mCalendar;
    public ObservableField<String> date = new ObservableField<>();
    private DatePickerDialog datePickerDialog;
    private long maxDate = -1;
    private long minDate = -1;

    public CustomDatePicker(Context context) {
        this(context, null);
    }

    public CustomDatePicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomDatePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(final Context context, AttributeSet attrs) {
        ((LayoutDatePickerBinding) DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.layout_date_picker, this, true)).setInput(this);
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
        setDate();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog =
                        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        setDate();
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                                mCalendar.get(Calendar.DAY_OF_MONTH));
                if (maxDate != -1) {
                    datePickerDialog.getDatePicker().setMaxDate(maxDate);
                }
                if (minDate != -1) {
                    datePickerDialog.getDatePicker().setMinDate(minDate);
                }
                datePickerDialog.show();
            }
        });

        TextView textView = (TextView) findViewById(R.id.text_date);
        View view = findViewById(R.id.view);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomDatePicker);
        try {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    a.getDimension(R.styleable.CustomDatePicker_dateTextSize,
                            getResources().getDimension(R.dimen.sp_14)));
            textView.setTextColor(
                    a.getColor(R.styleable.CustomDatePicker_dateTextColor, Color.BLACK));
            view.setBackgroundColor(a.getColor(R.styleable.CustomDatePicker_underLineColor,
                    getContext().getResources().getColor(R.color.grey_bright)));
        } finally {
            a.recycle();
        }
    }

    private void setDate() {
        date.set(DateUtils.convertToString(mCalendar.getTime(),
                DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY, null));
    }

    public long getDateInMilis() {
        return mCalendar.getTimeInMillis();
    }

    public String getStringDate() {
        return DateUtils.timestampToString(mCalendar.getTimeInMillis(),
                DateUtils.TIMEZONE_FORMAT_SERVER, null);
    }

    public String getStringFormatDDMMYY() {
        return DateUtils.timestampToString(mCalendar.getTimeInMillis(),
                DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY, null);
    }

    public void setDateToCalendar(String dateString) {
        if (StringUtils.isEmpty(dateString)) return;

        Date date = DateUtils.stringToDate(dateString, DateUtils.TIMEZONE_FORMAT_SERVER,
                Locale.getDefault());
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
        mCalendar.setTime(date);
        setDate();
    }

    public void setDateToCalendar(Date date) {
        if (date == null) return;
        mCalendar.setTime(date);
        setDate();
    }

    public void setMaxDate(String date) {
        Date d = DateUtils.convertToDate(date, DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY);
        if (d == null) {
            maxDate = -1;
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));

        maxDate = calendar.getTimeInMillis();
    }

    public void setMaxDate(Date date) {
        if (date == null) {
            maxDate = -1;
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMaximum(Calendar.MILLISECOND));

        maxDate = calendar.getTimeInMillis();
    }

    public void setMinDate(String date) {
        Date d = DateUtils.convertToDate(date, DateUtils.CALENDAR_DATE_FORMAT_DD_MM_YY);
        if (d == null) {
            minDate = -1;
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));

        minDate = calendar.getTimeInMillis();
    }

    public void setMinDate(Date date) {
        if (date == null) {
            minDate = -1;
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getMinimum(Calendar.MILLISECOND));

        minDate = calendar.getTimeInMillis();
    }
}
