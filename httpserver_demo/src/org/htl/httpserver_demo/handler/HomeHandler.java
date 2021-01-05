package org.htl.httpserver_demo.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.htl.httpserver_demo.utils.Link;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Simple HTML is generated within this handler
 */
public class HomeHandler implements HttpHandler {

	private ArrayList<Link> links = new ArrayList<>();

	public HomeHandler() {
		links.add(new Link("Simple Demo for generating HTML", "simple_html"));
		links.add(new Link("Simple Demo for dealing with GET Parameters", "simple_get"));
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		OutputStream outputStream = httpExchange.getResponseBody();
		StringBuilder str = new StringBuilder();
		str.append("<html><title> HttpServer Demo</title><body>");
		str.append("<ul>");
		for (Link link : links)
		{
			String linkStr = String.format("<li><a href='%s'>%s</a> </li>", link.getUrl(), link.getDisplayName());
			str.append(linkStr);
		}
		str.append("</ul");
		str.append("</body></html>");
		httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
		httpExchange.sendResponseHeaders(200, str.length()); // 200, everything went ok
		outputStream.write(str.toString().getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
