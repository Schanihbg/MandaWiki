package com.example.mandawiki;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SongList extends Fragment {
	ListView songList;
	String[][] urlList;
	ArrayAdapter<String> adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myFragmentView;
		myFragmentView = inflater.inflate(R.layout.fragment_song_list, container, false);
		
		songList = (ListView) myFragmentView.findViewById(R.id.song_list);
		urlList = new String[2][0];
		
		WikiReader task = new WikiReader();
	    task.execute("http://xn--mnda-qoa.se/wiki/index.php/Kategori:S%C3%A5nger");
	    
	    songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> parent, final View view,
	            int position, long id) {
	        	String hit = (String) parent.getItemAtPosition(position);
	        	for(int i = 0; i < urlList[0].length; i++) {
	        		if(urlList[0][i] == hit) {
	        			Activity activity = getActivity();
	        			if(activity instanceof Songs) {
	        				((Songs) activity).viewSong(urlList[1][i]);
	        			}
	        			break;
	        		}
	        	}
	        }

	      });
	    
		return myFragmentView;
	}
	

	private class WikiReader extends AsyncTask<String, Void, String[]> {
		@Override
		protected String[] doInBackground(String... urls) {
	    	String response = "", urlResponse = "";
	    	String[] result = null;
	    	for (String url : urls) {
	        
	    		try {
	    			Document document = Jsoup.connect(url).get();
	    			
	    			Elements content = document.select("tbody");
	    			Elements songs = content.select("a[href");
	    			
	    			for(org.jsoup.nodes.Element song : songs) {
	    				response += song.text() + "\n";
	    				urlResponse += song.attr("abs:href") + "\n";
	    			}
	    			result = response.split(System.getProperty("line.separator"));
	    			urlList[0] = result;
	    			urlList[1] = urlResponse.split(System.getProperty("line.separator"));
	        	} catch (Exception e) {
	        		
	        	}
	    	}
	    	return result;
	    }

    	@Override
    	protected void onPostExecute(String[] result) {
    		adapter = new ArrayAdapter<String>(getActivity(),
    		        android.R.layout.simple_list_item_1, result);
    	    songList.setAdapter(adapter);
    	}
	}
	
}
