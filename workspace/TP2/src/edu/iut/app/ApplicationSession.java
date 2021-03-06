package edu.iut.app;

import java.util.ResourceBundle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationSession {
	
	// Exercice 1 : G�rer l'internationation
	protected ResourceBundle resourceBundle;
	protected Locale locale;
	
	// Exercice 2 : Logger
	protected Logger sessionGuiLogger;
	protected Logger sessionExceptionLogger;


	private static ApplicationSession session = null;
	private ApplicationSession() {
		/* Definir US comme locale par défaut */
		Locale.setDefault(new Locale("US"));
		
		locale = Locale.getDefault();
		resourceBundle = ResourceBundle.getBundle("edu/iut/resources/strings/res", locale);
		sessionGuiLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
		sessionGuiLogger.setLevel(Level.ALL);
		sessionExceptionLogger = Logger.getAnonymousLogger();
		sessionExceptionLogger.setLevel(Level.ALL);
	}
	
	
	static public ApplicationSession instance() {
		if (session == null) {			
			session = new ApplicationSession();
		}
		return session;
	}
	
	public Logger getGUILogger() {
		return sessionGuiLogger;
	}
	public Logger getExceptionLogger() {
		return sessionExceptionLogger;
	}
	
	public void setLocale(Locale locale){
		this.locale = locale;
		Locale.setDefault(this.locale);
		resourceBundle = ResourceBundle.getBundle("edu/iut/resources/strings", locale);
	}
	
	public String getString(String key) {
		return resourceBundle.getString(key);
	}
	
	
}
