package com.DataModels;

public enum MessageTypes {
	ExecuteRequest("execute_request"),
	ExecuteInput("execute_input"),
	ExecuteResult("execute_result"),
	ExecuteReply("execute_reply"),
	Error("error"),
	Status("status"),
	Stream("stream"),
	KernelInfoRequest("kernel_info_request"),
	KernelInfoReply("kernel_info_reply"),
	DisplayData("display_data"),
	ClearOutput("clear_output");
    
    private final String name;
    
    private MessageTypes (String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return this.name;
    }
}
