package kfdl.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import kfdl.test.kafoodle.R;

public class ProfileFragment extends Fragment {
    @BindView(R.id.profile_logo)
    ImageView profileLogo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);

        Glide.with(this)
                .load(R.drawable.man)
                .fitCenter()
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(profileLogo);

        return root;
    }
}
