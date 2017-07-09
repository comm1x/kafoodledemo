package kfdl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kfdl.test.kafoodle.R;

public class PlaceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        findViewById(R.id.place1).setOnClickListener(v -> {
            startActivity(MapsActivity.startDisplayPlace(this, 1));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.place2).setOnClickListener(v -> {
            startActivity(MapsActivity.startDisplayPlace(this, 2));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.place3).setOnClickListener(v -> {
            startActivity(MapsActivity.startDisplayPlace(this, 3));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });
    }

    public static Intent startDefault(Context context) {
        return new Intent(context, PlaceActivity.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right_part, R.anim.slide_out_right);
    }
}
