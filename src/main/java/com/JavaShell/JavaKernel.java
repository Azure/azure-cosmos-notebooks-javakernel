package com.JavaShell;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jdk.jshell.JShell;
import jdk.jshell.JShellException;
import jdk.jshell.SnippetEvent;
import jdk.jshell.SourceCodeAnalysis;
import jdk.jshell.Snippet.Status;
import jdk.jshell.execution.LocalExecutionControlProvider;



public class JavaKernel {

	private static HashMap<String, JShell> JShells = new HashMap<String, JShell>();

	public static String createJShell(String kernelId) {
		JShell jShell = JShell.builder().executionEngine(new LocalExecutionControlProvider(), null).build();
		String currentLocation;
		try {
			currentLocation = new File(JavaKernel.class.getProtectionDomain().getCodeSource().getLocation()
			    .toURI()).getPath();
			jShell.addToClasspath(currentLocation);
		} catch (URISyntaxException e) {
		}
		jShell.addToClasspath("C:\\Users\\srnara\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.9.1\\jackson-databind-2.9.1.jar");
		jShell.addToClasspath("C:\\Users\\srnara\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.9.1\\jackson-core-2.9.1.jar");
		jShell.addToClasspath("C:\\Users\\srnara\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.9.7\\jackson-annotations-2.9.7.jar");
		
		JShells.put(kernelId, jShell);
		return kernelId;
	}

	public static void removeJShell(String kernelId) {
		JShells.remove(kernelId);
	}

	public static void interruptJShell(String kernelId) {
		if (JShells.containsKey(kernelId)) {
			JShells.get(kernelId).stop();
		} else {
			throw new IllegalArgumentException("kernel with specified id not present");
		}
	}

	public static String getAllKernels()
	{
		return String.join(", ",JShells.keySet());
	}
	
	public static JShellResult getResult(String kernelId, String code) {
		JShell js = JShells.get(kernelId);
		if (js == null) {
			return new JShellResult(null, "Invalid invocation!");
		}
		SourceCodeAnalysis sca = js.sourceCodeAnalysis();
		List<String> snippets = new ArrayList<>();
		do {
		    SourceCodeAnalysis.CompletionInfo info = sca.analyzeCompletion(code);
		    snippets.add(info.source());
		    code = info.remaining();
		} while (code.length() > 0);
		
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream modifiedOutputPrintStream = new PrintStream(byteArrayOutputStream);
		PrintStream originalOutputPrintString = System.out;
		System.setOut(modifiedOutputPrintStream);
		
		ByteArrayOutputStream byteArrayErrorStream = new ByteArrayOutputStream();
		PrintStream modifiedErrorPrintStream = new PrintStream(byteArrayErrorStream);
		PrintStream originalErrorPrintString = System.err;
		System.setErr(modifiedErrorPrintStream);

		List<SnippetEvent> events = new ArrayList<SnippetEvent>();
		for (String snippet : snippets) {
			List<SnippetEvent> snippetEvents = js.eval(snippet);
			JShellException exception = snippetEvents.get(0).exception();
			if (exception != null) {
				return new JShellResult(null, exception.toString());
			}
			events.addAll(snippetEvents);
		}
		
		System.out.flush();
		System.err.flush();
		System.setOut(originalOutputPrintString);
		System.setErr(originalErrorPrintString);
		
		String streamOutput = byteArrayOutputStream.toString();
		if (streamOutput != null && streamOutput.equals("")) {
			streamOutput = null;
		}
		
		String errorOutput = byteArrayErrorStream.toString();
		if (errorOutput != null && errorOutput.length() != 0) {
			return new JShellResult(null, errorOutput, streamOutput);
		}
		
		if (events.size() == 0) {
			return new JShellResult("", null);
		}
		
		for (SnippetEvent e : events) {
			StringBuilder sb = new StringBuilder();
			if (e.causeSnippet() == null) {
				// We have a snippet creation event
				switch (e.status()) {
				case VALID:
					sb.append("Successful ");
					break;
				case RECOVERABLE_DEFINED:
					sb.append("With unresolved references ");
					break;
				case RECOVERABLE_NOT_DEFINED:
					sb.append("Possibly reparable, failed  ");
					break;
				case REJECTED:
					sb.append("Failed ");
					break;
				}
				if (e.previousStatus() == Status.NONEXISTENT) {
					sb.append("addition");
				} else {
					sb.append("modification");
				}
				sb.append(" of ");
				sb.append(e.snippet().source());
				System.out.println(sb);
			}
			
			StringBuilder errors = new StringBuilder();
			js.diagnostics(e.snippet()).forEach(
					  d -> errors.append(d.getMessage(Locale.getDefault()) + "\n")
		    );
			String error = errors.toString();
			if (error.length() != 0) {
				System.out.println(error);
				return new JShellResult(null, error);
			}
		}
		return new JShellResult(events.get(events.size() - 1).value(), null, streamOutput);
	}
}

