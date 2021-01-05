package org.htl.httpserver_demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileUtils {
	public static String readTextFromClassPath(String filePath) throws IOException {
		InputStream is = FileUtils.class.getResourceAsStream(filePath);
		System.out.println(is);
		return readTextFromInputStream(is);
	}

	public static String readTextFromInputStream(InputStream is) throws IOException {
		StringBuilder str = new StringBuilder();

		Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		int c = 0;
		while ((c = reader.read()) != -1) {
			str.append((char) c);
		}
		return str.toString();
	}
}
