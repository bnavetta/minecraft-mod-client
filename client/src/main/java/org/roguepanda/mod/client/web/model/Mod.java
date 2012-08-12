package org.roguepanda.mod.client.web.model;

import java.net.URL;
import java.util.Date;

/**
 * Represents a mod (fully detailed)
 * @author ben
 *
 */
public class Mod
{
	private String name;
	private Long id;
	private String description;
	private Date created;
	private Date lastModified;
	private URL home;
	private String authorName;
	private Long authorId;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public URL getHome() {
		return home;
	}
	public void setHome(URL home) {
		this.home = home;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(": ");
		sb.append(name);
		sb.append("\n\t");
		sb.append(description);
		sb.append("\nCreated: ");
		sb.append(created);
		sb.append("\nModified: ");
		sb.append(lastModified);
		sb.append("\n");
		sb.append(home);
		sb.append("\n by ");
		sb.append(authorName);
		sb.append(" (");
		sb.append(authorId);
		sb.append(")");
		return sb.toString();
	}
}
