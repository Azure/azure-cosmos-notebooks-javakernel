package com.JavaShell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JShellServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		out.append("Connected");
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (request.getRequestURI().contains("interrupt")) {
			String kernelId = Paths.get(request.getRequestURI()).getParent().getFileName().toString();
			System.out.println("interrupt called on " + kernelId);
			JavaKernel.interruptJShell(kernelId);
		} else {
			StringBuffer jb = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null)
					jb.append(line);
			} catch (Exception e) {
				// report an error
			}

			JsonNode requestContent = new ObjectMapper().readTree(jb.toString());
			String kernelId = UUID.randomUUID().toString();
			PrintWriter out = response.getWriter();
			out.append("{'id': '" + kernelId + "', 'name': '" + requestContent.get("name").asText() + "'}");
		}
	}
}
