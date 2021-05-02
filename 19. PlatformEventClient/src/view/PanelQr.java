package view;

import java.awt.Dimension;

import javax.swing.JPanel;

public class PanelQr extends JPanel{
	private QrGenerator qrGenerator;

	private static final long serialVersionUID = 1L;
	
	public PanelQr() {
		setPreferredSize(new Dimension(100, 100));
		qrGenerator = new QrGenerator();
	}
	
	public void generate(String textQr) {
		qrGenerator.generate(textQr);
	}
}
