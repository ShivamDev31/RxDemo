package com.shivamdev.rxdemo.network.api;

import com.shivamdev.rxdemo.model.ContactsModel;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by shivamchopra on 20/11/15.
 */
public interface ContactsApi {

	@GET
	Observable<ContactsModel> getContactsApi();
}
