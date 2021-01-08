package org.htl.httpserver_demo;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.htl.httpserver_demo.handler.GetParameterHandler;
import org.htl.httpserver_demo.handler.HTMLFormHandler;
import org.htl.httpserver_demo.handler.HTMLFormProcessorHandler;
import org.htl.httpserver_demo.handler.HeaderParameterHandler;
import org.htl.httpserver_demo.handler.HomeHandler;
import org.htl.httpserver_demo.handler.SimpleHtmlHandler;

/*
 * These imports are hidden by eclipse:
 * Configure problem severity => Configure Project => Forbidden Reference => ignore
 */
import com.sun.net.httpserver.HttpServer;

/**
 * JDK HTTP server (since Java 1.6)
 */
public class TestServer {
	public static void main(String[] args) throws IOException {
		//Configure to start server at port 8008
		HttpServer server = HttpServer.create(new InetSocketAddress(8008), 0);
		server.createContext("/", new HomeHandler());
		server.createContext("/simple_html", new SimpleHtmlHandler());
		server.createContext("/simple_get", new GetParameterHandler());
		server.createContext("/header_parameters", new HeaderParameterHandler());
		server.createContext("/simple_form", new HTMLFormHandler());
		server.createContext("/simple_form_processor", new HTMLFormProcessorHandler());
		//Starts this server in a new background thread. Program still alive
		server.start();

		System.out.println(" Server started on port 8008");

	}
}
