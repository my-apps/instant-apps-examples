
package biz.app.examples.fonts;

import java.util.ArrayList;
import biz.app.primitives.Orientation;
import biz.app.system.Resources;
import biz.app.ui.FontFamily;
import biz.app.ui.FontStyle;
import biz.app.ui.UI;
import biz.app.ui.layouts.LayoutParams;
import biz.app.ui.layouts.Layouts;
import biz.app.ui.layouts.LinearLayout;
import biz.app.ui.layouts.ScrollLayout;
import biz.app.ui.widgets.ClickListener;
import biz.app.ui.widgets.Label;
import biz.app.ui.widgets.TabBarListener;
import biz.app.ui.widgets.TabPage;
import biz.app.ui.widgets.View;
import biz.app.ui.widgets.Widgets;

/** Simple class storing label and it's font data. */
class LabelData
{
	public final Label label;
	public final int size;
	public final FontFamily family;

	/**
	 * Constructor.
	 * @param label Label pointer to store.
	 * @param size Label font size.
	 * @param family Label font family.
	 */
	public LabelData(Label label, int size, FontFamily family)
	{
		this.label = label;
		this.family = family;
		this.size = size;
	}
}

public class Main extends UIMain
{
	/** Array with shown sizes. */
	private int[] m_Sizes = {1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 30, 36, 42, 72};
	/** Array, containing data about present labels. Used for update their style & text. */
	private ArrayList<LabelData> m_Labels;
	/** Current text style. */
	private FontStyle m_FontStyle;

	/** Constructor */
	public Main()
	{
		m_Labels = new ArrayList<LabelData>(FontFamily.values().length * m_Sizes.length);
		m_FontStyle = FontStyle.values()[0];

		// Create list with text labels.
		for (FontFamily fontFamily : FontFamily.values())
			uiTabBar.add(createTabPage(fontFamily));

		// Listener for text update button
		uiOkButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object param) {
				String text = uiInputLine.text();
				for (LabelData labelData : m_Labels)
					labelData.label.setText(text);
			}
		});

		for (FontStyle style : FontStyle.values())
		{
			TabPage tabbarPage = Widgets.createTabPage();
			tabbarPage.setText(style.name());
			uiStyleContainer.add(tabbarPage);
		}

		uiStyleContainer.listeners().addStrongListener(new TabBarListener() {
			@Override public void onPageActivated(int page) {
				m_FontStyle = FontStyle.values()[page];
				for (LabelData labelData : m_Labels)
					labelData.label.setFont(Resources.getFont(labelData.family, labelData.size, m_FontStyle));

				super.onPageActivated(page);
			}
		});

		UI.setRootView(uiMainContainer);
	}

	/**
	 * Creates example page for given font.
	 * @param fontFamily Current font family.
	 * @return new tabbar page.
	 */
	private TabPage createTabPage(FontFamily fontFamily)
	{
		TabPage tabbarPage = Widgets.createTabPage();
		tabbarPage.setText(fontFamily.name());

		ScrollLayout scrollLayout = Layouts.createScrollLayout();
		scrollLayout.layoutParams().setSize(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		tabbarPage.add(scrollLayout);

		LinearLayout textLines = Layouts.createLinearLayout();
		textLines.setOrientation(Orientation.VERTICAL);
		textLines.layoutParams().setSize(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		scrollLayout.add(textLines);

		for (int size : m_Sizes)
			textLines.add(textLine(fontFamily, size));

		return tabbarPage;
	}

	/**
	 * Creates container with two labels. First label text is their font size, second is example text.
	 * @param family Given font family for label texts.
	 * @param size Given size for label texts.
	 * @return new view, with labels.
	 */
	private View textLine(FontFamily family, int size)
	{
		LinearLayout line = biz.app.ui.layouts.Layouts.createLinearLayout();
		line.layoutParams().setSize(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		Label sizeLabel = Widgets.createLabel();
		sizeLabel.setText(String.valueOf(size));
		sizeLabel.setFont(Resources.getFont(family, size, FontStyle.NORMAL));
		sizeLabel.layoutParams().setSize(100, LayoutParams.WRAP_CONTENT);
		sizeLabel.setTextColor(biz.app.primitives.Color.WHITE);
		line.add(sizeLabel);

		Label textLabel = Widgets.createLabel();
		m_Labels.add(new LabelData(textLabel, size, family));
		textLabel.setText(uiInputLine.text());
		textLabel.setFont(biz.app.system.Resources.getFont(family, size, m_FontStyle));
		textLabel.layoutParams().setSize(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		textLabel.setTextColor(biz.app.primitives.Color.WHITE);
		line.add(textLabel);

		return line;
	}
}
