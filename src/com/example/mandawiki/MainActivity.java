package com.example.mandawiki;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity  {
	
	public final static String EXTRA_MESSAGE = "com.example.mandawiki.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getSupportActionBar().hide();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void IntentYakatana(View view) {
		Intent intent = new Intent(this, YakatanaRecipe.class);
		startActivity(intent);
	}
	
	public void IntentSongs(View view) {
		Intent intent = new Intent(this, Songs.class);
		startActivity(intent);
	}
	
	public void IntentWorkers(View view) {
		Intent intent = new Intent(this, Workers.class);
		startActivity(intent);
	}
	
}
