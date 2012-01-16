package com.stark;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Item 
{
	private String name;
	private String url;
	private int icon;
	public Item(String name,String url,int icon)
	{
		this.name = name;
		this.url = url;
		this.icon = icon;
	}
	public String getName()
	{
		return name;
	}
	public String getUrl()
	{
		return url;
	}
	public int getIcon()
	{
		return icon;
	}
}
