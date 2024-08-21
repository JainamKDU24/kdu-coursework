package kdu.backend4;

import java.util.*;

public class Main {

    public static void main(String[] args) throws MissingGradeException, InvalidDataException {
        Logging logger = new Logging();

        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', ' '}, {'A', 'B', 'B'}};
        double[] gpa = new double[0];
        //For Task 2
        try {
            gpa = StudentUtil.calculateGPA(studentIdList, studentsGrades);
        } catch (MissingGradeException e) {
            logger.logInfo(String.valueOf(e));
        }
        int[] studentsInRange = StudentUtil.getStudentsByGPA(3.2, 3.5, studentIdList, studentsGrades);

        logger.logInfo("GPAs of different students: " + Arrays.toString(gpa));
        logger.logInfo("Students in GPA rang: " + Arrays.toString(studentsInRange));
    }
}
