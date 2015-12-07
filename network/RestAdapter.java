package com.shivamdev.rxdemo.network;

import com.shivamdev.rxdemo.utils.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by shivamchopra on 20/11/15.
 */
public class RestAdapter {


	Retrofit retrofit = new Retrofit.Builder()
			.baseUrl(Constants.SOF_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.build();
}
