package com.DataModels;

public enum ChannelNames {
    Shell("shell"),
    IoPub("iopub"),
    Stream("stream"),
    Error("error");
    
    private final String name;
    
    private ChannelNames (String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return this.name;
    }
}
