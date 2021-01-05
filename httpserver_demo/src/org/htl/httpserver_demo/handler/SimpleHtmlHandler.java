package org.htl.httpserver_demo.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Simple HTML is generated within this handler
 */
public class SimpleHtmlHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "<html><body><h1>Hello</h1></body></html>";
		httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
		httpExchange.sendResponseHeaders(200, htmlResponse.length()); //200, everything went ok
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
