package kdu.backend2;
import java.util.*;

public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] result = new double[studentIdList.length];

        for (int i = 0; i < studentIdList.length; i++) {
            int total = 0;
            int totalCourses = studentsGrades[i].length;

            for (char grade : studentsGrades[i]) {
                switch (grade) {
                    case 'A':
                        total += 4;
                        break;
                    case 'B':
                        total += 3;
                        break;
                    case 'C':
                        total += 2;
                        break;
                }
            }
            result[i] = (double) total / totalCourses;
        }
        return result;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        if (lower < 0 || higher < 0 || lower > higher) {
            return null;
        }
        double[] gpa = calculateGPA(studentIdList, studentsGrades);
        int count = 0;
        for (double j : gpa) {
            if (j >= lower && j <= higher) {
                count++;
            }
        }
        int[] result = new int[count];
        int index = 0;

        for (int i = 0; i < studentIdList.length; i++) {
            if (gpa[i] >= lower && gpa[i] <= higher) {
                result[index++] = studentIdList[i];
            }
        }
        return result;
    }
}
