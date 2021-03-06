package com.viettel.mbccs.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by jacky on 6/5/17.
 */

public class ScreenUtils {

    public static int px2dip(Context mContext, float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if(context == null){
            return (int)dipValue;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * @param context
     * @param px
     * @return
     */
    public static float px2sp(Context context, Float px) {
        if(context == null){
            return px.intValue();
        }
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    /**
     * @param context
     * @param sp
     * @return
     */
    public static float sp2px(Context context, float sp) {
        if(context == null){
            return sp;
        }
        Resources r = context.getResources();
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                r.getDisplayMetrics());
        return size;
    }

    private static int screenWidthPixels;
    private static int screenHeightPixels;

    /**
     * @param context
     * @return
     */
    public static int getScreenWidthPixels(Context context) {

        if (context == null) {
            Log.e("error","Can't get screen size while the activity is null!");
            return 0;
        }

        if (screenWidthPixels > 0) {
            return screenWidthPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        screenWidthPixels = dm.widthPixels;
        return screenWidthPixels;
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenHeightPixels(Context context) {
        if (context == null) {
            Log.e("error","Can't get screen size while the activity is null!");
            return 0;
        }

        if (screenHeightPixels > 0) {
            return screenHeightPixels;
        }
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        screenHeightPixels = dm.heightPixels;
        return screenHeightPixels;
    }

    public static int dpToPx(Context context,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private int getActionBarHeight(AppCompatActivity appCompatActivity) {
        int actionBarHeight;
        final TypedArray styledAttributes = appCompatActivity.getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize }
        );
        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarHeight;
    }
}
