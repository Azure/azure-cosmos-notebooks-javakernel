package com.DataModels;

public enum ExecutionState {
    Busy("busy"),
    Idle("idle"),
    Starting("starting");
    
    private final String name;
    
    private ExecutionState (String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return this.name;
    }
}
