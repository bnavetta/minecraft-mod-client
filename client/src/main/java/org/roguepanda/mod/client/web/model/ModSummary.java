package org.roguepanda.mod.client.web.model;

/**
 * Represents the mod information returned by search and recent api calls
 * @author ben
 *
 */
public class ModSummary
{
	private String name;
	private Long id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString()
	{
		return id + ": " + name;
	}
}
