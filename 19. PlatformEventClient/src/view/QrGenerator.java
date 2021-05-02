package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;

public class QrGenerator {
	private static final char ZERO = '0';
	private JPanel panel;
	
	public QrGenerator() {
		panel = new JPanel();
	}

	public  void generate(String textQr) {
		String text = textQr;
		panel.removeAll();
		panel.setLayout(new GridLayout(10, 10));
		byte [] list = text.getBytes();
		for (byte b : list) {
			String binaryList = Integer.toBinaryString(b & 0xFF).replace(' ', ZERO);
			for (char c : binaryList.toCharArray()) {
				JButton btn = new JButton();
				if (c == ZERO) {
					btn.setBackground(Color.WHITE);
				}else{
					btn.setBackground(Color.BLACK);
				}
				btn.setEnabled(false);
				btn.setBorderPainted(false);
				panel.add(btn);
			}
		}
		panel.revalidate();
		panel.repaint();
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		panel.paintAll(image.createGraphics());
	}
}
