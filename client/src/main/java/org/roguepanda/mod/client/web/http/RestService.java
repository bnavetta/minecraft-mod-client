package org.roguepanda.mod.client.web.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestService
{
	@SuppressWarnings("serial")
	public static class HttpException extends Exception
	{
		private int status;
		
		public HttpException(int status, String message)
		{
			super(message);
			this.status = status;
		}
		
		public int getStatus()
		{
			return status;
		}
		
		public String toString()
		{
			return status + ": " + getMessage();
		}
	}
	
	private HttpClient client = new DefaultHttpClient();
	private ObjectMapper mapper = new ObjectMapper();
	
	public <T> T getAsJsonObject(String url, JavaType type) throws IOException, HttpException
	{
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		if(response.getStatusLine().getStatusCode() > 399) //400+ are errors
			throw new HttpException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
		return mapper.readValue(response.getEntity().getContent(), type);
	}
	
	public String getAsString(String url) throws IOException, HttpException
	{
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		if(response.getStatusLine().getStatusCode() > 399) //400+ are errors
			throw new HttpException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
		HttpEntity ent = response.getEntity();
		return entityBodyAsString(ent);
	}

	private String entityBodyAsString(HttpEntity entity) throws IOException
	{
		Charset charset = ContentType.getOrDefault(entity).getCharset();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		entity.writeTo(baos);
		return new String(baos.toByteArray(), charset);
	}
}
