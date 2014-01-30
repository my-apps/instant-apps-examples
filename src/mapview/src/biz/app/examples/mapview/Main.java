
package biz.app.examples.mapview;

import biz.app.ui.UI;

/** Main class of the application. */
public class Main
{
	/** Constructor. */
	public Main()
	{
		UI.setRootView(new MapViewExample().uiMain);
	}
}
