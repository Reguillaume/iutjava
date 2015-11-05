package edu.iut.app;

import java.util.ArrayList;
import java.util.Collection;

public class ApplicationErrorLog extends AbstractApplicationLog {

	protected Collection<IApplicationLogListener> listeners;
	
	public ApplicationErrorLog() {
		super();
		listeners=new ArrayList<IApplicationLogListener>();
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
		super.fireMessage("[ERROR]", this.message);
        for(IApplicationLogListener l : listeners) l.newMessage("[ERREUR]", this.message);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void addListener(IApplicationLogListener listener) {
		listeners.add(listener);
		super.ajouterListener(listener);
	}

	@Override
	public Collection<IApplicationLogListener> getApplicationLogListeners() {
		return listeners;
	}


}
