package kfdl.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.test.kafoodle.R;

public class Category extends RelativeLayout {
    @BindView(R.id.category_title)
    TextView title;

    @BindView(R.id.category_background)
    ImageView backgroundImage;

    @BindView(R.id.category_hint)
    TextView hint;

    public Category(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View root = inflate(context, R.layout.category, this);
        ButterKnife.bind(this, root);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Category);
            try {

                if (a.hasValue(R.styleable.Category_title)) {
                    title.setText(a.getString(R.styleable.Category_title));
                }

                if (a.hasValue(R.styleable.Category_backgroundImage)) {
                    Glide.with(context)
                            .load(a.getResourceId(R.styleable.Category_backgroundImage, 0))
                            .centerCrop()
                            .into(backgroundImage);
                }

                if (a.hasValue(R.styleable.Category_hint)) {
                    hint.setText(a.getString(R.styleable.Category_hint));
                    hint.setVisibility(VISIBLE);
                }
            } finally {
                a.recycle();
            }
        }

        backgroundImage.setOnTouchListener((v, event) -> {
            super.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    backgroundImage.setAlpha(200);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    backgroundImage.setAlpha(255);
                    break;
            }
            return true;
        });
    }
}
