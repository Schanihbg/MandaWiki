package com.example.mandawiki;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class YakatanaRecipe extends ActionBarActivity {
	
	Button listButton;
	Recipe recipe;
	
	public final static String EXTRA_MESSAGE = "com.example.YakatanaRecipe.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yakatana_recipe);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

		listButton = (Button) findViewById(R.id.list_button);
		listButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showShoppingList(YakatanaRecipe.this);
				
			}
			
		});
		
		getSupportActionBar().hide();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.yakatana_recipe, menu);
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

	public void generateRecpie(View view) {
		
		InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE); 
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                   InputMethodManager.HIDE_NOT_ALWAYS);

		Recipe oldRecipe = (Recipe) getSupportFragmentManager().findFragmentById(R.id.recipe_container);
		
		if(oldRecipe != null){
			try {
				recipe = new Recipe();
			
				EditText textEdit = (EditText)findViewById(R.id.yakatana_edit_message);
				String text = textEdit.getText().toString();
			
				int number = Integer.parseInt(text);
				Bundle args = new Bundle();
		        args.putInt("number", number);
				recipe.setArguments(args);
				
				listButton.setVisibility(View.VISIBLE);
				
				getSupportFragmentManager().beginTransaction().add(R.id.container, recipe).commit();
			} 
			catch (NumberFormatException e) {
				new AlertDialog.Builder(this)
			    	.setTitle("Error")
			    	.setMessage("Ange endast siffror.")
			    	.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			    		public void onClick(DialogInterface dialog, int which) { 
			    			// do nothing
			    		}
			    	})
			    	.show();
			}
		}
		else {
			try {
				recipe = new Recipe();
			
				EditText textEdit = (EditText)findViewById(R.id.yakatana_edit_message);
				String text = textEdit.getText().toString();
			
				int number = Integer.parseInt(text);
				Bundle args = new Bundle();
		        args.putInt("number", number);
				recipe.setArguments(args);
				
				listButton.setVisibility(View.VISIBLE);
				
				FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
				
				transaction.replace(R.id.container, recipe);
				//transaction.addToBackStack(null);
				
				transaction.commit();
			} 
			catch (NumberFormatException e) {
				new AlertDialog.Builder(this)
		    	.setTitle("Error")
		    	.setMessage("Ange endast siffror.")
		    	.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog, int which) { 
		    			// do nothing
		    		}
		    	})
		    	.show();
			}
		}
		
	}
	
	
	public void showShoppingList(final Activity context){
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		// Inflate the popup_layout.xml
	    LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
	    
	    LayoutInflater layoutInflater = (LayoutInflater) context
	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View layout = layoutInflater.inflate(R.layout.activity_shopping_list, viewGroup);

	    setListText(layout);
	    
	    // Creating the PopupWindow
	    final PopupWindow popup = new PopupWindow(context);
	    popup.setContentView(layout);
	    popup.setWidth(width - 20);
	    popup.setHeight(height - 60);
	    popup.setFocusable(true);

	    // Displaying the popup at the specified location, + offsets.
	    popup.showAtLocation(layout, Gravity.NO_GRAVITY, 10, 50);
	    
	    
	}

	private void setListText(View layout) {
		List list = recipe.getList();
		TextView kottfars, chilisas, chilipulver, peppar, ortsalt, oregano, tabasco, filmjolk, cremefraiche,
			philadelphia, lok, vitlok, gurka, tomat, ananas, sallad, chips;
		String text;
		
		kottfars = (TextView) layout.findViewById(R.id.list_kottfars);
		text = Float.toString(list.getKottfars()) + getString(R.string.list_kottfars_text);
		kottfars.setText(text);
		
		chilisas = (TextView) layout.findViewById(R.id.list_chilisas);
		text = Integer.toString((int) Math.ceil(list.getChilisas())) + " " + getResources()
				.getQuantityString(R.plurals.list_chilisas_text, (int)Math.ceil(list.getChilisas()));
		chilisas.setText(text);
		
		chilipulver = (TextView) layout.findViewById(R.id.list_chilipulver);
		text = Integer.toString((int) list.getChilipulver()) + getString(R.string.list_chilipulver_text);
		chilipulver.setText(text);
		
		peppar = (TextView) layout.findViewById(R.id.list_peppar);
		text = Integer.toString((int) list.getPeppar()) + getString(R.string.list_peppar_text);
		peppar.setText(text);
		
		ortsalt = (TextView) layout.findViewById(R.id.list_ortsalt);
		text = Integer.toString((int) (list.getOrtsalt() + list.getSas_ortsalt())) + getString(R.string.list_ortsalt_text);
		ortsalt.setText(text);
		
		oregano = (TextView) layout.findViewById(R.id.list_oregano);
		text = Integer.toString((int) list.getOregano()) + getString(R.string.list_oregano_text);
		oregano.setText(text);
		
		tabasco = (TextView) layout.findViewById(R.id.list_tabasco);
		text = Integer.toString((int) Math.ceil(list.getTabasco())) + " " + getResources()
				.getQuantityString(R.plurals.list_tabasco_text, (int)Math.ceil(list.getTabasco()));
		tabasco.setText(text);
		
		filmjolk = (TextView) layout.findViewById(R.id.list_filmjolk);
		text = Integer.toString((int) Math.ceil(list.getSas_filmjolk()/10)) + " " + getString(R.string.list_filmjolk_text);
		filmjolk.setText(text);
		
		cremefraiche = (TextView) layout.findViewById(R.id.list_cremefraiche);
		text = Integer.toString((int) list.getSas_cremefraiche()) + getString(R.string.list_cremefraiche_text);
		cremefraiche.setText(text);
		
		philadelphia = (TextView) layout.findViewById(R.id.list_philadelphia);
		text = Integer.toString((int) list.getSas_philadelphia()) + getString(R.string.list_philadelphia_text);
		philadelphia.setText(text);
		
		lok = (TextView) layout.findViewById(R.id.list_lok);
		text = Integer.toString((int) Math.ceil(list.getLok())) + " " + getResources()
				.getQuantityString(R.plurals.list_lok_text, (int)Math.ceil(list.getLok()));
		lok.setText(text);
		
		vitlok = (TextView) layout.findViewById(R.id.list_vitlok);
		text = Integer.toString((int) (list.getVitlok() + list.getSas_vitlok())) + " " + getString(R.string.list_vitlok_text);
		vitlok.setText(text);
		
		gurka = (TextView) layout.findViewById(R.id.list_gurka);
		text = Integer.toString((int) Math.ceil(list.getGurka())) + " " + getResources()
				.getQuantityString(R.plurals.list_gurka_text, (int)Math.ceil(list.getGurka()));
		gurka.setText(text);
		
		tomat = (TextView) layout.findViewById(R.id.list_tomat);
		text = Integer.toString((int) Math.ceil(list.getTomat())) + " " + getResources()
				.getQuantityString(R.plurals.list_tomat_text, (int)Math.ceil(list.getTomat()));
		tomat.setText(text);
		
		ananas = (TextView) layout.findViewById(R.id.list_ananas);
		text = Integer.toString((int) Math.ceil(list.getAnanas())) + " " + getResources()
				.getQuantityString(R.plurals.list_ananas_text, (int)Math.ceil(list.getAnanas()));
		ananas.setText(text);
		
		sallad = (TextView) layout.findViewById(R.id.list_sallad);
		text = Integer.toString((int) Math.ceil(list.getSallad())) + " " + getResources()
				.getQuantityString(R.plurals.list_sallad_text, (int)Math.ceil(list.getSallad()));
		sallad.setText(text);
		
		chips = (TextView) layout.findViewById(R.id.list_chips);
		text = Integer.toString((int) Math.ceil(list.getChips())) + " " + getResources()
				.getQuantityString(R.plurals.list_chips_text, (int)Math.ceil(list.getChips()));
		chips.setText(text);
	}
}
