package kfdl.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kfdl.demo.model.DataLayer;
import kfdl.demo.model.Place;
import kfdl.demo.view.Toolbar;
import kfdl.test.kafoodle.R;

public class MapsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DataLayer dataLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        App.getComponent().inject(this);

        Integer placeId = getIntent().getExtras().getInt("placeId");
        Place place = dataLayer.getPlaceById(placeId);

        toolbar.setTitle(place.title);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(map -> {
            LatLng point = new LatLng(place.lat, place.lng);
            Marker marker = map.addMarker(
                    new MarkerOptions()
                            .position(point)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
            );

            map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                    .target(point)
                    .zoom(15f)
                    .bearing(0)
                    .tilt(25)
                    .build()
            ));

            map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View view = getLayoutInflater().inflate(R.layout.marker, null);

                    TextView markerHeader = (TextView) view.findViewById(R.id.marker_header);
                    TextView markerDescription = (TextView) view.findViewById(R.id.marker_description);
                    TextView markerPhone = (TextView) view.findViewById(R.id.marker_phone);

                    markerHeader.setText(place.title);
                    markerDescription.setText(place.description);
                    markerPhone.setText(getString(R.string.phone) + " " + place.phone);

                    return view;
                }
            });

            marker.showInfoWindow();
        });
    }

    public static Intent startDisplayPlace(Context context, Integer placeId) {
        Intent i = new Intent(context, MapsActivity.class);
        i.putExtra("placeId", placeId);
        return i;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right_part, R.anim.slide_out_right);
    }
}
