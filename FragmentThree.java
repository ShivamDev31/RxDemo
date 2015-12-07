package com.shivamdev.rxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shivamchopra on 25/11/15.
 */
public class FragmentThree extends Fragment {

	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_three, container, false);

		TextView fragButton = (TextView) view.findViewById(R.id.tv_frag_three);

		return view;
	}
}
