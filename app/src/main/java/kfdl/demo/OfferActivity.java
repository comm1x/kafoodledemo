package kfdl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import kfdl.test.kafoodle.R;

public class OfferActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        findViewById(R.id.offer1).setOnClickListener(v -> {
            startActivity(OfferCardActivity.startOfferView(this, 1));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.offer2).setOnClickListener(v -> {
            startActivity(OfferCardActivity.startOfferView(this, 2));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.offer3).setOnClickListener(v -> {
            startActivity(OfferCardActivity.startOfferView(this, 3));
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
        return new Intent(context, OfferActivity.class);
    }
}
