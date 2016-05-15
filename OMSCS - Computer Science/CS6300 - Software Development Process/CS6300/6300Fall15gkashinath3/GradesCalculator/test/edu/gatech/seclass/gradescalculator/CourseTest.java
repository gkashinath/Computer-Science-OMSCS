package edu.gatech.seclass.gradescalculator;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {

    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String GRADES_DB = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
    static final String GRADES_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-grades-goldenversion.xlsx";
    static final String STUDENTS_DB = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
    static final String STUDENTS_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-students-goldenversion.xlsx";
    
    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path gradesdbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path gradesdbfile = fs.getPath(GRADES_DB);
        Files.copy(gradesdbfilegolden, gradesdbfile, REPLACE_EXISTING);
        Path studentsdbfilegolden = fs.getPath(STUDENTS_DB_GOLDEN);
        Path studentsdbfile = fs.getPath(STUDENTS_DB);
        Files.copy(studentsdbfilegolden, studentsdbfile, REPLACE_EXISTING);    	
    	students = new Students(STUDENTS_DB);
        grades = new Grades(GRADES_DB);
        course = new Course(students, grades);
    }

    @After
    public void tearDown() throws Exception {
        students = null;
        grades = null;
        course = null;
    }

    @Test
    public void testGetNumStudents1() {
        int numStudents = course.getNumStudents();
        assertEquals(15, numStudents);
    }

    @Test
    public void testGetNumAssignments1() {
        int numAssignments = course.getNumAssignments();
        assertEquals(4, numAssignments);
    }

    @Test
    public void testGetNumProjects1() {
        int numProjects;
        numProjects = course.getNumProjects();
        assertEquals(5, numProjects);
    }

    @Test
    public void testGetStudents1() {
        HashSet<Student> studentsRoster = null;
        studentsRoster = course.getStudents();
        assertEquals(15, studentsRoster.size());
    }

    @Test
    public void testGetStudents2() {
        HashSet<Student> studentsRoster = null;
        studentsRoster = course.getStudents();
        Student student = null;
        for (Student s : studentsRoster) {
            if (s.getName().compareTo("Tendai Charpentier") == 0) {
                student = s;
                break;
            }
        }
        assertNotNull(student);
    }

    @Test
    public void testGetStudentsByName1() {
        Student student = null;
        student = course.getStudentByName("Rastus Kight");
        assertEquals("901234512", student.getGtid());
    }

    @Test
    public void testGetStudentsByName2() {
        Student student = null;
        student = course.getStudentByName("Genista Parrish");
        assertEquals(90, course.getAttendance(student));
    }

    @Test
    public void testGetStudentsByID1() {
        Student student = course.getStudentByID("901234504");
        assertEquals("Shevon Wise", student.getName());
    }

    @Test
    public void testGetTeam1() {
        Student student = course.getStudentByName("Genista Parrish");
        assertEquals("Team 3", course.getTeam(student));
    }
    
    // New tests
    @Test
    public void testAddAssignment() {
        course.addAssignment("Assignment: black-box testing");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(5, course.getNumAssignments());
        course.addAssignment("Assignment: white-box testing");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(6, course.getNumAssignments());
    }

    @Test
    public void testAddGradesForAssignment() {
        String assignmentName = "Assignment: category partition";
        Student student1 = new Student("Josepha Jube", "901234502");
        Student student2 = new Student("Christine Schaeffer", "901234508");
        Student student3 = new Student("Ernesta Anderson", "901234510");
        course.addAssignment(assignmentName);
        course.updateGrades(new Grades(GRADES_DB));
        HashMap<Student, Integer> grades = new HashMap<Student, Integer>();
        grades.put(student1, 87);
        grades.put(student2, 94);
        grades.put(student3, 100);
        course.addGradesForAssignment(assignmentName, grades);
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(87, course.getAverageAssignmentsGrade(student1));
        assertEquals(90, course.getAverageAssignmentsGrade(student2));
        assertEquals(92, course.getAverageAssignmentsGrade(student3));
    }

    @Test
    public void testGetAverageAssignmentsGrade() {
        // Rounded to the closest integer
        Student student = new Student("Ernesta Anderson", "901234510");
        assertEquals(90, course.getAverageAssignmentsGrade(student));
    }

    @Test
    public void testGetAverageProjectsGrade() {
        // Rounded to the closest integer
        Student student = new Student("Rastus Kight", "901234512");
        assertEquals(72, course.getAverageProjectsGrade(student));
    }

    @Test
    public void testAddIndividualContributions() {
        String projectName1 = "Project 2";
        Student student1 = new Student("Josepha Jube", "901234502");
        Student student2 = new Student("Grier Nehling", "901234503");
        HashMap<Student, Integer> contributions1 = new HashMap<Student, Integer>();
        contributions1.put(student1, 96);
        contributions1.put(student2, 87);
        course.addIndividualContributions(projectName1, contributions1);
        course.updateGrades(new Grades(GRADES_DB));
        String projectName2 = "Project 3";
        HashMap<Student, Integer> contributions2 = new HashMap<Student, Integer>();
        contributions2.put(student1, 98);
        contributions2.put(student2, 100);
        course.addIndividualContributions(projectName2, contributions2);
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(85, course.getAverageProjectsGrade(student1));
        assertEquals(82, course.getAverageProjectsGrade(student2));
    }
    
    @Test
    public void testFormulaUpdate1() {
    	grades.setFormula("AA * .5 + AP * .5");
    	Student student = new Student("Kym Hiles", "901234507");
        assertEquals(88, course.getOverallGrade(student));
    }

    @Test
    public void testFormulaUpdate2() {
    	grades.setFormula("99.5");
        Student student = new Student("Genista Parrish", "901234509");
        assertEquals(100, course.getOverallGrade(student));
    }

    @Test(expected = GradeFormulaException.class)
    public void testFormulaUpdate3() {
    	grades.setFormula("AT * 02 + AAA * 0.5 + AP * 0.3");
        Student student = new Student("Genista Parrish", "901234509");
        assertEquals(100, course.getOverallGrade(student));
    }
}
