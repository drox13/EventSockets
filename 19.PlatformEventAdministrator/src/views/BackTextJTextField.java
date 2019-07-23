package views;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class BackTextJTextField extends JTextField implements FocusListener {

	private static final Color BACK_TEXT_BACKGROUND_COLOR = Color.WHITE;
	private static final Color BACK_BORDER_COLOR = Color.decode("#dfe0e6");
	private static final Color BACK_TEXT_FOREGROUND_COLOR = Color.decode("#88898c");
	private static final Color WRITING_TEXT_BACKGROUND_COLOR = Color.WHITE;
	private static final Color WRITING_BORDER_COLOR = Color.decode("#00c2ff");
	private static final Color WRITING_TEXT_FOREGROUND_COLOR = Color.BLACK;

	private static final long serialVersionUID = 1L;
	private String backText;

	public BackTextJTextField() {
		addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (getText().equals(getBackText())) {
			setBackground(WRITING_TEXT_BACKGROUND_COLOR);
			setBorder(BorderFactory.createLineBorder(WRITING_BORDER_COLOR));
			setText("");
			setForeground(WRITING_TEXT_FOREGROUND_COLOR);
		} else {
			setText(getText());
			setForeground(WRITING_TEXT_FOREGROUND_COLOR);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (getText().length() <= 0) {
			setBackText(getBackText());
		} else {
			setText(getText());
			setForeground(WRITING_TEXT_FOREGROUND_COLOR);
		}
	}

	public void setBackText(String backText) {
		setBackground(BACK_TEXT_BACKGROUND_COLOR);
		setBorder(BorderFactory.createLineBorder(BACK_BORDER_COLOR));
		setText(backText);
		setForeground(BACK_TEXT_FOREGROUND_COLOR);
		this.backText = backText;
	}

	public String getBackText() {
		return backText;
	}
}