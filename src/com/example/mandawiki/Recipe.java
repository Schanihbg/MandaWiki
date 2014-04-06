package com.example.mandawiki;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Recipe extends Fragment {
	
	private List list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myFragmentView;
		myFragmentView = inflater.inflate(R.layout.fragment_recipe, container, false);
		
		Bundle bundle = this.getArguments();
		int number = bundle.getInt("number");
		list = new List(number);
		
		String recipe = editRecipe(list);
		
		TextView text = (TextView) myFragmentView.findViewById(R.id.recipe_textView);
		
		text.setText(recipe);
		
		return myFragmentView;
	}
	
	private String editRecipe(List list){
		String recipe = getResources().getString(R.string.recipe_text);
		
		recipe = recipe.replace("[kottfars]", Float.toString(list.getKottfars()));
		recipe = recipe.replace("[lok]", Float.toString(list.getLok()));
		recipe = recipe.replace("[vitlok]", Float.toString(list.getVitlok()));
		recipe = recipe.replace("[chilisas]", Float.toString(list.getChilisas()));
		recipe = recipe.replace("[chilipulver]", Float.toString(list.getChilipulver()));
		recipe = recipe.replace("[peppar]", Float.toString(list.getPeppar()));
		recipe = recipe.replace("[ortsalt]", Float.toString(list.getOrtsalt()));
		recipe = recipe.replace("[tabasco]", Float.toString(list.getTabasco()));
		recipe = recipe.replace("[oregano]", Float.toString(list.getOregano()));
		recipe = recipe.replace("[sas_filmjolk]", Float.toString(list.getSas_filmjolk()));
		recipe = recipe.replace("[sas_cremefraiche]", Float.toString(list.getSas_cremefraiche()));
		recipe = recipe.replace("[sas_philadelphia]", Float.toString(list.getSas_philadelphia()));
		recipe = recipe.replace("[sas_ortsalt]", Float.toString(list.getSas_ortsalt()));
		recipe = recipe.replace("[sas_vitlok]", Float.toString(list.getSas_vitlok()));
		
		return recipe;
	}
	
	public List getList() {
		return list;
	}

}
