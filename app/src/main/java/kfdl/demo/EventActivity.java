package kfdl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import kfdl.test.kafoodle.R;

public class EventActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        findViewById(R.id.event1).setOnClickListener(v -> {
            startActivity(EventCardActivity.startEventView(this, 1));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.event2).setOnClickListener(v -> {
            startActivity(EventCardActivity.startEventView(this, 2));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.event3).setOnClickListener(v -> {
            startActivity(EventCardActivity.startEventView(this, 3));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right_part, R.anim.slide_out_right);
    }

    public static Intent startDefault(Context context) {
        return new Intent(context, EventActivity.class);
    }
}
