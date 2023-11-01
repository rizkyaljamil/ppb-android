package com.example.ppb_menu.custom_ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.ppb_menu.R;

public class ButtonPesan extends AppCompatButton {
    private Drawable backgroundButton;
    private int txtColor;

    public ButtonPesan(Context context) {
        super(context);
        init();
    }

    public ButtonPesan(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ButtonPesan(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(backgroundButton);

        setTextColor(txtColor);
        setTextSize(14);
        setGravity(Gravity.CENTER);
        setText(R.string.message);
    }

    private void init() {
        txtColor = ContextCompat.getColor(getContext(), R.color.green);
        backgroundButton = ContextCompat.getDrawable(getContext(), R.drawable.custom_button_pesan);
    }
}
