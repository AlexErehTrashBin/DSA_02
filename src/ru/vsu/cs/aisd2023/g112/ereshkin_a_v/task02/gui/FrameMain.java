package ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.gui;

import ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.LinkedList;
import ru.vsu.cs.aisd2023.g112.ereshkin_a_v.task02.LinkedListUtils;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Comparator;


public class FrameMain extends JFrame {
	private JPanel panelMain;
	private JTable tableInput;
	private JButton buttonLoadInputFromFile;
	private JButton buttonRandomInput;
	private JButton buttonSaveInputInfoFile;
	private JButton buttonSort;
	private JButton buttonSaveOutputIntoFile;
	private JTable tableOutput;

	private final JFileChooser fileChooserOpen;
	private final JFileChooser fileChooserSave;


	public FrameMain() {
		this.setTitle("FrameMain");
		this.setContentPane(panelMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		JTableUtils.initJTableForArray(tableInput, 60, true, true, false, true);
		JTableUtils.initJTableForArray(tableOutput, 60, true, true, false, true);
		//tableOutput.setEnabled(false);
		tableInput.setRowHeight(35);
		tableOutput.setRowHeight(35);

		fileChooserOpen = new JFileChooser();
		fileChooserSave = new JFileChooser();
		fileChooserOpen.setCurrentDirectory(new File("."));
		fileChooserSave.setCurrentDirectory(new File("."));
		FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
		fileChooserOpen.addChoosableFileFilter(filter);
		fileChooserSave.addChoosableFileFilter(filter);

		fileChooserSave.setAcceptAllFileFilterUsed(false);
		fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooserSave.setApproveButtonText("Save");

		JMenuBar menuBarMain = new JMenuBar();
		setJMenuBar(menuBarMain);

		JMenu menuLookAndFeel = new JMenu();
		menuLookAndFeel.setText("Вид");
		menuBarMain.add(menuLookAndFeel);
		SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

		JTableUtils.writeArrayToJTable(tableInput, new int[][]{
				{0, 1, 2, 3, 4}
		});

		this.pack();


		buttonLoadInputFromFile.addActionListener(actionEvent -> {
			try {
				if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
					int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
					JTableUtils.writeArrayToJTable(tableInput, arr);
				}
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonRandomInput.addActionListener(actionEvent -> {
			try {
				int[][] matrix = ArrayUtils.createRandomIntMatrix(
						tableInput.getRowCount(), tableInput.getColumnCount(), 100);
				JTableUtils.writeArrayToJTable(tableInput, matrix);
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonSaveInputInfoFile.addActionListener(actionEvent -> {
			try {
				if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
					int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
					String file = fileChooserSave.getSelectedFile().getPath();
					if (!file.toLowerCase().endsWith(".txt")) {
						file += ".txt";
					}
					ArrayUtils.writeArrayToFile(file, matrix);
				}
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonSaveOutputIntoFile.addActionListener(actionEvent -> {
			try {
				if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
					int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput);
					String file = fileChooserSave.getSelectedFile().getPath();
					if (!file.toLowerCase().endsWith(".txt")) {
						file += ".txt";
					}
					ArrayUtils.writeArrayToFile(file, matrix);
				}
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonSort.addActionListener(actionEvent -> {
			try {
				int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableInput);
				assert matrix != null;
				LinkedList<Integer> list = new LinkedList<>();
				for (int i = 0; i < matrix[0].length; i++) {
					list.addLast(matrix[0][i]);
				}
				list.bubbleSort(Comparator.naturalOrder());
				Integer[] result = LinkedListUtils.toArray(list);
				JTableUtils.writeArrayToJTable(
						tableOutput, ArrayUtils.toPrimitive(result));
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
	}
}
