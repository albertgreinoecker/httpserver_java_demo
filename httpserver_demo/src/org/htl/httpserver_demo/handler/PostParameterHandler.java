package org.htl.httpserver_demo.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.htl.httpserver_demo.utils.HttpUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * A simple example how get paramters can be used
 */
public class PostParameterHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		HashMap<String, String> getParams = HttpUtils.postParamsToMap(httpExchange);
		
		OutputStream outputStream = httpExchange.getResponseBody();
		StringBuilder str = new StringBuilder();
		str.append("<html><body>");
		String query = httpExchange.getRequestURI().toString();
		str.append("Parameters are fetched from this String:<b>" + query + "</b>");
		str.append("<ul>");
		for (String name : getParams.keySet())
		{
			String pars = String.format("<li>%s : %s </li>" , name, getParams.get(name));
			str.append(pars);
			
		}
		str.append("</ul></body></html>");
		httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
		httpExchange.sendResponseHeaders(200, str.length()); //200, everything went ok
		outputStream.write(str.toString().getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
