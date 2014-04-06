package com.example.mandawiki;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class Songs extends FragmentActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_songs);
		getActionBar().setTitle("Sånger");
		
	}
	
	public void viewSong(String url) {
		SongText newFragment = new SongText();
		Bundle args = new Bundle();
		args.putString(SongText.ARG_URL, url);
		newFragment.setArguments(args);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		if(findViewById(R.id.SongText) == null) {
			transaction.replace(R.id.SongList, newFragment);
			transaction.addToBackStack(null);
			transaction.commit();
		} else {
			transaction.replace(R.id.SongText, newFragment);
			transaction.commit();
		}
		
	}

}
