package edu.iut.app;

import java.util.ArrayList;

public class ApplicationLogs extends ArrayList<IApplicationLog> {
	
	public ApplicationLogs() {
		new ArrayList<IApplicationLog>();
	}
	
	ArrayList<IApplicationLog> getErrors() {
		ArrayList<IApplicationLog> erreurLog=new ArrayList<IApplicationLog>();
		for(IApplicationLog i : this) {
			for(IApplicationLogListener j : i.getApplicationLogListeners()) {
				if (j instanceof ApplicationErrorLog) erreurLog.add((IApplicationLog) j);
			}
		}
		return erreurLog;
	}
	
	ArrayList<IApplicationLog> getWarnings() {
		ArrayList<IApplicationLog> warningLog=new ArrayList<IApplicationLog>();
		for(IApplicationLog i : this) {
			for(IApplicationLogListener j : i.getApplicationLogListeners()) {
				if (j instanceof ApplicationWarningLog) warningLog.add((IApplicationLog) j);
			}
		}
		return warningLog;
	}
	
	ArrayList<IApplicationLog> getInfos() {
		ArrayList<IApplicationLog> infosLog=new ArrayList<IApplicationLog>();
		for(IApplicationLog i : this) {
			for(IApplicationLogListener j : i.getApplicationLogListeners()) {
				if (j instanceof ApplicationInfoLog) infosLog.add((IApplicationLog) j);
			}
		}
		return infosLog;
	}
}
