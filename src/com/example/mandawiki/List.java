package com.example.mandawiki;

public class List {
	
	private float kottfars, lok, vitlok, chilisas, chilipulver, peppar, ortsalt, tabasco, 
		oregano, sas_filmjolk, sas_cremefraiche, sas_philadelphia, sas_ortsalt, sas_vitlok, tomat, gurka, ananas, sallad, chips;
	
	public List(int number) {
		float newNumber = number / 3.0f;
		
		kottfars 			= newNumber * 1/2;		//gram
		lok 				= newNumber * 1;		//st
		vitlok				= newNumber * 2;		//klyftor
		chilisas			= newNumber * 1/2;		//flaska
		chilipulver			= newNumber * 1;		//tsk
		peppar				= newNumber * 1;		//tsk
		ortsalt				= newNumber * 1;		//tsk
		tabasco				= newNumber * 1/6;		//flaska
		oregano				= newNumber * 1;		//tsk
		sas_filmjolk		= newNumber * 1;		//dl
		sas_cremefraiche 	= newNumber * 2;		//dl
		sas_philadelphia	= newNumber * 200;		//gram
		sas_ortsalt			= newNumber * 1;		//tsk
		sas_vitlok			= newNumber * 2;		//klyftor
		tomat 				= newNumber * 2;		//st
		gurka 				= newNumber * 1/2;		//st
		ananas 				= newNumber * 1;		//st
		sallad 				= newNumber * 1/4;		//st
		chips 				= newNumber * 1;		//st
		
	}
	
	public float getKottfars() {
		return kottfars;
	}

	public float getLok() {
		return lok;
	}

	public float getVitlok() {
		return vitlok;
	}

	public float getChilisas() {
		return chilisas;
	}

	public float getChilipulver() {
		return chilipulver;
	}

	public float getPeppar() {
		return peppar;
	}

	public float getOrtsalt() {
		return ortsalt;
	}

	public float getTabasco() {
		return tabasco;
	}

	public float getOregano() {
		return oregano;
	}

	public float getSas_filmjolk() {
		return sas_filmjolk;
	}

	public float getSas_cremefraiche() {
		return sas_cremefraiche;
	}

	public float getSas_philadelphia() {
		return sas_philadelphia;
	}

	public float getSas_ortsalt() {
		return sas_ortsalt;
	}

	public float getSas_vitlok() {
		return sas_vitlok;
	}
	
	public float getTomat() {
		return tomat;
	}

	public float getGurka() {
		return gurka;
	}

	public float getAnanas() {
		return ananas;
	}

	public float getSallad() {
		return sallad;
	}

	public float getChips() {
		return chips;
	}
}
