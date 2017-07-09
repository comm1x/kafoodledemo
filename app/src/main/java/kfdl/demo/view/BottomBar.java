package kfdl.demo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.test.kafoodle.R;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

public class BottomBar extends LinearLayout {
    private static final int TEXT_SIZE_SELECTED = 14;
    private static final int TEXT_SIZE_BLURED = 12;
    private static final int DURATION = 200;
    private Button activeButton;
    private OnItemSelectedListener onItemSelectedListener;

    @BindView(R.id.button1)
    Button button1;

    @BindView(R.id.button2)
    Button button2;

    @BindView(R.id.button3)
    Button button3;

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View root = inflate(context, R.layout.bottom_bar, this);
        ButterKnife.bind(this, root);

        button1.setOnClickListener(v -> clickButton(1));
        button2.setOnClickListener(v -> clickButton(2));
        button3.setOnClickListener(v -> clickButton(3));
        activeButton = button1;
    }

    private void changeTextSize(Button button, int fromSize, int toSize) {
        ValueAnimator animator = ValueAnimator.ofFloat(fromSize, toSize);
        animator.setDuration(DURATION);
        animator.addUpdateListener(valueAnimator -> {
            button.setTextSize((Float) valueAnimator.getAnimatedValue());
        });
        animator.start();
    }

    private void selectButton(Button button) {
        changeTextSize(button, TEXT_SIZE_BLURED, TEXT_SIZE_SELECTED);
        CalligraphyUtils.applyFontToTextView(getContext(), button, "fonts/montserrat_semibold.ttf");
    }

    private void blurButton(Button button) {
        changeTextSize(button, TEXT_SIZE_SELECTED, TEXT_SIZE_BLURED);
        CalligraphyUtils.applyFontToTextView(getContext(), button, "fonts/montserrat_regular.ttf");
    }

    private void clickButton(int index) {
        switch (index) {
            case 1: {
                if (activeButton == button1)
                    return;

                selectButton(button1);

                if (activeButton == button2)
                    blurButton(button2);

                if (activeButton == button3)
                    blurButton(button3);

                activeButton = button1;
                break;
            }
            case 2: {
                if (activeButton == button2)
                    return;

                selectButton(button2);

                if (activeButton == button1)
                    blurButton(button1);

                if (activeButton == button3)
                    blurButton(button3);

                activeButton = button2;
                break;
            }
            case 3: {
                if (activeButton == button3)
                    return;

                selectButton(button3);

                if (activeButton == button1)
                    blurButton(button1);

                if (activeButton == button2)
                    blurButton(button2);

                activeButton = button3;
                break;
            }
        }

        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(index);
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener l) {
        onItemSelectedListener = l;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int index);
    }
}
