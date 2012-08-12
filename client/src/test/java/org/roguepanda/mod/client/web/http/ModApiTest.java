package org.roguepanda.mod.client.web.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.roguepanda.mod.client.web.http.RestService.HttpException;
import org.roguepanda.mod.client.web.model.Mod;
import org.roguepanda.mod.client.web.model.ModSummary;

public class ModApiTest
{
	private ModApi api;
	
	@Before
	public void setUp() throws Exception
	{
		api = new ModApi();
	}

	@Test
	public void testBaseURL()
	{
		assertEquals("http://modrepository.cloudfoundry.com/api", ModApi.DEFAULT_BASE_URL);
		assertEquals(ModApi.DEFAULT_BASE_URL, api.getBaseURL());
		
		api.setBaseURL("http://www.example.com/api/");
		assertEquals("Trailing slash not removed:", "http://www.example.com/api", api.getBaseURL());
	}

	@Test
	public void testSearch() throws IOException, HttpException
	{
		api.setBaseURL("http://localhost:8080/mod-repo/api"); //we can control local data
		List<ModSummary> mods = api.search("mod");
		assertNotNull(mods);
		assertEquals("Number of results: ", 1, mods.size());
		ModSummary mod = mods.get(0);
		assertNotNull(mod);
		
		assertEquals("Epic Mod", mod.getName());
		//can't test id because database ids aren't stable
	}

	@Test
	public void testRecent() throws IOException, HttpException
	{
		api.setBaseURL("http://localhost:8080/mod-repo/api"); //we can control local data
		List<ModSummary> mods = api.recent(10);
		assertNotNull(mods);
		assertEquals("Number of results: ", 1, mods.size());
		ModSummary mod = mods.get(0);
		assertNotNull(mod);
		
		assertEquals("Epic Mod", mod.getName());
		//can't test id because database ids aren't stable
	}

	@Test
	public void testDetails() throws IOException, HttpException
	{
		api.setBaseURL("http://localhost:8080/mod-repo/api"); //we can control local data
		//use search to get an id since it could change
		ModSummary summary = api.search("Epic Mod").get(0);
		
		Mod mod = api.details(summary.getId());
		assertNotNull(mod);
		assertEquals("Epic Mod", mod.getName());
		assertEquals(summary.getId(), mod.getId());
		//can't test dates because they will most likely change
		assertEquals("testUser", mod.getAuthorName());
		//can't test author id because it will most likely change
		assertTrue("Mod Description: ", mod.getDescription().contains("An epic mod")); //textareas tend to add extra whitespace
		assertEquals(new URL("http://www.epicmod.com"), mod.getHome());
	}

	@Test
	public void testBuildRecentURL()
	{
		String url = api.buildRecentURL(5);
		assertEquals("http://modrepository.cloudfoundry.com/api/recent.json?count=5", url);
	}

	@Test
	public void testBuildDetailsURL()
	{
		String url = api.buildDetailsURL(100L);
		assertEquals("http://modrepository.cloudfoundry.com/api/details/100.json", url);
	}

	@Test
	public void testBuildSearchURL()
	{
		String url = api.buildSearchURL("Epic Mod");
		assertEquals("http://modrepository.cloudfoundry.com/api/search.json?q=Epic+Mod", url);
		
		url = api.buildSearchURL("Foo");
		assertEquals("http://modrepository.cloudfoundry.com/api/search.json?q=Foo", url);
		
		url = api.buildSearchURL("Spécial");
		assertEquals("http://modrepository.cloudfoundry.com/api/search.json?q=Sp%C3%A9cial", url);
	}

}