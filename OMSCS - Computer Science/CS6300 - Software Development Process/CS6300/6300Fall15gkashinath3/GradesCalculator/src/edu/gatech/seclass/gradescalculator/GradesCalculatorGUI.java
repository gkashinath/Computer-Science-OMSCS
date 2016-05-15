package edu.gatech.seclass.gradescalculator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GradesCalculatorGUI {

	private Students students = null;
	private Grades grades = null;
	private Course course = null;
	private JFrame mainwindow = null;
	private JComboBox<String> studentsComboBox = null;
	private JTextField formulaField = null;
	private JEditorPane studentInfoArea = null;
	private JPanel formulaPanel = null;
	private JButton updateFormulaButton = null;

	public static void main(String[] args) {
		String gradesdb = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
		String studentsdb = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
		GradesCalculatorGUI window = null;
		if (args.length < 1) {
			System.out.println("Using default spreadsheets");
		} else {
			try {
				while(args.length > 0) {
					if (args[0].equals("-students")) { 
						studentsdb = args[1];
						System.out.println("Using student spreadsheet " + studentsdb);
						args = updateArgs(args);
					}
					else if (args[0].equals("-grades")) { 
						gradesdb = args[1];
						System.out.println("Using grades spreadsheet " + gradesdb);
						args = updateArgs(args);
					}
					else {
						usage();
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				usage();
			}
		}
		window = new GradesCalculatorGUI(studentsdb, gradesdb);
		window.mainwindow.setVisible(true);
	}

	private static void usage() {
		System.err.println("Usage: GradesCalcGUI [-students <studentdb>] [-grades <gradesdb>]");
		System.exit(-1);
	}

	private static String[] updateArgs(String[] args) {
		if (args.length >= 2) {
			args = Arrays.copyOfRange(args, 2, args.length);
		}
		return args;
	}

	public GradesCalculatorGUI(String studentsdbfile, String gradesdbfile) {
		students = new Students(studentsdbfile);
		grades = new Grades(gradesdbfile);
		course = new Course(students, grades);

		mainwindow = new JFrame();
		mainwindow.setTitle("Grades Calculator");
		mainwindow.setBounds(100, 100, 600, 400);
		mainwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainwindow.getContentPane().setLayout(new BorderLayout());
		//
		studentInfoArea = new JEditorPane();
		studentInfoArea.setEditable(false);
		studentInfoArea.setSize(400, 200);
		JScrollPane scrollPane = new JScrollPane(studentInfoArea);
		mainwindow.getContentPane().add(scrollPane, BorderLayout.CENTER);
		//
		studentsComboBox = new JComboBox<String>();
		studentsComboBox.setSize(389, 20);
		studentsComboBox.addItemListener(new ItemChangeListener());
		mainwindow.getContentPane().add(studentsComboBox, BorderLayout.NORTH);
		fillComboBox();
		//
		JPanel buffer1 = new JPanel();
		JPanel buffer2 = new JPanel();
		buffer1.setSize(10, 100);
		buffer2.setSize(10, 100);
		mainwindow.getContentPane().add(buffer1, BorderLayout.WEST);
		mainwindow.getContentPane().add(buffer2, BorderLayout.EAST);
		mainwindow.validate();
		//
		formulaPanel = new JPanel();
		formulaPanel.setLayout(new FlowLayout());
		JLabel formulaLabel = new JLabel("Formula");
		formulaLabel.setSize(46, 14);
		formulaPanel.add(formulaLabel);
		formulaField = new JTextField();
		formulaField.setSize(340, 20);
		formulaField.setColumns(30);
		formulaField.setText(course.getFormula());
		formulaPanel.add(formulaField);
		updateFormulaButton = new JButton("Update Formula");
		updateFormulaButton.setSize(130, 20);
		updateFormulaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateFormula();
			}
		});
		formulaPanel.add(updateFormulaButton);
		mainwindow.getContentPane().add(formulaPanel, BorderLayout.SOUTH);
	}

	protected void updateFormula() {
		course.setFormula(formulaField.getText());
		refreshStudentInfo();
	}

	private void refreshStudentInfo() {
		String studentInfo = getStudentInfo();
		studentInfoArea.setText(studentInfo);
	}

	private void fillComboBox() {
		HashSet<Student> students = course.getStudents();
		ArrayList<String> names = new ArrayList<String>();
		for (Student student : students) {
			names.add(student.getName());
		}
		Collections.sort(names);
		for (String name : names) {
			studentsComboBox.addItem(name);
		}
	}

	private String getStudentInfo() {
		String selected = (String) studentsComboBox.getSelectedItem();
		Student student = course.getStudentByName(selected);
		String output = "Name: " + student.getName() + "\n" + "Email: "
				+ course.getEmail(student) + "\n" + "GTID: " + student.getGtid()
				+ "\n\n" + "Attendance: " + course.getAttendance(student) + "\n"
				+ "Average assignments grade: "
				+ course.getAverageAssignmentsGrade(student) + "\n"
				+ "Average projects grade: "
				+ course.getAverageProjectsGrade(student) + "\n\n"
				+ "Overall Grade: " + course.getOverallGrade(student) + "\n";
		return output;
	}

	private class ItemChangeListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				refreshStudentInfo();
			}
		}
	}
}
