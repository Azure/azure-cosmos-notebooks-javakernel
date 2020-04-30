package com.DataModels;

public enum ExecuteStatus {
    Ok("ok"),
    Error("error"),
    Abort("abort");
    
    private final String name;
    
    private ExecuteStatus (String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return this.name;
    }
}
