package org.essembeh.rtfm.core.actions;

import org.essembeh.rtfm.core.actions.listener.IJobListener;
import org.essembeh.rtfm.core.utils.listener.IListenable;

public interface IJob extends IListenable<IJobListener> {

	void submit();

}
