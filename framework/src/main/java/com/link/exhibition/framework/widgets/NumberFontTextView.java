package com.link.exhibition.framework.widgets;

import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.link.exhibition.framework.utils.data.DisplayUtils;

/**
 * Created on 2019/2/27  10:42
 * chenpan pan.chen@linkfeeling.cn
 */
public final class NumberFontTextView extends AppCompatTextView {

//    private boolean adjustTopForAscent = true;
//    private Paint.FontMetricsInt fontMetricsInt;

    public NumberFontTextView(Context context) {
        this(context, null);
    }

    public NumberFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(@Nullable Typeface tf) {
        super.setTypeface(DisplayUtils.getNumberFont());
    }

//    @Override
//    public boolean getIncludeFontPadding() {
//        return false;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        if (!adjustTopForAscent && !TextUtils.isEmpty(getText())) {
//            adjustTopForAscent = false;
//            if (fontMetricsInt == null) {
//                fontMetricsInt = new Paint.FontMetricsInt();
//                getPaint().getFontMetricsInt(fontMetricsInt);
//            }
//            canvas.translate(0, fontMetricsInt.descent);
//        }
//        super.onDraw(canvas);
//    }
}
