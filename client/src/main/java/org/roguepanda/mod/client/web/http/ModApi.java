package org.roguepanda.mod.client.web.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.roguepanda.mod.client.web.http.RestService.HttpException;
import org.roguepanda.mod.client.web.model.Mod;
import org.roguepanda.mod.client.web.model.ModSummary;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ModApi
{
	public static final String DEFAULT_BASE_URL = "http://modrepository.cloudfoundry.com/api";
	
	private static CollectionType MOD_SUMMARY_LIST_TYPE = TypeFactory.defaultInstance().constructCollectionType(List.class, ModSummary.class);
	
	private static JavaType MOD_TYPE = TypeFactory.defaultInstance().constructType(Mod.class);
	
	private RestService rest = new RestService();
	
	private String baseURL = DEFAULT_BASE_URL;

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL)
	{
		if(baseURL.endsWith("/"))
		{
			this.baseURL = baseURL.substring(0, baseURL.length() - 1);//remove trailing slash
		}
		else
		{
			this.baseURL = baseURL;
		}
	}
	
	public List<ModSummary> search(String query) throws IOException, HttpException
	{
		String url = buildSearchURL(query);
		return rest.getAsJsonObject(url, MOD_SUMMARY_LIST_TYPE);
	}
	
	public List<ModSummary> recent(int count) throws IOException, HttpException
	{
		String url = buildRecentURL(count);
		return rest.getAsJsonObject(url, MOD_SUMMARY_LIST_TYPE);
	}
	
	public Mod details(long id) throws IOException, HttpException
	{
		String url = buildDetailsURL(id);
		return rest.getAsJsonObject(url, MOD_TYPE);
	}
	
	public String buildRecentURL(int count)
	{
		return baseURL + "/recent.json?count=" + count; //an integer won't have any illegal characters, just digits
	}
	
	public String buildDetailsURL(long id)
	{
		return baseURL + "/details/" + id + ".json";
	}
	
	public String buildSearchURL(String query)
	{
		try
		{
			return baseURL + "/search.json?q=" + URLEncoder.encode(query, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException("All JAva implementations must support UTF-8");
		}
	}
}
