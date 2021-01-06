package org.htl.httpserver_demo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		System.out.println(query);
		
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

	/**
	 * basically taken from
	 * https://stackoverflow.com/questions/35983807/java-httpserver-handling-post-requests-and-read-the-html-form-information/36536076
	 * Get all post parameters
	 * @param query
	 * @return
	 * @throws IOException
	 */
	public static HashMap<String, String> postParamsToMap(HttpExchange httpExchange) throws IOException {
		HashMap<String, String> result = new HashMap<String, String>();
		Headers requestHeaders = httpExchange.getRequestHeaders();

		for (Entry<String, List<String>> e : requestHeaders.entrySet()) {
			String name = e.getKey();
			String value = e.getValue().get(0);
			result.put(name, value);
		}
		return result;
	}
}
