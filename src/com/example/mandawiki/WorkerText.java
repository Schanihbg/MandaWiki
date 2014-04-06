package com.example.mandawiki;


import java.io.InputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkerText extends Fragment {
	public static final String ARG_URL = "url_argument";
	TextView workerText, workerFacts;
	ImageView imageView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myFragmentView;
		myFragmentView = inflater.inflate(R.layout.fragment_worker_text, container, false);
		
		
		if(this.getArguments() != null) {
			Bundle bundle = this.getArguments();
			String url = bundle.getString(ARG_URL);
		
			workerText = (TextView) myFragmentView.findViewById(R.id.worker_text);
			workerFacts = (TextView) myFragmentView.findViewById(R.id.worker_facts_text);
			imageView = (ImageView) myFragmentView.findViewById(R.id.portrait_view);
			WikiReader task = new WikiReader();
		    task.execute(url);
		}
		
	    
		return myFragmentView;
	}
	
	
	private class WikiReader extends AsyncTask<String, Void, Parcel> {
		@Override
		protected Parcel doInBackground(String... urls) {
			Parcel parcel = Parcel.obtain();
	    	String[] text = new String[7];
	    	text[6] = "false";
	    	Bitmap portrait = null;
	    	for (String url : urls) {
	        
	    		try {
	    			Document document = Jsoup.connect(url).get();
	    			
	    			Elements facts = document.select("tbody");
	    			facts = facts.select("b");
	    			Elements content = document.select("p");
	    			int i = 0;
	    			for(org.jsoup.nodes.Element fact : facts) {
	    				text[i] = fact.text();
	    				i++;
	    			}
	    			
	    			text[5] = Jsoup.parse(content.toString().replaceAll("(?i)<br[^>]*>", "br2n")).text();
	    			text[5] = text[5].replaceAll("br2n", "\n");
	    			text[5] = text[5].replaceAll("Kategori: Arbetare", "");
	    			
	    			Elements images = document.select("[src]");
	    			
	    			for(Element image : images) {
	    				if (image.tagName().equals("img")) {
	    					if(((String)image.attr("abs:src")).contains("images/thumb")){
	    						try {
	    				            InputStream in = new java.net.URL(image.attr("abs:src")).openStream();
	    				            portrait = BitmapFactory.decodeStream(in);
	    				            text[6] = "true";
	    				        } catch (Exception e) {
	    				            e.printStackTrace();
	    				        }
	    					}
	    				}
	    			}
	    			
	        	} catch (Exception e) {
	        		
	        	}
	    		parcel.writeStringArray(text);
	    		if(text[6].equals("true")) {
	    			portrait.writeToParcel(parcel, 0);
	    		}
	    		parcel.setDataPosition(0);
	    	}
	    	return parcel;
	    }

    	@Override
    	protected void onPostExecute(Parcel parcel) {
    		String[] result = parcel.createStringArray();
    		
    		String text = getString(R.string.workers_facts);
    		text = text.replace("[name]", result[0]);
    		text = text.replace("[nick]", result[1]);
    		text = text.replace("[generation]", result[2]);
    		text = text.replace("[born]", result[3]);
    		text = text.replace("[class]", result[4]);
    		
    		workerFacts.setText(text);
    		if(result[6].equals("true")) {
    			imageView.setImageBitmap(Bitmap.CREATOR.createFromParcel(parcel));
    		}
    		workerText.setText(result[5]);
    	}
	}
	
}