package org.omnidebt.client.view;

import org.omnidebt.client.R;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AboutFragment extends Fragment {
	
	private ImageView			ivJordan	= null;
	private ImageView			ivArnaud	= null;
	private ImageView			ivMatthias	= null;
	private ImageView			ivMathea	= null;
	
	private LinearLayout		llLayout	= null;
	private FragmentActivity	faActivity	= null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		faActivity	= (FragmentActivity)	super.getActivity();
		llLayout	= (LinearLayout)		inflater.inflate(R.layout.about_fragment, container, false);

		ivJordan = (ImageView) llLayout.findViewById(R.id.imageJordan);
		ivJordan.setOnClickListener(onClickJordan);
		ivArnaud = (ImageView) llLayout.findViewById(R.id.imageArnaud);
		ivArnaud.setOnClickListener(onClickArnaud);
		ivMatthias = (ImageView) llLayout.findViewById(R.id.imageMatthias);
		ivMatthias.setOnClickListener(onClickMatthias);
		ivMathea = (ImageView) llLayout.findViewById(R.id.imageMathea);
		ivMathea.setOnClickListener(onClickMathea);

		return llLayout;

	}

	private OnClickListener onClickJordan = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "https://twitter.com/DjowIsGreat";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
	
	private OnClickListener onClickArnaud = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "https://github.com/alacrampe";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
	
	private OnClickListener onClickMatthias = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "https://github.com/mdevlamynck";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};
	
	private OnClickListener onClickMathea = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String url = "http://www.viadeo.com/profile/002vvnwd3e2ne5i";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	};

}
