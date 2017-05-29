package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.mbccs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jacky on 5/21/17.
 */

public class ItemSpinText extends LinearLayout {

    private Context mContext;
    private Unbinder mUnbinder;

    @BindView(R.id.iv_icon_left) ImageView mIconLeft;
    @BindView(R.id.iv_icon_right) ImageView mIconRight;
    @BindView(R.id.tv_title) TextView mText;
    //@BindView(R.id.tv_title) EditText mEdittext;

    public ItemSpinText(Context context) {
        super(context);
        init(context, null);
    }

    public ItemSpinText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemSpinText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_spinner_text_view, this);
        mUnbinder = ButterKnife.bind(this);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemSpinText);
        initTextView(mTypedArray);
        initIcon(mTypedArray, R.styleable.ItemSpinText_istIconLeft, R.styleable.ItemSpinText_istIconLeftBg, mIconLeft);
        initIcon(mTypedArray, R.styleable.ItemSpinText_istIconRight, R.styleable.ItemSpinText_istIconRightBg, mIconRight);

    }

    private void initTextView(TypedArray mTypedArray) {
        String title = mTypedArray.getString(R.styleable.ItemSpinText_istTextViewTitle);
        boolean isActive = mTypedArray.getBoolean(R.styleable.ItemSpinText_istTextView, false);
        mText.setVisibility(TextUtils.isEmpty(title) || !isActive ? GONE : VISIBLE);
        if (!TextUtils.isEmpty(title)) {
            int size = mTypedArray.getDimensionPixelSize(R.styleable.ItemSpinText_istTextViewSize,
                    getResources().getDimensionPixelSize(R.dimen.default_item_spinner_text_size));
            int color = mTypedArray.getColor(R.styleable.ItemSpinText_istTextViewColor,
                    getResources().getColor(R.color.default_item_spinner_text_color));
            mText.setText(title);
            mText.setTextSize(px2dip(size));
            mText.setTextColor(color);
        }
    }

    private void initIcon(TypedArray mTypedArray,int slActive, int styleable, ImageView mImage) {
        boolean isActive = mTypedArray.getBoolean(slActive, false);
        Drawable icon = mTypedArray.getDrawable(styleable);
        if (icon != null) {
            mImage.setImageDrawable(icon);
        }
        mImage.setVisibility(icon == null || !isActive ? GONE : VISIBLE);
    }

    private int px2dip(float pxValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setIconLeft(int icon) {
        if (icon > 0) {
            mIconLeft.setBackgroundResource(icon);
        }
    }

    public void setIconRight(int icon) {
        if (icon > 0) {
            mIconRight.setBackgroundResource(icon);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDetachedFromWindow();
    }
}