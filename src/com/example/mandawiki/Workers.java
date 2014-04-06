package com.example.mandawiki;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class Workers extends FragmentActivity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workers);
		
		getActionBar().setTitle("Arbetare");
	}
	
	public void viewWorker(String url) {
		WorkerText newFragment = new WorkerText();
		Bundle args = new Bundle();
		args.putString(WorkerText.ARG_URL, url);
		newFragment.setArguments(args);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

		if(findViewById(R.id.WorkerText) == null) {
			transaction.replace(R.id.WorkerList, newFragment);
			transaction.addToBackStack(null);
			transaction.commit();
		} else {
			transaction.replace(R.id.WorkerText, newFragment);
			transaction.commit();
		}
		
	}

}
