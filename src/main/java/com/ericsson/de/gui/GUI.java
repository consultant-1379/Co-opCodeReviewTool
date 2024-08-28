package com.ericsson.de.gui;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField Filter1;
	private JTextField Filter2;
	private JTextArea textArea;
	private JComboBox Filter1Drop, ViewDrop;
	private JLabel label;
	private String Filter1Input, Filter2Input, Filter1DropInput, ViewDropInput;
	private JLabel lblCodeReviewCheck;
	private JLabel lblNewLabel_1;
	private static String[] params = { "", "", "" };
	private JButton btnReset;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	public void reset() {
		Filter1Drop.setSelectedIndex(-1);
		ViewDrop.setSelectedIndex(0);
		Filter1.setEnabled(false);
		Filter1.setText("");
		Filter2.setEnabled(false);
		Filter2.setText("");
		ViewDrop.setEnabled(false);
		textArea.setText("");
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1199, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		String[] FilterOptions = { "Guard groups", "Project", "Authors email" };
		String[] TimeFilterOptions = { "Number of commits", "Time in days" };
		String[] ViewFormat = { "Default", "Verbose", "% folowing guidelines" };

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 268, 545);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Rockwell", Font.PLAIN, 12));
		label.setBounds(0, 243, 268, 22);
		panel.add(label);

		Filter1Drop = new JComboBox(FilterOptions);
		Filter1Drop.setSelectedIndex(-1);
		Filter1Drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter1.setEnabled(true);
				Filter1.setEnabled(true);
				Filter2.setEnabled(true);
				ViewDrop.setEnabled(true);

				if (Filter1Drop.getSelectedIndex() != -1) {
					if (Filter1Drop.getSelectedItem().equals("Project")) {
						label.setText("Please enter full project name e.g OSS/com.eri....");
					} else if (Filter1Drop.getSelectedItem().equals("Guard groups")) {
						label.setText("Please enter guard group ID number, not name");
					} else if (Filter1Drop.getSelectedItem().equals("Authors email")) {
						label.setText("Please enter the e-mail of employee");
					}
				}

			}
		});
		Filter1Drop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Filter1Drop.setBounds(0, 171, 221, 34);
		panel.add(Filter1Drop);

		Filter1 = new JTextField();
		Filter1.setBounds(0, 272, 268, 27);
		panel.add(Filter1);
		Filter1.setEnabled(false);
		Filter1.setColumns(10);

		Filter2 = new JTextField();
		Filter2.setColumns(10);
		Filter2.setBounds(0, 349, 104, 27);
		Filter2.setEnabled(false);
		panel.add(Filter2);

		// TO DO add action listner and enable search
		ViewDrop = new JComboBox(ViewFormat);
		ViewDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.setEnabled(true);
			}
		});
		ViewDrop.setBounds(0, 427, 221, 34);
		ViewDrop.setEnabled(false);
		panel.add(ViewDrop);

		JLabel lblChooseHowYou = new JLabel("How you would like to filter your query");
		lblChooseHowYou.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblChooseHowYou.setBounds(0, 126, 268, 47);
		panel.add(lblChooseHowYou);

		JLabel lblNewLabel = new JLabel("Filter by number of commits ");
		lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblNewLabel.setBounds(0, 323, 241, 15);
		panel.add(lblNewLabel);

		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Rockwell", Font.PLAIN, 14));
		btnSearch.setBounds(62, 472, 142, 34);
		btnSearch.setEnabled(false);
		panel.add(btnSearch);

		JLabel lblChooseYourView = new JLabel("Choose your view ");
		lblChooseYourView.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblChooseYourView.setHorizontalAlignment(SwingConstants.LEFT);
		lblChooseYourView.setBounds(0, 402, 204, 14);
		panel.add(lblChooseYourView);

		lblCodeReviewCheck = new JLabel("Code Review check");
		lblCodeReviewCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeReviewCheck.setFont(new Font("Rockwell", Font.BOLD, 17));
		lblCodeReviewCheck.setBounds(10, 11, 248, 47);
		panel.add(lblCodeReviewCheck);

		lblNewLabel_1 = new JLabel();
		// lblNewLabel_1.setIcon(new
		// ImageIcon(GUI.class.getResource("/com/ericsson/de/GUI/Ericsson-logo.png")));
		lblNewLabel_1.setBounds(62, 50, 120, 87);
		panel.add(lblNewLabel_1);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setFont(new Font("Rockwell", Font.PLAIN, 12));
		btnReset.setBounds(72, 517, 110, 28);
		panel.add(btnReset);
		lblNewLabel_1.setVisible(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(288, 11, 885, 545);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Filter1Input = Filter1.getText();
				Filter2Input = Filter2.getText();
				Filter1DropInput = Filter1Drop.getSelectedItem().toString();
				ViewDropInput = ViewDrop.getSelectedItem().toString();
				params[0] = Filter1DropInput;
				params[1] = Filter2Input;
				params[2] = Filter1DropInput;
				textArea.setText(Filter1DropInput + "\n" + Filter1Input + "\n" + Filter2Input + "\n" + ViewDropInput);
			}
		});
	}
}
