
package biz.app.examples.dialogs;

import biz.app.system.SystemAPI;
import biz.app.system.Timer;
import biz.app.system.TimerKind;
import biz.app.ui.actionsheets.ActionSheetButton;
import biz.app.ui.actionsheets.ActionSheets;
import biz.app.ui.actionsheets.DateSelectionActionSheet;
import biz.app.ui.actionsheets.DateSelectionActionSheetListener;
import biz.app.ui.actionsheets.MultipleSelectionActionSheet;
import biz.app.ui.actionsheets.MultipleSelectionActionSheetListener;
import biz.app.ui.actionsheets.SingleSelectionActionSheet;
import biz.app.ui.actionsheets.SingleSelectionActionSheetListener;
import biz.app.ui.dialogs.DialogButton;
import biz.app.ui.dialogs.Dialogs;
import biz.app.ui.dialogs.LoginDialog;
import biz.app.ui.dialogs.LoginDialogListener;
import biz.app.ui.dialogs.MessageBox;
import biz.app.ui.dialogs.ProgressDialog;
import biz.app.ui.dialogs.TextInputDialog;
import biz.app.ui.dialogs.TextInputDialogKind;
import biz.app.ui.dialogs.TextInputDialogListener;
import biz.app.ui.widgets.ClickListener;
import biz.app.ui.widgets.View;

