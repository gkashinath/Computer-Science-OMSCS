package edu.gatech.seclass.gradescalculator;

import java.util.HashMap;
import java.util.HashSet;

public class Student {

    private String name;
    private String gtid;
    private int attendance;
    private String team;
    private String email;
    private HashMap<String,Integer> assignmentGrades;
    private HashMap<String,Integer> teamGrades;
    private HashMap<String,Integer> projectGrades;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGtid() {
        return gtid;
    }

    public void setGtid(String gtid) {
        this.gtid = gtid;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student(){}
    
    public HashMap<String,Integer> getAssignmentGrades() {
        return assignmentGrades;
    }
    
    public void setAssignmentGrades(HashMap<String,Integer> assignmentGrades) {
        this.assignmentGrades = assignmentGrades;
    }

    public HashMap<String, Integer> getTeamGrades() {
        return teamGrades;
    }

    public void setTeamGrades(HashMap<String, Integer> teamGrades) {
        this.teamGrades = teamGrades;
    }
    
    public HashMap<String,Integer> getProjectGrades() {
        return projectGrades;
    }

    public void setProjectGrades(HashMap<String,Integer> projectGrades) {
        this.projectGrades = projectGrades;
    }
        
    public Student(String name, String gtid){
        boolean nameFound = false; 
        for (Student student : Course.getStudents()){
            if (gtid.equals(student.getGtid()) && name.equals(student.getName())){
                nameFound = true;
                this.name = name;
                this.gtid = gtid;
                this.team = student.getTeam();
                this.attendance = student.getAttendance();
                this.assignmentGrades = student.getAssignmentGrades();
                this.projectGrades = student.getProjectGrades();
                this.teamGrades = student.getTeamGrades();
            }
        }
        if (!nameFound){
        	HashSet<Student> students = Course.getStudents();
            students.add(this);
            Course.setStudents(students);
        }
    }
}