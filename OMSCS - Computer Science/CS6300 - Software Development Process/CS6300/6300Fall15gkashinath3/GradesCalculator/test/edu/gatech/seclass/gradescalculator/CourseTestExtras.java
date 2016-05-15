package edu.gatech.seclass.gradescalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CourseTestExtras {

    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String STUDENTS_DB = "DB/GradesDatabase6300-students.xlsx";
    static final String GRADES_DB = "DB/GradesDatabase6300-grades.xlsx";
    
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
    
	@Before
	public void setUp() throws Exception {
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
    public void testGetStudentsByName1() {
        expectedEx.expect(NullPointerException.class);
        
        Student student = course.getStudentByName("Gandharv Kashinath");
        student.getAttendance();      
    }
    
    @Test
    public void testGetStudentsByName2() {
        expectedEx.expect(NullPointerException.class);
        
        Student student = course.getStudentByName("Gandharv Kashinath");
        student.getTeam();      
    }

    @Test
    public void testGetStudentsByName3() {
        expectedEx.expect(NullPointerException.class);
        
        Student student = course.getStudentByName("Gandharv Kashinath");
        student.getGtid();      
    }
    
/*    @Test
    public void testGetStudentsByID1() {
    	expectedEx.expect(NullPointerException.class);
    	
        Student student = course.getStudentByID("123456789");
        student.getName();
    }*/
    
    @Test
    public void testGetTeam1() {
        Student student = course.getStudentByID("901234504");
        assertEquals("Team 1", student.getTeam());
    }
    
    @Test
    public void testGetEmail() {
        Student student = course.getStudentByName("Genista Parrish");
        assertEquals("gp@gatech.edu", student.getEmail());
    }
}
