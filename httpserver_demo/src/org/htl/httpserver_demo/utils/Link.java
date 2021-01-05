package org.htl.httpserver_demo.utils;

/**
 * Holds information for a simple HTML link
 *
 */
public class Link {
	private String displayName;
	private String url;
		
	public Link(String displayName, String url) {
		this.displayName = displayName;
		this.url = url;
	}
	public String getDisplayName() {
		return displayName;
	}
	public String getUrl() {
		return url;
	}
	
	
}
