package com.shivamdev.rxdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shivamdev.rxdemo.utils.Utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private Subscription textSubscribe;
	private Subscription buttonSubscribe;
	private Subscription clickSubscribtion;
	private TextView tvMain;
	private EditText etMain;
	FragmentManager manager;
	FragmentTransaction ft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager = getSupportFragmentManager();
		tvMain = (TextView) findViewById(R.id.tv_main);

		etMain = (EditText) findViewById(R.id.et_main);
		Button bListen = (Button) findViewById(R.id.b_listen);

		Observable<String> buttonObservable = Observable.create(subscriber -> {
			bListen.setOnClickListener(view -> {
				if (!subscriber.isUnsubscribed()) {
					subscriber.onNext(etMain.getText().toString());
				}
			});
		});

		buttonSubscribe = buttonObservable
				//.map(str -> Integer.valueOf(str))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe((String text) -> {

					if (!TextUtils.isEmpty(text)) {
						//if (text != 0) {
						tvMain.setText(text);
					} else {
						Toast.makeText(MainActivity.this, "Please write something.", Toast.LENGTH_SHORT).show();
					}
				}, (Throwable throwable) -> {
					Toast.makeText(MainActivity.this, "Ohh man there is an error", Toast.LENGTH_SHORT).show();
				});

		Observable<String> textObservable = Observable.create(subscriber -> {
			etMain.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					subscriber.onNext(s.toString());

				}
			});
		});

		textSubscribe = textObservable
				.debounce(500, TimeUnit.MILLISECONDS)
				//.filter(text ->  Utils.isValidEmail(text))
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(email -> {
					if (Utils.isValidEmail(email)) {
						etMain.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
						tvMain.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
						tvMain.setText("Your email is : " + email);
					} else {
						etMain.setTextColor(getResources().getColor(android.R.color.holo_red_light));
						tvMain.setTextColor(getResources().getColor(android.R.color.holo_red_light));
						tvMain.setText("Enter a valid email.");
					}
				});


	}

	/*public void checkFragmentClick() {
		Observable<String> observable = FragmentTwo.getClick();

		clickSubscribtion = observable
				.map(text -> "Text from fragment : " + text)
				.subscribe(onNextString -> {
			Log.d(TAG, "Main Activity onNext : " + onNextString);
			tvMain.setText(onNextString);
		}, onError -> {
			Log.d(TAG, "Main Activity onError : " + onError.toString());

		}, () -> {
			Log.d(TAG, "Main Activity onCompleted");
		});


	}*/

	@Override
	protected void onDestroy() {
		super.onDestroy();
		textSubscribe.unsubscribe();
		buttonSubscribe.unsubscribe();
	}

	public void openFragmentOne(View view) {
		switch (view.getId()) {
			case R.id.b_open_fragment_one :
				ft = manager.beginTransaction();
				ft.add(R.id.frame_layout, new FragmentTwo()).addToBackStack(null).commit();
				break;

			case R.id.b_open_fragment_two :
				ft = manager.beginTransaction();
				ft.replace(R.id.frame_layout, new FragmentThree()).addToBackStack(null).commit();
				break;
		}
	}
}