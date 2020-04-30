module ChatModule {
	opens com.DataModels;
	opens com.JavaShell;
	opens com.Helpers;
	
	requires jdk.jshell;
	requires javax.servlet.api;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	requires javax.websocket.api;
	requires java.logging;
	requires jackson.annotations;
}