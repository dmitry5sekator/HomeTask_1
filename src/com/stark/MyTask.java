package com.stark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MyTask extends AsyncTask<Void,Integer, ArrayList<Item>>{

	@Override
	protected ArrayList<Item> doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		String source = getData();
		ArrayList<Item> items = new ArrayList<Item>();
		////////////////////////
		////////////////////////
		//////////////////////////
		try
    	{
    		String x = "";
    		JSONArray entries = new JSONArray(source.substring(source.indexOf("[")));
    		TreeMap <String,Item> myMap = new TreeMap<String,Item>();
    		ArrayList <HashMap<String, Object>> myBooks = new ArrayList<HashMap<String,Object>>();
    		TreeMap<Integer,Item> myB = new TreeMap<Integer,Item>();
    		HashMap<String, Object> hm;
    		double a = entries.length();
    		double b = 100;
    		double len = b / a;
    		float progress = 0;;
    		for (int i=0;i<entries.length();i++)
    		{
    			progress+= len;
    			JSONObject post = entries.getJSONObject(i);
    			String urlIcon = post.getString("make_icon");
    			Bitmap test = getDrawableFromUrl(urlIcon);
    			myB.put(post.getInt("id"), new Item(post.getString("id"),
    												post.getString("name"),
    												post.getString("url"),
    												test));
    			publishProgress((int)progress);
    		}
    		for(Entry<Integer, Item> entry : myB.entrySet()) 
    		{
    			Integer key = entry.getKey();
    			Item value = entry.getValue();
    			items.add(value);
    		}
    	}
    	catch (Exception je)
    	{
    		
    	}
		return items;
	}
	@Override
	protected void onProgressUpdate(Integer... values) {
	super.onProgressUpdate(values);
	ListviewActivity.progressBar.setProgress(values[0]);
	}
	@Override
	protected void onPostExecute(ArrayList<Item> result) {
	super.onPostExecute(result);
	ListviewActivity.xxx = result;
	ListviewActivity.startUpList();
	}
public String getData(){
		String begin="";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://buyersguide.caranddriver.com/api/feed/?mode=json&q=make");
        try{
            HttpResponse response = client.execute(request);
            //txtResult.setText(request(response));
            begin = request(response);
            //examineJSONFile(begin);
            //txtResult.setText(request(response));
        }catch(Exception ex){
            //txtResult.setText("Failed!");
        	return "";
        }
        return begin;
    }
    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }
    private Bitmap getDrawableFromUrl(String url)
    {
    	Bitmap bmImg = null;
    	URL myFileUrl =null; 
    	Drawable d = null;
    	try 
    	{
    		myFileUrl= new URL(url);
    		HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
    		conn.setDoInput(true);
    		conn.connect();
    		InputStream is = conn.getInputStream();
    		bmImg = BitmapFactory.decodeStream(is);
    		d =new BitmapDrawable(bmImg);
    		//ImageView view = (ImageView)findViewById(R.id.caricon);
    		//view.setImageBitmap(bmImg);
    	 }
    	catch (IOException e) 
    	{
    		e.printStackTrace();
    	}
    	return bmImg;
	}

	
}
