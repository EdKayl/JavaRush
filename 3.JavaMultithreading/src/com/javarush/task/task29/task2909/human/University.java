package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
    String name;
    int age;
    private List<Student> students = new ArrayList<>();


    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public University() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double grade) {
        //TODO:
        for(Student student : students) {
            if(grade == student.getAverageGrade()) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        List<Student> tempList = new ArrayList<>(students);
        Collections.sort(tempList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return ((Double)o2.getAverageGrade()).compareTo(o1.getAverageGrade());
            }
        });
        return tempList.get(0);
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        List<Student> tempList = new ArrayList<>(students);
        Collections.sort(tempList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return ((Double)o1.getAverageGrade()).compareTo(o2.getAverageGrade());
            }
        });
        return tempList.get(0);
    }

    public void expel(Student student) {
        students.remove(student);
    }
}