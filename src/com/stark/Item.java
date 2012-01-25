package com.stark;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Item 
{
	private String id;
	private String name;
	private String url;
	private Bitmap icon;
	public Item(String id,String name,String url,Bitmap icon)
	{
		this.id = id;
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
	public Bitmap getIcon()
	{
		return icon;
	}
	public String getId()
	{
		return id;
	}
}
