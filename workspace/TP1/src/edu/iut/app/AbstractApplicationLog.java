package edu.iut.app;

import java.util.ArrayList;

public abstract class AbstractApplicationLog implements IApplicationLog {

	protected String message;

    protected Collection<IApplicationLogListener> tabI
    
    protected /*TYPE TABLEAU*/ listeners;
	
	public AbstractApplicationLog() {
		message = null;
		listeners = new /*TYPE TABLEAU*/();
	}
	
    abstract void setMessage(String message);

	/** Listener action */
	protected void fireMessage(String level, String message) {
		for (IApplicationLogListener listener_i : listeners) {
			listener_i.newMessage(level, message);
		}
	}
}
