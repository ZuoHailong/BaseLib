package com.drumbeat.baselib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.drumbeat.baselib.R;

/**
 * 自定义EmptyView
 * Created by ZuoHailong on 2019/7/29.
 */
public class CustomEmptyView extends RelativeLayout {

    private Context context;

    private View customEmptyView;

    private ImageView ivEmpty;
    private TextView tvEmpty;


    public CustomEmptyView setText(@StringRes int stringRes) {
        tvEmpty.setText(context.getString(stringRes));
        return this;
    }

    public CustomEmptyView setTextSize(int textSize) {
        tvEmpty.setTextSize(textSize);
        return this;
    }

    public CustomEmptyView setTextColor(@ColorRes int colorRes) {
        tvEmpty.setTextColor(ContextCompat.getColor(context, colorRes));
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEmptyView setIconColor(@ColorRes int colorRes) {
        Drawable drawable = ivEmpty.getDrawable();
        drawable.setTint(ContextCompat.getColor(context, colorRes));
        return this;
    }

    public CustomEmptyView setIconResource(@DrawableRes int drawableRes) {
        ivEmpty.setImageResource(drawableRes);
        return this;
    }

    public CustomEmptyView(Context context) {
        this(context, null);
    }

    public CustomEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        customEmptyView = View.inflate(context, R.layout.baselib_view_custom_empty, this);

        ivEmpty = customEmptyView.findViewById(R.id.ivEmpty);
        tvEmpty = customEmptyView.findViewById(R.id.tvEmpty);
    }
}
