package com.shivamdev.rxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rx.subjects.PublishSubject;

/**
 * Created by shivamchopra on 25/11/15.
 */
public class FragmentTwo extends Fragment {

	public static final int INITIAL_DELAY = 0;
	public static final int POLLING_INTERVAL = 2000;
	private static final String TAG = FragmentTwo.class.getSimpleName();
	private static EditText fragEdit;
	FragmentManager manager;
	FragmentTransaction ft;
	private Button fragButton;
	private Button openFragThree;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_two, container, false);
		manager = getActivity().getSupportFragmentManager();

		fragButton = (Button) view.findViewById(R.id.b_fragment_two);
		fragEdit = (EditText) view.findViewById(R.id.et_frag);
		openFragThree = (Button) view.findViewById(R.id.b_fragment_open_three);
		MainActivity activity = (MainActivity) getActivity();

		fragButton.setOnClickListener(buttonView -> {


		});

		openFragThree.setOnClickListener(clickView -> {
			getSubject();
		});
		return view;
	}

	public void getSubject() {

		PublishSubject<String> subs = PublishSubject.create();

		subs
				.doOnCompleted(() -> Log.d(TAG, "Completed once"))
				.doOnSubscribe(() -> Log.d(TAG, "onSubscribe once"))

				.subscribe(text -> {
					Log.d(TAG, "Text : " + text);
						if(text.equals("Man")) {
							subs.onNext(text);
						}
				});


		subs.onNext("Hello");
		subs.onNext("World");
		subs.onNext("Man");

		/*AsyncSubject<String> obs = AsyncSubject.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(final Subscriber<? super String> observer) {

				Schedulers.newThread().createWorker()
						.schedulePeriodically(() -> {
							observer.onNext("Something");
						}, INITIAL_DELAY, POLLING_INTERVAL, TimeUnit.MILLISECONDS);
			}
		}).take(2);*/





		/*obs.subscribe(new Subscriber<String>() {
			@Override
			public void onCompleted() {
				Log.d(TAG, "completed");
			}

			@Override
			public void onError(Throwable e) {
				Log.d(TAG, "error" + e);
			}

			@Override
			public void onNext(String string) {
				Log.d(TAG, "onNext : " + string);
				if(string.equals("Something")) {
					obs.;
				}
			}
		});*/
	}
}