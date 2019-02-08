package com.Athin.SplitTiff;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class SplitTiffUI {

	private JFrame frmTifFileSplitter;
	private JTextField textField_1;
	private JTextField textField;

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
		frmTifFileSplitter = new JFrame();
		frmTifFileSplitter.setType(Type.UTILITY);
		frmTifFileSplitter.setTitle("Tif File Splitter");
		frmTifFileSplitter.setBounds(100, 100, 360, 308);
		frmTifFileSplitter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTifFileSplitter.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Select the dpi for the image(96/300)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(28, 117, 266, 29);
		frmTifFileSplitter.getContentPane().add(lblNewLabel);

		JFileChooser filechooser = new JFileChooser();
		JFileChooser filechooser1 = new JFileChooser();

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSubmit.setBounds(70, 191, 89, 23);
		frmTifFileSplitter.getContentPane().add(btnSubmit);

		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setBounds(28, 31, 185, 20);
		frmTifFileSplitter.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblEnterTheTif = new JLabel("Enter the tif file path to Split");
		lblEnterTheTif.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterTheTif.setBounds(28, 11, 266, 21);
		frmTifFileSplitter.getContentPane().add(lblEnterTheTif);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("300");
		rdbtnNewRadioButton.setBounds(28, 143, 65, 23);
		frmTifFileSplitter.getContentPane().add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("96");
		rdbtnNewRadioButton_1.setSelected(true);
		rdbtnNewRadioButton_1.setBounds(120, 143, 65, 23);
		frmTifFileSplitter.getContentPane().add(rdbtnNewRadioButton_1);
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
		btnNewButton.setBounds(223, 30, 89, 23);
		frmTifFileSplitter.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(28, 225, 284, 23);
		frmTifFileSplitter.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(25, 87, 188, 20);
		frmTifFileSplitter.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBrowse.setBounds(223, 86, 89, 23);
		frmTifFileSplitter.getContentPane().add(btnBrowse);
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

		JLabel lblNewLabel_2 = new JLabel("Enter Output Directory");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(28, 62, 185, 23);
		frmTifFileSplitter.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(SplitTiffUI.class.getResource("/resources/Icon1.png")));
		lblNewLabel_3.setBounds(245, 157, 89, 57);
		frmTifFileSplitter.getContentPane().add(lblNewLabel_3);

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
						result = SplitTiff.doSplit(textField_1.getText().replace("\\", "\\\\"), textField.getText().replace("\\", "\\\\"), dpi);
						if (result != 0) {
							lblNewLabel_1.setText("Splitting Completed.  " + result + "  files created");
						} else {
							lblNewLabel_1.setText("Cannot Perform Split");
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
