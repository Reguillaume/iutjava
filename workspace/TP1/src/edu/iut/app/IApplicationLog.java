package edu.iut.app;

import java.util.Collection;

public interface IApplicationLog {

    void setMessage(String message);
    String getMessage();
    void addListener(IApplicationLogListener listener);
    Collection<IApplicationLogListener> getApplicationLogListeners();
    
}
