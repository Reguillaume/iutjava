<<<<<<< HEAD
package edu.iut.gui.listeners;

import edu.iut.app.IApplicationLogListener;

public abstract class AbstractApplicationMessageDialog implements
		IApplicationLogListener {

	@Override
	public void newMessage(String level, String message) {
	 showMessage(level, message);

	}
	
	protected abstract void showMessage(String level, String message);

}
=======
package edu.iut.gui.listeners;

import edu.iut.app.IApplicationLogListener;

public abstract class AbstractApplicationMessageDialog implements
		IApplicationLogListener {

	@Override
	public void newMessage(String level, String message) {
	 showMessage(level, message);

	}
	
	protected abstract void showMessage(String level, String message);

}
>>>>>>> 606d363c0a78d99f0fd25a55d8f1fb14a6ccb183
