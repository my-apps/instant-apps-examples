
package biz.app.examples.labellinebreakmode;

import biz.app.primitives.LineBreakMode;
import biz.app.ui.widgets.TabPage;
import biz.app.ui.widgets.Widgets;
import biz.app.ui.UI;

/** Main class of the application. */
public class Main extends UITabBar
{
	/** Constructor */
	public Main()
	{
		uiTabBar.add(createTabPage(LineBreakMode.SINGLE_LINE));
		uiTabBar.add(createTabPage(LineBreakMode.SINGLE_LINE_WITH_ELLIPSIS));
		uiTabBar.add(createTabPage(LineBreakMode.WORD_WRAP));
		uiTabBar.add(createTabPage(LineBreakMode.WORD_WRAP_WITH_ELLIPSIS));
		UI.setRootView(uiTabBar);
	}

	/**
	 * Creates example page for the given line break mode.
	 * @param mode Line break mode.
	 * @return new tabbar page.
	 */
	private TabPage createTabPage(LineBreakMode mode)
	{
		UIPage page = new UIPage();
		page.uiTitleBar.setText(fullDescriptionOf(mode));
		page.uiLabel.setLineBreakMode(mode);
		page.uiLabel.setText(SampleText.SAMPLE_TEXT);

		TabPage tabPage = Widgets.createTabPage();
		tabPage.setText(shortDescriptionOf(mode));
		tabPage.add(page.uiMain);

		return tabPage;
	}

	/**
	 * Returns short description of the given line break mode.
	 * @param mode Line break mode.
	 * @return short description of the line break mode.
	 */
	private String shortDescriptionOf(LineBreakMode mode)
	{
		switch (mode)
		{
		case SINGLE_LINE: return SampleText.SINGLE_LINE_SHORT_TEXT;
		case SINGLE_LINE_WITH_ELLIPSIS: return SampleText.SINGLE_LINE_ELLIPSIS_SHORT_TEXT;
		case WORD_WRAP: return SampleText.WORD_WRAP_SHORT_TEXT;
		case WORD_WRAP_WITH_ELLIPSIS: return SampleText.WORD_WRAP_ELLIPSIS_SHORT_TEXT;
		}

		return null;
	}

	/**
	 * Returns full description of the given line break mode.
	 * @param mode Line break mode.
	 * @return full description of the line break mode.
	 */
	private String fullDescriptionOf(LineBreakMode mode)
	{
		switch (mode)
		{
		case SINGLE_LINE: return SampleText.SINGLE_LINE_TEXT;
		case SINGLE_LINE_WITH_ELLIPSIS: return SampleText.SINGLE_LINE_ELLIPSIS_TEXT;
		case WORD_WRAP: return SampleText.WORD_WRAP_TEXT;
		case WORD_WRAP_WITH_ELLIPSIS: return SampleText.WORD_WRAP_ELLIPSIS_TEXT;
		}

		return null;
	}
}
