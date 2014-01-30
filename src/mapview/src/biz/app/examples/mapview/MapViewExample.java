
package biz.app.examples.mapview;

import biz.app.system.Log;
import biz.app.ui.widgets.ClickListener;
import biz.app.ui.widgets.View;
import biz.app.geo.GeoPoint;
import biz.app.geo.MapMarker;
import biz.app.ui.dialogs.Dialogs;
import biz.app.ui.dialogs.TextInputDialog;
import biz.app.ui.dialogs.TextInputDialogKind;
import biz.app.ui.dialogs.TextInputDialogListener;
import biz.app.ui.dialogs.DialogButton;
import biz.app.ui.dialogs.MessageBox;

/** Map view example. */
public class MapViewExample extends UIMapViewExample
{
	/** Constructor. */
	public MapViewExample() {
		super();
		uiAddMarkerButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object obj) {
				Log.info(getClass().getName(), "Left button clicked.");
				showInputCoordinatesDialog();
			}
		});
	}

	/** Shows input coordinates dialog. */
	private void showInputCoordinatesDialog()
	{
		final String[] okTitle = new String[] { "OK" };
		TextInputDialog dialog = Dialogs.createTextInputDialog(TextInputDialogKind.PLAIN_TEXT, okTitle);
		dialog.setTitle("Input coordinates:");
		dialog.setHint("55.45 37.37");
		dialog.setListener(new TextInputDialogListener() {
			@Override public void onDialogClosed(TextInputDialog dialog, DialogButton button, String text) {
				Log.info(getClass().getName(), "Coordinate: " + text);
				try
				{
					int spaceIndex = text.indexOf(' ');
					if (spaceIndex == -1)
						throw new Exception("Unable to parse coordinates.");
					double latitude = Double.parseDouble(text.substring(0, spaceIndex));
					double longitude = Double.parseDouble(text.substring(spaceIndex + 1));
					GeoPoint point = new GeoPoint(latitude, longitude);
					uiMapView.addMarker(new MapMarker(point, "Coordinates: " + text, ""));
					uiMapView.setCenter(point);
				}
				catch (Exception e)
				{
					MessageBox errorBox = Dialogs.createMessageBox("Error", e.getMessage(), okTitle);;
					errorBox.show();
				}
			}
		});

		dialog.show();
	}
}
