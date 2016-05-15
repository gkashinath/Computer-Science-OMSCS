package edu.gatech.seclass.gradescalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Course {

    private static HashSet<Student> students;
    private ArrayList<String> assignments;
    private ArrayList<String> projects;
    private String formula;
    private Grades grades;

    public int getNumStudents() {
        return students.size();
    }

    public int getNumAssignments() {
        return assignments.size();
    }

    public int getNumProjects(){
        return projects.size();
    }

    public static HashSet<Student> getStudents() {
        return students;
    }

    public static void setStudents(HashSet<Student> studentsRoster) {
        Course.students = studentsRoster;
    }

    public ArrayList<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<String> assignments) {
        this.assignments = assignments;
    }

    public ArrayList<String> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<String> projects) {
        this.projects = projects;
    }
    
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    
    public String getEmail(Student student) {
        return student.getEmail();
    }
    
    public Course(Students students, Grades grades){
        Course.students = students.getStudentsRoster();
        this.grades = grades;
        assignments = Students.getAssignments(grades.getWorkbook());
        projects = Students.getProjects(grades.getWorkbook());
        Students.setStudentAttendance(grades.getWorkbook(),this);
        Students.setAssignmentGrades(grades.getWorkbook(), this);
        Students.setProjectGrades(grades.getWorkbook(), this);
        Students.setTeamGrades(grades.getWorkbook(), this);
        if (grades.getFormula() == null){
            formula = "AT * 0.2 + AA * 0.4 + AP * 0.4";
        } else{
            formula = grades.getFormula();
        }
    }

    public Student getStudentByName(String name){
        for (Student student: students){
            String dbName = student.getName();
            if(dbName.equals(name)){
                return student;
            }
        }
        return null;
    }
    
    public Student getStudentByID(String gtid){
        for(Student student: students){
            if(student.getGtid().equals(gtid)){
                return student;
            }
        }
        return new Student();
    }

    public int getAttendance(Student student){
        return student.getAttendance();
    }

    public String getTeam(Student student){
        return student.getTeam();
    }

    //Task Card #1.5
    public int getAverageAssignmentsGrade(Student student){
        float total = 0;
        int n=0;
        
        for (Map.Entry<String,Integer> assignmentGrades : student.getAssignmentGrades().entrySet()){
            total += assignmentGrades.getValue();
            n++;
        }

        int averageAssignmentsGrade=Math.round((float)(total/n));
        return averageAssignmentsGrade;
    }
    
    //Task Card #1.6
    public int getAverageProjectsGrade(Student student){
        float total = 0;
        float hundred = 100;
        int n=0;
        
        HashMap<String,Integer> teamProjectGrades = student.getTeamGrades();
        for (Map.Entry<String,Integer> studentProjectGrade : student.getProjectGrades().entrySet()){
            Integer teamProjectGrade = teamProjectGrades.get(studentProjectGrade.getKey());
            float teamPercent = (float)teamProjectGrade/hundred;
            float studentPercent = (float)studentProjectGrade.getValue()/hundred;
            total+= teamPercent * studentPercent;
            n++;
        }
        float average = total/n;
        
        int averageProjectsGrades=Math.round(average*hundred);
        return averageProjectsGrades;
    }

    //Task Card #1.6
    public void addIndividualContributions(String projectName, HashMap<Student, Integer> contributions){
        for (Map.Entry<Student,Integer> contribution : contributions.entrySet()){
            Student student = contribution.getKey();
            HashMap<String,Integer> projectGrades = student.getProjectGrades();
            for (Map.Entry<String,Integer> projectGrade : projectGrades.entrySet()){
                if (projectName.equals(projectGrade.getKey())){
                    projectGrade.setValue(contribution.getValue());
                }
            }
            student.setProjectGrades(projectGrades);
        }
    }
    
    //Task Card #1.7
    public int getOverallGrade(Student student){
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        String formula = grades.getFormula();
        
        if (formula == null){
            formula = this.formula;
        }
        
        String givenFormula = formula;
        if (formula.contains("AA")){
            givenFormula = formula.replace("AA", Double.toString(getAverageAssignmentsGrade(student)));
        }
        if(formula.contains("AP")){
            givenFormula = givenFormula.replace("AP", Double.toString(getAverageProjectsGrade(student)));
        }
        if(formula.contains("AT")){
            givenFormula = givenFormula.replace("AT", Double.toString(student.getAttendance()));
        }
        try {
            return (int)Math.round((double)scriptEngine.eval(givenFormula));
        } catch (GradeFormulaException | ScriptException e) {
            throw new GradeFormulaException("Malformed formula");
        }
        
    }
    
    //Task Card #2.1
    public void addAssignment(String assignment){
        assignments.add(assignment);
    }
    
    //Task Card #2.2
    public void addGradesForAssignment(String assignmentName, HashMap<Student, Integer> grades){
    	
        for (Map.Entry<Student,Integer> grade : grades.entrySet()){
            Student student = grade.getKey();
            HashMap<String,Integer> assignmentGrades = student.getAssignmentGrades();
            assignmentGrades.put(assignmentName, grade.getValue());
            student.setAssignmentGrades(assignmentGrades);
        }
    }
    
    //Task Card #2.3
    public void updateGrades(Grades grades){}
    
}