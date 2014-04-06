package com.example.mandawiki;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SongText extends Fragment {
	public static final String ARG_URL = "url_argument";
	TextView songText;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myFragmentView;
		myFragmentView = inflater.inflate(R.layout.fragment_song_text, container, false);
		
		
		if(this.getArguments() != null) {
			Bundle bundle = this.getArguments();
			String url = bundle.getString(ARG_URL);
			WikiReader task = new WikiReader();
			songText = (TextView) myFragmentView.findViewById(R.id.song_text);
		    task.execute(url);
		}
		
		return myFragmentView;
	}
	
	
	private class WikiReader extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {
	    	String response = "";
	    	for (String url : urls) {
	        
	    		try {
	    			Document document = Jsoup.connect(url).get();
	    			
	    			Elements content = document.select("p");
	    			
	    			
	    			response = Jsoup.parse(content.toString().replaceAll("(?i)<br[^>]*>", "br2n")).text();
	    			response = response.replaceAll("br2n", "\n");
	    			
	    			
	        	} catch (Exception e) {
	        		
	        	}
	    	}
	    	return response;
	    }

    	@Override
    	protected void onPostExecute(String result) {
    		songText.setText(result);
    	}
	}
	
}