package kfdl.demo;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.demo.model.User;
import kfdl.demo.view.BottomBar;
import kfdl.test.kafoodle.R;

public class MainActivity extends BaseActivity {
    private Integer currentFragmentIndex = 0;
    private Fragment activeFragment = null;

    @BindView(R.id.logo_background)
    ImageView logoBackground;

    @BindView(R.id.logo_title)
    ImageView logoTitle;

    @BindView(R.id.cats_list)
    GridLayout catsList;

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getComponent().inject(this);

        Glide.with(this)
                .load(R.drawable.food2)
                .fitCenter()
                .centerCrop()
                .into(logoBackground);

        Glide.with(this)
                .load(R.drawable.logo)
                .fitCenter()
                .into(logoTitle);

        findViewById(R.id.cat_offers).setOnClickListener(v -> {
            startActivity(OfferActivity.startDefault(this));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.cat_places).setOnClickListener(v -> {
            startActivity(PlaceActivity.startDefault(this));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.cat_events).setOnClickListener(v -> {
            startActivity(EventActivity.startDefault(this));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left_part);
        });

        findViewById(R.id.cat_nutritions).setOnClickListener(v -> {
            Snackbar.make(findViewById(R.id.activity_content), "Nutritions in progress", Snackbar.LENGTH_SHORT)
                    .setAction("OK", v1 -> {})
                    .show();
        });

        findViewById(R.id.cat_details).setOnClickListener(v -> {
            Snackbar.make(findViewById(R.id.activity_content), "Details in progress", Snackbar.LENGTH_SHORT)
                    .setAction("OK", v1 -> {})
                    .show();
        });

        findViewById(R.id.cat_pools).setOnClickListener(v -> {
            Snackbar.make(findViewById(R.id.activity_content), "Daily pools in progress", Snackbar.LENGTH_SHORT)
                    .setAction("OK", v1 -> {})
                    .show();
        });

        bottomBar.setOnItemSelectedListener(index -> {
            if (index == currentFragmentIndex) {
                return;
            }

            switch (index) {
                case 1: {
                    if (activeFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.slide_in_right_full, R.anim.slide_out_right)
                                .remove(activeFragment)
                                .commit();
                    }
                    break;
                }
                case 2: {
                    ProfileFragment fragment = new ProfileFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    if (currentFragmentIndex == 3) {
                        ft.setCustomAnimations(R.anim.slide_in_right_full, R.anim.slide_out_right);
                    } else {
                        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left_full);
                    }
                    ft.replace(R.id.activity_content, fragment);
                    ft.commit();
                    activeFragment = fragment;
                    break;
                }
                case 3: {
                    SettingsFragment fragment = new SettingsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left_full)
                            .replace(R.id.activity_content, fragment)
                            .commit();
                    activeFragment = fragment;
                    break;
                }
            }

            currentFragmentIndex = index;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (! user.isLogged) {
            startActivity(LoginActivity.startDefault(this));
            return;
        }
    }
}
