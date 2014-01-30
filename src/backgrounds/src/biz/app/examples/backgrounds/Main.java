
package biz.app.examples.backgrounds;

import biz.app.ui.UI;

/** Main class of the application. */
public class Main
{
	/** Constructor. */
	public Main()
	{
		UI.setRootView(new UIBackgroundsExample().uiTabBar);
	}
}
