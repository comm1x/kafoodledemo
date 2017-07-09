package kfdl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.demo.model.DataLayer;
import kfdl.demo.model.Event;
import kfdl.demo.view.Toolbar;
import kfdl.test.kafoodle.R;

public class EventCardActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.header_image)
    ImageView headerImage;

    @BindView(R.id.header_text)
    TextView header;

    @BindView(R.id.description_text)
    TextView description;

    @Inject
    DataLayer dataLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_card);
        ButterKnife.bind(this);
        App.getComponent().inject(this);

        Integer eventId = getIntent().getExtras().getInt("eventId");
        Event event = dataLayer.getEventById(eventId);
        loadEvent(event);

        findViewById(R.id.book_button).setOnClickListener(v -> {
            Snackbar.make(findViewById(R.id.activity_content), "No more places", Snackbar.LENGTH_SHORT)
                    .setAction("OK", v1 -> {})
                    .show();
        });
    }

    private void loadEvent(Event event) {
        header.setText(event.header);
        description.setText(event.description);

        Glide.with(this)
                .load(getResources().getIdentifier(event.photo, "drawable", getPackageName()))
                .centerCrop()
                .into(headerImage);
    }

    public static Intent startEventView(Context context, Integer eventId) {
        Intent i = new Intent(context, EventCardActivity.class);
        i.putExtra("eventId", eventId);
        return i;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right_part, R.anim.slide_out_right);
    }
}
