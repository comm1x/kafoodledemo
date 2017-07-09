package kfdl.demo.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.demo.MainActivity;
import kfdl.test.kafoodle.R;

public class Toolbar extends RelativeLayout {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.toolbar_left_button)
    Button leftButton;

    @BindView(R.id.toolbar_right_button)
    Button rightButton;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View root = inflate(context, R.layout.toolbar, this);
        ButterKnife.bind(this, root);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Toolbar);
            try {
                if (a.hasValue(R.styleable.Toolbar_toolbarTitle)) {
                    toolbarTitle.setText(a.getString(R.styleable.Toolbar_toolbarTitle));
                }

                if (a.hasValue(R.styleable.Toolbar_enableBackButton) && a.getBoolean(R.styleable.Toolbar_enableBackButton, false)) {
                    leftButton.setVisibility(VISIBLE);
                }

                if (a.hasValue(R.styleable.Toolbar_enableHomeButton) && a.getBoolean(R.styleable.Toolbar_enableHomeButton, false)) {
                    rightButton.setVisibility(VISIBLE);
                }

                if (a.hasValue(R.styleable.Toolbar_backButtonTitle)) {
                    setLeftButtonTitle(a.getString(R.styleable.Toolbar_backButtonTitle));
                }
            } finally {
                a.recycle();
            }
        }

        if (context instanceof Activity) {
            leftButton.setOnClickListener(v -> ((Activity) context).onBackPressed());
            rightButton.setOnClickListener(v -> {
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            });
        }
    }

    public void setTitle(String title) {
        toolbarTitle.setText(title);
    }

    public void setLeftButtonTitle(String title) {
        leftButton.setText(title);
    }
}
