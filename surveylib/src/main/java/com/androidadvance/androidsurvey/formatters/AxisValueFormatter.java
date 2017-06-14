package com.androidadvance.androidsurvey.formatters;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by minhnx on 6/10/17.
 */

public class AxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    public AxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value);
    }
}