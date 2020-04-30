package com.JavaShell;

import com.Helpers.Display;
import com.Helpers.DisplayResult;

public class JShellResult {
	
	private String error;
	
	private String result;
	
	private String streamOutput;
	
	private DisplayResult displayParameters;
	
	public JShellResult(String result, String error) {
		this(result, error, null);
	}
	
	public JShellResult(String result, String error, String streamOutput) {
		this.result = result;
		this.error = error;
		this.streamOutput = streamOutput;
		if (this.streamOutput != null) {
			this.displayParameters = Display.extractDisplayparameters(this.streamOutput);
			if (this.displayParameters != null) {
				this.streamOutput = this.displayParameters.getOriginalStreamOutput();
			}
		}
	}
	
	public String getError() {
		return this.error;
	}
	
	public String getResult() {
		return this.result;
	}

	public String getStreamOutput() {
		return this.streamOutput;
	}

	public DisplayResult getDisplayParameters() {
		return this.displayParameters;
	}

	@Override
	public String toString() {
		return "\nerror: " + (error == null ? "null" : error) + ", result: " + (result == null ? "null" : result) + ", streamOutput: " + (streamOutput == null ? "null" : streamOutput);
	}
}
