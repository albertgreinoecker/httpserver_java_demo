package org.htl.httpserver_demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class HttpUtils {

	/**
	 * basically taken from:
	 * https://www.rgagnon.com/javadetails/java-get-url-parameters-using-jdk-http-server.html
	 * returns the url parameters in a map
	 */
	public static HashMap<String, String> getParamsToMap(HttpExchange httpExchange) {
		HashMap<String, String> result = new HashMap<String, String>();
		String query = httpExchange.getRequestURI().getQuery();
		if (query == null)
		{
			return result;
		}
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return processQuery(query);
	}

	/**
	 * basically taken from
	 * https://stackoverflow.com/questions/35983807/java-httpserver-handling-post-requests-and-read-the-html-form-information/36536076
	 * Get all post parameters
	 * 
	 * @param query
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String, String> headerParamsToMap(HttpExchange httpExchange) throws IOException {
		HashMap<String, String> result = new HashMap<String, String>();
		Headers requestHeaders = httpExchange.getRequestHeaders();

		for (Entry<String, List<String>> e : requestHeaders.entrySet()) {
			String name = e.getKey();
			String value = e.getValue().get(0);
			result.put(name, value);
		}
		return result;
	}

	public static HashMap<String, String> postParamsToMap(HttpExchange httpExchange) throws IOException {
		InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String query = br.readLine();
		return processQuery(query);
	}

	/**
	 * Helper function to parse parameters form a query string
	 * 
	 * @param query e.g. first_name=test1&last_name=ttt
	 * @return Hashtable stuffed with <name, value>
	 */
	private static HashMap<String, String> processQuery(String query) {
		HashMap<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
}
