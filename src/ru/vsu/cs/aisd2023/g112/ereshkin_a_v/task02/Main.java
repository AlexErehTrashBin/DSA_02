package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02;

import ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.gui.FrameMain;
import ru.vsu.cs.util.SwingUtils;

import java.util.Locale;

public class Main {
	public static void main(String[] args) {
		SwingUtils.setLookAndFeelByName("Windows");
		Locale.setDefault(Locale.ROOT);

		SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
		java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
	}
}