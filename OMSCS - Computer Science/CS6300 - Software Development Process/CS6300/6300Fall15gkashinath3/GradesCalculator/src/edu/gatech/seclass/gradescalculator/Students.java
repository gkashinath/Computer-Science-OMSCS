package edu.gatech.seclass.gradescalculator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Students {

    private HashSet<Student> studentsRoster;
    XSSFWorkbook workbook;

    public HashSet<Student> getStudentsRoster() {
        return studentsRoster;
    }

    public void setStudentsRoster(HashSet<Student> studentsRoster) {
        this.studentsRoster = studentsRoster;
    }

    public Students(String location){
        InputStream file;

        try {
            file = new FileInputStream(location);
            try {
                this.workbook = new XSSFWorkbook(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.studentsRoster = createStudents(this.workbook);
    }
    
    public static HashSet<Student> createStudents(XSSFWorkbook workbook){
    	HashSet<Student> studentsRoster = new HashSet<Student>();
    	XSSFSheet studentInfo = workbook.getSheet("StudentsInfo");
        Iterator<Row> rowIterator = studentInfo.iterator();
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Student student = new Student();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 0:
                        student.setName(cell.getStringCellValue());
                        break;
                    case 1:
                        student.setGtid(String.valueOf((int) cell.getNumericCellValue()));
                        student.setTeam(getStudentTeam(workbook, student));
                        break;
                    case 2:
                    	student.setEmail(cell.getStringCellValue());
                    	break;
                }
                studentsRoster.add(student);
            }
        }
        return studentsRoster;
    }
    
    public static ArrayList<String> getAssignments(XSSFWorkbook workbook){
    	ArrayList<String> assignments = new ArrayList<>();
        XSSFSheet individualGrades = workbook.getSheet("IndividualGrades");
        Row row = individualGrades.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        
        cellIterator.next();
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            assignments.add(cell.getStringCellValue());
        }
        return assignments;
    }
    
    public static ArrayList<String> getProjects(XSSFWorkbook workbook){
    	ArrayList<String> projects = new ArrayList<>();
    	XSSFSheet individualContribs = workbook.getSheet("IndividualContribs");
        Row row = individualContribs.getRow(0);
        Iterator<Cell> cellIterator = row.cellIterator();
        
        cellIterator.next();
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            projects.add(cell.getStringCellValue());
        }
        return projects;
        
    }
    
    public static String getStudentTeam(XSSFWorkbook workbook,  Student student){
        XSSFSheet teams = workbook.getSheet("Teams");
        Iterator<Row> rowIterator = teams.iterator();
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (String.valueOf((int)cell.getNumericCellValue()).equals(student.getGtid())){
                    return row.getCell(0).getStringCellValue();
                }
            }
        }
        return null;
    }
    
    public static void setStudentAttendance(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet attendence = workbook.getSheet("Attendance");
        Iterator<Row> rowIterator = attendence.iterator();
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String gtid = String.valueOf((int) row.getCell(0).getNumericCellValue());
            for (Student student : students){
                if (gtid.equals(student.getGtid())){
                    student.setAttendance((int)row.getCell(1).getNumericCellValue());
                }
            }

        }
    }
    
    public static void setAssignmentGrades(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("IndividualGrades");
        Iterator<Row> rowIterator = sheet.iterator();
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String gtid = String.valueOf((int) row.getCell(0).getNumericCellValue());
            Iterator<Cell> cellIterator = row.cellIterator();
            cellIterator.next();
            for (Student student : students){
                HashMap<String,Integer> assignmentGrades = new HashMap<>();
                if (gtid.equals(student.getGtid())){
                    ArrayList<String> assignments = course.getAssignments();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        assignmentGrades.put(assignments.get(cell.getColumnIndex()-1),(int) cell.getNumericCellValue());
                    }
                    student.setAssignmentGrades(assignmentGrades);
                }
            }

        }
    }
    
    public static void setProjectGrades(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("IndividualContribs");
        Iterator<Row> rowIterator = sheet.iterator();
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String gtid = String.valueOf((int) row.getCell(0).getNumericCellValue());
            Iterator<Cell> cellIterator = row.cellIterator();
            cellIterator.next();
            for (Student student : students){
                HashMap<String,Integer> projectGrades = new HashMap<>();
                if (gtid.equals(student.getGtid())){
                    ArrayList<String> projects = course.getProjects();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        projectGrades.put(projects.get(cell.getColumnIndex()-1),(int) cell.getNumericCellValue());
                    }
                    student.setProjectGrades(projectGrades);
                }
            }

        }
    }
    
    public static void setTeamGrades(XSSFWorkbook workbook, Course course){
        HashSet<Student> students = course.getStudents();
        XSSFSheet sheet = workbook.getSheet("TeamGrades");
        Iterator<Row> rowIterator = sheet.iterator();
        
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String team =  row.getCell(0).getStringCellValue();
            for (Student student : students){
                if (team.equals(student.getTeam())){
                    Iterator<Cell> cellIterator = row.cellIterator();
                    cellIterator.next();
                    HashMap<String,Integer> teamGrades = new HashMap<>();
                    ArrayList<String> projects = course.getProjects();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        teamGrades.put(projects.get(cell.getColumnIndex()-1), (int) cell.getNumericCellValue());
                    }
                    student.setTeamGrades(teamGrades);
                }
            }

        }
    }
    
}