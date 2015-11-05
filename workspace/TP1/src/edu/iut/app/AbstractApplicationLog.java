package edu.iut.app;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractApplicationLog implements IApplicationLog {

	protected String message;
    
    protected Collection<IApplicationLogListener> listeners;
	
	public AbstractApplicationLog() {
		message = null;
		listeners = new ArrayList<IApplicationLogListener>();
	}
	
    public abstract void setMessage(String message);

	protected void fireMessage(String level, String message) {
		for (IApplicationLogListener listener_i : listeners) {
			listener_i.newMessage(level, message);
		}
	}
	
	public void ajouterListener(IApplicationLogListener l) {
		listeners.add(l);
	}
	
	public Collection<IApplicationLogListener> getListeners() {
		return listeners;
	}
}
