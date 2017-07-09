package kfdl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.demo.model.DataLayer;
import kfdl.demo.model.Offer;
import kfdl.demo.view.Toolbar;
import kfdl.test.kafoodle.R;

public class OfferCardActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.header_image)
    ImageView headerImage;

    @Inject
    DataLayer dataLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_card);
        ButterKnife.bind(this);
        App.getComponent().inject(this);

        Integer offerId = getIntent().getExtras().getInt("offerId");
        Offer offer = dataLayer.getOfferById(offerId);
        loadOffer(offer);
    }

    private void loadOffer(Offer offer) {
        toolbar.setTitle(offer.name);

        Glide.with(this)
                .load(getResources().getIdentifier(offer.image, "drawable", getPackageName()))
                .centerCrop()
                .into(headerImage);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right_part, R.anim.slide_out_right);
    }

    public static Intent startOfferView(Context context, Integer offerId) {
        Intent i = new Intent(context, OfferCardActivity.class);
        i.putExtra("offerId", offerId);
        return i;
    }
}
