package com.Athin.SplitTiff;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Toolkit;

public class SplitTiffUI {

	private JFrame frmTifFileSplitter;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplitTiffUI window = new SplitTiffUI();

					window.frmTifFileSplitter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SplitTiffUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JFileChooser filechooser = new JFileChooser();
		JFileChooser filechooser1 = new JFileChooser();
		frmTifFileSplitter = new JFrame();
		frmTifFileSplitter.setResizable(false);
		frmTifFileSplitter.setIconImage(
				Toolkit.getDefaultToolkit().getImage(SplitTiffUI.class.getResource("/resources/Icon1.png")));
		frmTifFileSplitter.setType(Type.UTILITY);
		frmTifFileSplitter.setTitle("Tif Operator");
		frmTifFileSplitter.setBounds(100, 100, 360, 308);
		frmTifFileSplitter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTifFileSplitter.getContentPane().setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 344, 269);
		frmTifFileSplitter.getContentPane().add(tabbedPane);

		// Panel 1- tab for splitting into individual files
		JPanel panel = new JPanel();
		tabbedPane.addTab("Split", null, panel, null);

		JLabel lblNewLabel = new JLabel("Select the dpi for the image(96/300)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 9, 207, 14);

		panel.add(lblNewLabel);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBounds(108, 193, 89, 23);

		panel.add(btnSubmit);
		textField_1 = new JTextField();
		textField_1.setToolTipText("Path of File to Split");
		textField_1.setBounds(10, 80, 223, 22);

		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblEnterTheTif = new JLabel("Enter the tif file path to Split");
		lblEnterTheTif.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterTheTif.setBounds(10, 58, 159, 14);

		panel.add(lblEnterTheTif);
		JRadioButton rdbtnNewRadioButton = new JRadioButton("300");
		rdbtnNewRadioButton.setBounds(77, 30, 74, 23);

		panel.add(rdbtnNewRadioButton);
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("96");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setBounds(27, 30, 48, 23);

		panel.add(rdbtnNewRadioButton_1);
		ButtonGroup G = new ButtonGroup();
		G.add(rdbtnNewRadioButton);
		G.add(rdbtnNewRadioButton_1);

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					textField_1.setText(filechooser.getSelectedFile().getAbsolutePath());
				} else {
					textField_1.setText("");
				}
			}
		});
		btnNewButton.setBounds(243, 80, 93, 23);

		panel.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(10, 218, 277, 23);

		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setToolTipText("Output Directory");
		textField.setBounds(10, 120, 223, 22);

		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Enter Output Directory");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 105, 128, 14);

		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setToolTipText("AMT");
		lblNewLabel_3.setIcon(new ImageIcon(SplitTiffUI.class.getResource("/resources/Icon1.png")));
		lblNewLabel_3.setBounds(240, 143, 89, 87);

		panel.add(lblNewLabel_3);
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.setBounds(243, 119, 93, 23);

		panel.add(btnBrowse);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filechooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (filechooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					textField.setText(filechooser1.getSelectedFile().getAbsolutePath());

				} else {
					textField.setText("");
				}
			}
		});

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean validations = true;
				try {

					lblNewLabel_1.setText("");

					if (textField.getText().length() == 0 || textField.getText() == "") {
						lblNewLabel_1.setText("Please Enter the output file path");
						validations = false;
					}

					if (textField_1.getText().length() == 0 || textField_1.getText() == "") {
						lblNewLabel_1.setText("Please Enter the input file path");
						validations = false;
					}

					int dpi;

					if (rdbtnNewRadioButton.isSelected())
						dpi = 300;
					else
						dpi = 96;
					int result = 0;
					if (validations) {
						lblNewLabel_1.setText("Running...");
						lblNewLabel_1.paintImmediately(lblNewLabel_1.getVisibleRect());
						result = SplitTiff.doSplit(textField_1.getText().replace("\\", "\\\\"),
								textField.getText().replace("\\", "\\\\"), dpi);
						if (result != 0) {
							lblNewLabel_1.setText("Splitting Completed.  " + result + "  files created");
						} else {
							lblNewLabel_1.setText("Cannot Perform Split");
						}
					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});

		/*
		 * *****************************************************************************
		 * **********
		 */

		/* *********************************************************************** */
		// Panel 2- tab for splitting specific range of file into one
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Split-Range", null, panel2, null);
		panel2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Enter the dpi for resulting image");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 11, 212, 19);
		panel2.add(lblNewLabel_4);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("96");
		rdbtnNewRadioButton_2.setSelected(true);
		rdbtnNewRadioButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnNewRadioButton_2.setBounds(20, 37, 51, 23);
		panel2.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("300");
		rdbtnNewRadioButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnNewRadioButton_3.setBounds(73, 37, 89, 23);
		panel2.add(rdbtnNewRadioButton_3);

		ButtonGroup G1 = new ButtonGroup();
		G1.add(rdbtnNewRadioButton_2);
		G1.add(rdbtnNewRadioButton_3);

		JLabel lblEnterTheInput = new JLabel("Enter the input file and output folder");
		lblEnterTheInput.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterTheInput.setBounds(10, 67, 205, 14);
		panel2.add(lblEnterTheInput);

		textField_2 = new JTextField();
		textField_2.setToolTipText("Input file Path");
		textField_2.setBounds(10, 87, 212, 20);
		panel2.add(textField_2);
		textField_2.setColumns(10);

		JButton btnBrowse_1 = new JButton("Browse");
		btnBrowse_1.setBounds(232, 86, 89, 23);
		panel2.add(btnBrowse_1);

		btnBrowse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					textField_2.setText(filechooser.getSelectedFile().getAbsolutePath());
				} else {
					textField_2.setText("");
				}
			}
		});

		JLabel lblSelectTheRange = new JLabel("Select the Page Range to Split");
		lblSelectTheRange.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectTheRange.setBounds(10, 143, 194, 19);
		panel2.add(lblSelectTheRange);

		JLabel lblStart = new JLabel("Start");
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStart.setBounds(10, 169, 46, 14);
		panel2.add(lblStart);

		textField_3 = new JTextField();
		textField_3.setBounds(61, 166, 51, 20);
		panel2.add(textField_3);
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent kepPressed) {

				if (!Character.isDigit(kepPressed.getKeyChar()))
					kepPressed.consume();

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		JLabel lblEnd = new JLabel("End");
		lblEnd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnd.setBounds(128, 169, 46, 14);
		panel2.add(lblEnd);

		textField_4 = new JTextField();
		textField_4.setBounds(167, 166, 51, 20);
		panel2.add(textField_4);
		textField_4.setColumns(10);
		textField_4.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent kepPressed) {

				if (!Character.isDigit(kepPressed.getKeyChar()))
					kepPressed.consume();

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});

		JLabel lblResponse2 = new JLabel("");
		lblResponse2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResponse2.setForeground(Color.RED);
		lblResponse2.setBounds(10, 217, 311, 24);
		panel2.add(lblResponse2);

		textField_5 = new JTextField();
		textField_5.setToolTipText("Output Directory");
		textField_5.setBounds(10, 121, 212, 20);
		panel2.add(textField_5);
		textField_5.setColumns(10);

		JButton btnBrowse_2 = new JButton("Browse");
		btnBrowse_2.setBounds(232, 120, 89, 23);
		panel2.add(btnBrowse_2);
		panel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setToolTipText("AMT");
		lblNewLabel_5.setIcon(new ImageIcon(SplitTiffUI.class.getResource("/resources/Icon1.png")));
		lblNewLabel_5.setBounds(240, 143, 89, 87);
		panel2.add(lblNewLabel_5);

		btnBrowse_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filechooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (filechooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					textField_5.setText(filechooser1.getSelectedFile().getAbsolutePath());

				} else {
					textField_5.setText("");
				}
			}
		});

		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit_1.setBounds(108, 193, 89, 23);
		panel2.add(btnSubmit_1);

		btnSubmit_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String result;
				try {
					Boolean validations = true;

					int dpi;
					if (rdbtnNewRadioButton_2.isSelected())
						dpi = 96;
					else
						dpi = 300;

					if (textField_5.getText().length() == 0 || textField_5.getText() == "") {
						lblResponse2.setText("Please Enter the output file path");
						validations = false;
					}
					if (textField_2.getText().length() == 0 || textField_2.getText() == "") {
						lblResponse2.setText("Please Enter the input file path");
						validations = false;
					}

					if (validations) {
						lblResponse2.setText("Running...");
						lblResponse2.paintImmediately(lblResponse2.getVisibleRect());
						result = SplitTiff.doSplitRange(textField_2.getText().replace("\\", "\\\\"),
								textField_5.getText().replace("\\", "\\\\"), dpi,
								Integer.parseInt(textField_3.getText()), Integer.parseInt(textField_4.getText()));
						lblResponse2.setText(result);
					}
				} catch (Exception e) {

					result = "Error Occurred while Splitting!!!";
					lblResponse2.setText(result);
				}

			}
		});

		/* ***********************************************************************************/

		/* Panel3- Tab for combining tif files into one */

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Combine Tifs", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblAddTheFiles = new JLabel("Add the files to be combined");
		lblAddTheFiles.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddTheFiles.setBounds(10, 11, 220, 14);
		panel_1.add(lblAddTheFiles);

		textField_6 = new JTextField();
		textField_6.setToolTipText("Input file paths separated by ';'");
		textField_6.setBounds(10, 36, 220, 23);
		panel_1.add(textField_6);
		textField_6.setColumns(10);

		JButton btnAddFile = new JButton("Add File");
		btnAddFile.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddFile.setBounds(240, 35, 89, 23);
		panel_1.add(btnAddFile);

		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					if (textField_6.getText().length() == 0)
						textField_6.setText(filechooser.getSelectedFile().getAbsolutePath());
					else
						textField_6
								.setText(textField_6.getText() + ";" + filechooser.getSelectedFile().getAbsolutePath());
				} else {

				}

			}
		});

		JLabel lblSelectTheDestination = new JLabel("Select the destination directory");
		lblSelectTheDestination.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectTheDestination.setBounds(10, 76, 220, 14);
		panel_1.add(lblSelectTheDestination);

		textField_7 = new JTextField();
		textField_7.setToolTipText("Output Directory");
		textField_7.setBounds(10, 101, 220, 23);
		panel_1.add(textField_7);
		textField_7.setColumns(10);

		JButton btnBrowse_3 = new JButton("Browse");
		btnBrowse_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				filechooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (filechooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					textField_7.setText(filechooser1.getSelectedFile().getAbsolutePath());

				} else {
					textField_7.setText("");
				}
			}
		});
		btnBrowse_3.setBounds(240, 100, 89, 23);
		panel_1.add(btnBrowse_3);

		JLabel lblSelectDpiOf = new JLabel("Select dpi of the combined image");
		lblSelectDpiOf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectDpiOf.setBounds(10, 143, 220, 14);
		panel_1.add(lblSelectDpiOf);

		JRadioButton radioButton = new JRadioButton("96");
		radioButton.setSelected(true);
		radioButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioButton.setBounds(6, 162, 47, 23);
		panel_1.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("300");
		radioButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		radioButton_1.setBounds(55, 162, 75, 23);
		panel_1.add(radioButton_1);

		ButtonGroup G2 = new ButtonGroup();
		G2.add(radioButton_1);
		G2.add(radioButton);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setToolTipText("AMT");
		lblNewLabel_6.setIcon(new ImageIcon(SplitTiffUI.class.getResource("/resources/Icon1.png")));
		lblNewLabel_6.setBounds(240, 143, 89, 87);
		panel_1.add(lblNewLabel_6);

		JButton btnSubmit_2 = new JButton("Submit");
		btnSubmit_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit_2.setBounds(108, 193, 89, 23);
		panel_1.add(btnSubmit_2);
		JLabel lblResponse = new JLabel("");
		lblResponse.setForeground(Color.RED);
		lblResponse.setBounds(10, 218, 283, 23);
		panel_1.add(lblResponse);

		btnSubmit_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				boolean validations = true;
				String result = "";
				try {

					if (textField_7.getText().length() == 0 || textField_7.getText() == "") {
						lblResponse.setText("Please Enter the output file path");
						validations = false;
					}

					if (textField_6.getText().length() == 0 || textField_6.getText() == "") {
						lblResponse.setText("Please Enter the input file path");
						validations = false;
					}

					int dpi;

					if (radioButton.isSelected())
						dpi = 96;
					else
						dpi = 300;

					if (validations) {
						lblResponse.setText("Running....");
						lblResponse.paintImmediately(lblResponse.getVisibleRect());
						String[] inputPaths = textField_6.getText().split(";");
						result = SplitTiff.combineTiffs(inputPaths, textField_7.getText().replace("\\", "\\\\"), dpi);
						lblResponse.setText(result);

					}

				} catch (Exception e) {
					lblResponse.setText("Error Occurred While Combining Tifs!!!");

				}
			}

		});

//**********************************************************************************
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				lblNewLabel_1.setText("");
				lblResponse2.setText("");
				lblResponse.setText("");

			}
		});

	}
}
