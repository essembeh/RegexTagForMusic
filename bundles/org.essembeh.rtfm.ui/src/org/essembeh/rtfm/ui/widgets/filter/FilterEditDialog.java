package org.essembeh.rtfm.ui.widgets.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.essembeh.rtfm.model.filter.INodeFilter;
import org.essembeh.rtfm.model.filter.NodeFilterFactory;

public class FilterEditDialog extends TitleAreaDialog {

	private final static String HELP_CONTENT;

	static {
		StringBuilder builder = new StringBuilder();
		builder.append(NodeFilterFactory.fromString("mandatory.attribute")).append("\n");
		builder.append(NodeFilterFactory.fromString("!other.attribute")).append("\n");
		builder.append(NodeFilterFactory.fromString("my.attribute=value")).append("\n");
		builder.append(NodeFilterFactory.fromString("my.attribute==Case Sentive Value")).append("\n");
		builder.append(NodeFilterFactory.fromString("my.attribute!=value")).append("\n");
		builder.append(NodeFilterFactory.fromString("my.attribute!==Another test")).append("\n");
		builder.append(NodeFilterFactory.fromString("my.attribute~S[oO]+m[3e] Reg(ex|eX) Here !*")).append("\n");
		builder.append(NodeFilterFactory.fromString("my.attribute!~.*error.*")).append("\n");
		HELP_CONTENT = builder.toString();
	}

	private Text filterText;
	private final List<INodeFilter> filters = new ArrayList<>();

	public FilterEditDialog(Shell parentShell, List<INodeFilter> filters) {
		super(parentShell);
		this.filters.addAll(filters);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Filter edition");
		setMessage("Set filters, one per line", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayoutFactory.swtDefaults().applyTo(container);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(container);
		filterText = new Text(container, SWT.MULTI);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(filterText);
		filterText.setToolTipText(HELP_CONTENT);
		filterText.setText(filters.stream().map(INodeFilter::toString).collect(Collectors.joining("\n")));
		return area;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	protected void updateFilters() {
		filters.clear();
		for (String line : StringUtils.split(filterText.getText(), "\n")) {
			if (StringUtils.isNotBlank(line)) {
				filters.add(NodeFilterFactory.fromString(line));
			}
		}
	}

	@Override
	protected void okPressed() {
		try {
			updateFilters();
			super.okPressed();
		} catch (IllegalArgumentException e) {
			setErrorMessage(e.getMessage());
		}
	}

	public List<INodeFilter> getFilters() {
		return filters;
	}

}
