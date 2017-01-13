package org.essembeh.rtfm.ui.widgets.status;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.essembeh.rtfm.ui.SharedImages;

public class StatusLabelProvider extends LabelProvider {

	private static final Map<Integer, Image> IMAGES = new HashMap<>();

	static {
		IMAGES.put(IStatus.OK, SharedImages.getImage(SharedImages.ACCEPT));
		IMAGES.put(IStatus.INFO, SharedImages.getImage(SharedImages.LIGHTBULB_OFF));
		IMAGES.put(IStatus.WARNING, SharedImages.getImage(SharedImages.CANCEL));
		IMAGES.put(IStatus.ERROR, SharedImages.getImage(SharedImages.CANCEL));
		IMAGES.put(IStatus.CANCEL, SharedImages.getImage(SharedImages.CANCEL));
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IStatus) {
			return ((IStatus) element).getMessage();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof IStatus) {
			return IMAGES.get(((IStatus) element).getSeverity());
		}
		return super.getImage(element);
	}
}
