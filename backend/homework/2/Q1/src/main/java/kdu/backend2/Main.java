package kdu.backend2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};

        double[] gpa = StudentUtil.calculateGPA(studentIdList, studentsGrades);
        int[] studentsInRange = StudentUtil.getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrades);

        System.out.println("GPAs of different students: " + Arrays.toString(gpa));
        System.out.println("Students in GPA rang: " + Arrays.toString(studentsInRange));
    }
}
