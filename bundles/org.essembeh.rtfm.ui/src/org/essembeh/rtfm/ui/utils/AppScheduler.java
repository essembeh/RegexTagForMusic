package org.essembeh.rtfm.ui.utils;

import org.eclipse.swt.widgets.Display;

public class AppScheduler {

	public static void runInUiThread(boolean sync, Runnable r) {
		Display d = Display.getDefault();
		if (sync) {
			d.syncExec(r);
		} else {
			d.asyncExec(r);
		}
	}
}
