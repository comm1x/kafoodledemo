package kfdl.demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kfdl.demo.model.User;
import kfdl.test.kafoodle.R;

public class LoginActivity extends BaseActivity {
    private static String TAG = "LoginActivity";

    @BindView(R.id.login_background)
    ImageView background;

    @BindView(R.id.username)
    EditText usernameInput;

    @BindView(R.id.password)
    EditText passwordInput;

    @BindView(R.id.error_message)
    TextView errorLabel;

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        App.getComponent().inject(this);

        Glide.with(this)
                .load(R.drawable.food1)
                .fitCenter()
                .centerCrop()
                .into(background);

        passwordInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                onLoginClick();
                return true;
            }
            return false;
        });
    }

    public static Intent startDefault(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @OnClick(R.id.login_button)
    public void onLoginClick() {
        Log.d(TAG, "login");
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        final ProgressDialog dialog = ProgressDialog.show(this, null, "Login...");
            Observable.just(username.equals("user") && password.equals("123"))
                    .delay(1, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> dialog.dismiss())
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            user.isLogged = true;
                            finish();
                            overridePendingTransition(0, R.anim.slide_out_left_full);
                        } else {
                            errorLabel.setText("Login failed");
                        }
                    });
    }
}
