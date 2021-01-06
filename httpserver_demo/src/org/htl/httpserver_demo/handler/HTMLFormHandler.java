package org.htl.httpserver_demo.handler;

import java.io.IOException;
import java.io.OutputStream;

import org.htl.httpserver_demo.utils.FileUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Simple HTML is generated within this handler
 */
public class HTMLFormHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException  {

		OutputStream outputStream = httpExchange.getResponseBody();
		String str = null;
		try {
			str = FileUtils.readTextFromClassPath("/org/htl/httpserver_demo/resources/example_form.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
		httpExchange.sendResponseHeaders(200, str.length()); //200, everything went ok
		outputStream.write(new String(str.getBytes(), "UTF-8").getBytes());
		outputStream.flush();
		outputStream.close();
	}
}
