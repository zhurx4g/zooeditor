package com.isammoc.zooeditor.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class InputDialog extends Dialog {
	private String value;
	private Display display = null;
	private Shell shell = null;
	private String text;
	public InputDialog(Shell parent) {
		super(parent);
		display = parent==null?Display.getCurrent():parent.getDisplay();
		this.shell = parent==null?new Shell(parent):parent;
	}

    public String open(String defaultValue)
    {
    	GridLayout layout = new GridLayout(10, false);
    	shell.setLayout(layout);

    	/****************************************************************/
    	Image image = new Image(display, InputDialog.class.getResourceAsStream("/input.jpg"));
    	Label labelIcon = new Label (shell, SWT.NONE);
    	labelIcon.setImage(image);
    	GridData data = new GridData(SWT.RIGHT, SWT.TOP, true, true, 2, 2);
    	labelIcon.setLayoutData (data);

    	/****************************************************************/
		Label label = new Label (shell, SWT.NONE);
		label.setText(text);
		data = new GridData(SWT.LEFT, SWT.CENTER, true, true, 8, 1);
		label.setLayoutData (data);

		final Text text = new Text (shell, SWT.BORDER);
		data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 8, 1);
		text.setLayoutData (data);
		text.setText(defaultValue);
		text.setSelection(0,defaultValue.length());

		/****************************************************************/
		Label space = new Label (shell, SWT.NONE);
		data = new GridData(SWT.LEFT, SWT.TOP, true, true, 4, 1);
		space.setLayoutData (data);
		
		Button ok = new Button (shell, SWT.PUSH);
		ok.setText ("OK");
		data = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		ok.setLayoutData (data);
		ok.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
				System.out.println ("User typed: " + text.getText ());
				InputDialog.this.value= text.getText ();
				shell.close ();
			}
		});
		Button cancel = new Button (shell, SWT.PUSH);
		cancel.setText ("Cancel");
		data = new GridData(SWT.LEFT, SWT.TOP, true, true, 1, 1);
		cancel.setLayoutData (data);
		cancel.addSelectionListener (new SelectionAdapter () {
			public void widgetSelected (SelectionEvent e) {
				System.out.println("User cancelled dialog");
				shell.close ();
			}
		});
    	shell.pack ();
    	shell.open ();
    	while (!shell.isDisposed ()) {
    		if (!display.readAndDispatch ()) display.sleep ();
    	}
    	display.dispose ();
    	return InputDialog.this.value;
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public void setTitle(String title) {
		this.shell.setText(title);// = text;
	}
}
