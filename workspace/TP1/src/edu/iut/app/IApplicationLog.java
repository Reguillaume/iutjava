package edu.iut.app;

public interface IApplicationLog {

    void setMessage(String message);
    String getMessage();
    void addListener(IApplicationLogListener listener);
    IApplicationLogListener[] getApplicationLogListeners();
    
}
