
package biz.app.examples.dialogs;

import biz.app.ui.UI;

/** Main class of the application. */
public class Main
{
	/** Constructor. */
	public Main()
	{
		UI.setRootView(new DialogsExample().uiScroll);
	}
}