/** Main view of the application. */
public class DialogsExample extends UIDialogsExample
{
	/** Constructor. */
	public DialogsExample()
	{
		super();

		// MessageBox
		uiMessageBoxButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object obj) {
				MessageBox messageBox = Dialogs.createMessageBox("Hello, world!", new String[]{ "OK" });
				messageBox.setTitle("MessageBox");
				messageBox.show();
			}
		});

		// TextInputDialog
		uiInputButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object obj) {
				TextInputDialog textInputDialog =
					Dialogs.createTextInputDialog(TextInputDialogKind.PLAIN_TEXT, new String[]{ "OK", "CANCEL" });
				textInputDialog.setTitle("TextInputDialog");
				textInputDialog.setHint("Enter text here.");
				textInputDialog.setListener(new TextInputDialogListener() {
					@Override public void onDialogClosed(TextInputDialog dialog, DialogButton button, String text) {
						StringBuilder outputData = new StringBuilder();
						appendButtonName(outputData, button);
						if (button == DialogButton.OK_BUTTON)
						{
							outputData.append("Your Data: ");
							outputData.append(text);
						}
						createResultMessage(outputData.toString());
					}
				});
				textInputDialog.show();
			}
		});

		// TextInputDialog (secure)
		uiInputSecureButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object obj) {
				TextInputDialog textInputDialogSecure =
					Dialogs.createTextInputDialog(TextInputDialogKind.SECURE_TEXT, new String[]{ "OK", "CANCEL" });
				textInputDialogSecure.setTitle("TextInputDialogSecure");
				textInputDialogSecure.setListener(new TextInputDialogListener() {
					@Override public void onDialogClosed(TextInputDialog dialog, DialogButton button, String text) {
						StringBuilder outputData = new StringBuilder();
						appendButtonName(outputData, button);
						if (button == DialogButton.OK_BUTTON)
						{
							outputData.append("Your Data: ");
							outputData.append(text);
						}
						createResultMessage(outputData.toString());
					}
				});
				textInputDialogSecure.show();
			}
		});

		// LoginDialog
		uiLoginButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object obj) {
				LoginDialog loginDialog = 
					Dialogs.createLoginDialog("Enter login", "Enter password", new String[]{ "OK", "CANCEL" });
				loginDialog.setTitle("LoginDialog");
				loginDialog.setListener(new LoginDialogListener() {
					@Override public void onDialogClosed
							(LoginDialog dialog, DialogButton button, String login, String password) {
						StringBuilder outputData = new StringBuilder();
						appendButtonName(outputData, button);
						if (button == DialogButton.OK_BUTTON)
						{
							outputData.append("Your Data: login: '");
							outputData.append(login);
							outputData.append(" ', password: ");
							outputData.append(password);
							outputData.append("'");
						}
						createResultMessage(outputData.toString());
					}
				});
				loginDialog.show();
			}
		});

		// SelectionActionSheet
		uiSelectButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object obj) {
				final String[] strings = createSimpleArrayOfStrings();
				SingleSelectionActionSheet selectionDialog =
					ActionSheets.createSingleSelectionActionSheet(strings, new String[]{ "OK", "CANCEL" });
				selectionDialog.setTitle("SelectionDialog");
				selectionDialog.setSelectedIndex(10);
				selectionDialog.setListener(new SingleSelectionActionSheetListener() {
					@Override public void onActionSheetClosed(SingleSelectionActionSheet dialog,
						ActionSheetButton button, int pressedIndex)
					{
						StringBuilder outputData = new StringBuilder();
						appendButtonName(outputData, button);
						if (button == ActionSheetButton.OK_BUTTON)
						{
							outputData.append("Your Data: '");
							outputData.append(pressedIndex != -1 ? strings[pressedIndex] : "");
							outputData.append("'");
						}
						createResultMessage(outputData.toString());
					}
				});
				selectionDialog.show();
			}
		});

		// MultipleSelectionDialog
		uiMulteselectionButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View arg0, Object arg1) {
				final String[] strings = createSimpleArrayOfStrings();
				MultipleSelectionActionSheet selectionActionSheet =
					ActionSheets.createMultipleSelectionActionSheet(strings, new String[]{ "OK", "CANCEL" });
				selectionActionSheet.setTitle("SelectionDialog");
				selectionActionSheet.setSelectedItems(1, 3, 10);
				selectionActionSheet.setListener(new MultipleSelectionActionSheetListener()
				{
					@Override public void onActionSheetClosed(MultipleSelectionActionSheet actionSheet,
						ActionSheetButton button, int[] data)
					{
						StringBuilder outputData = new StringBuilder();
						appendButtonName(outputData, button);
						if (button == ActionSheetButton.OK_BUTTON)
						{
							outputData.append("Your Data:");
							for (int i = 0; i < data.length; i++)
							{
								outputData.append("\n");
								outputData.append(strings[data[i]]);
							}
						}
						createResultMessage(outputData.toString());
					}
				});
				selectionActionSheet.show();
			}
		});

		// DateSelectionDialog
		uiDateButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object object) {
				final DateSelectionActionSheet dateSelectionActionSheet =
					ActionSheets.createDateSelectionActionSheet( new String[]{ "OK", "CANCEL" });
				dateSelectionActionSheet.setTitle("DateSelectionDialog");
				dateSelectionActionSheet.setListener(new DateSelectionActionSheetListener()
				{
					@Override public void onActionSheetClosed(DateSelectionActionSheet actionSheet,
						ActionSheetButton button, int day, int month, int year)
					{
						StringBuilder outputData = new StringBuilder();
						appendButtonName(outputData, button);
						if (button == ActionSheetButton.OK_BUTTON)
						{
							outputData.append("Entered date: '");
							outputData.append(String.valueOf(day));
							outputData.append(".");
							outputData.append(String.valueOf(month));
							outputData.append(".");
							outputData.append(String.valueOf(year));
							outputData.append("'");
						}
						createResultMessage(outputData.toString());
					}
				});
				dateSelectionActionSheet.show();
			}
		});

		// ProgressDialog
		uiProgressButton.clickListeners().addStrongListener(new ClickListener() {
			@Override public void onClicked(View view, Object object) {
				final ProgressDialog progressDialog = Dialogs.createProgressDialog();
				progressDialog.setMessage("ProgressDialog message!");
				progressDialog.show();
				Timer timer = SystemAPI.startTimer(TimerKind.SINGLE_SHOT, 5000, new Runnable() {
					@Override public void run() {
						progressDialog.close();
					}
				});
			}
		});
	}

	/**
	 * Returns name of the button.
	 * @param button Button ID.
	 * @return button name.
	 */
	private String getButtonName(ActionSheetButton button)
	{
		switch (button)
		{
		case OK_BUTTON: return "Ok";
		case EXTRA_BUTTON: return "Extra";
		case CANCEL_BUTTON: return "Cancel";
		}
		return "nothing";
	}

	/**
	 * Returns name of the button.
	 * @param button Button ID.
	 * @return button name.
	 */
	private String getButtonName(DialogButton button)
	{
		switch (button)
		{
		case OK_BUTTON: return "Ok";
		case EXTRA_BUTTON: return "Extra";
		case CANCEL_BUTTON: return "Cancel";
		}
		return "nothing";
	}

	/**
	 * Displays result message.
	 * @param result Message to display.
	 */
	private void createResultMessage(String result)
	{
		MessageBox message = Dialogs.createMessageBox(result, new String[]{ "OK" });
		message.setTitle("Result");
		message.show();
	}

	/**
	 * Adds name of the pressed button into the given string builder.
	 * @param builder Builder to append to.
	 * @param button Kind of the button.
	 */
	private StringBuilder appendButtonName(StringBuilder builder, ActionSheetButton button)
	{
		builder.append("Your have pressed '");
		builder.append(getButtonName(button));
		builder.append("' button \n");
		return builder;
	}

	/**
	 * Adds name of the pressed button into the given string builder.
	 * @param builder Builder to append to.
	 * @param button Kind of the button.
	 */
	private StringBuilder appendButtonName(StringBuilder builder, DialogButton button)
	{
		builder.append("Your have pressed '");
		builder.append(getButtonName(button));
		builder.append("' button \n");
		return builder;
	}

	/**
	 * Creates array of strings for use in lists.
	 * @return array of strings.
	 */
	private String[] createSimpleArrayOfStrings()
	{
		String[] simple = new String[20];
		for (int i = 0; i < simple.length; i++)
			simple[i] = "String number " + String.valueOf(i);
		return simple;
	}
}
