package kdu.backend4;


public class StudentUtil {
    private StudentUtil() {

    }

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException {
        //Task 1
        if(studentIdList.length!=studentsGrades.length){
            throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " +
                    studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
        }
        double[] result = new double[studentIdList.length];

        for (int i = 0; i < studentsGrades.length; i++) {
            double gpa = 0.0;

            for (int j = 0; j < studentsGrades[i].length; j++) {
                if (studentsGrades[i][j] == 'A') {
                    gpa += 4.0;
                } else if (studentsGrades[i][j] == 'B') {
                    gpa += 3.0;
                } else if (studentsGrades[i][j] == 'C') {
                    gpa += 2.0;
                } else if (studentsGrades[i][j] == ' ') {
                    // Task 2
                    throw new MissingGradeException(studentIdList[i]);

                }
            }

            result[i] = gpa/studentsGrades[i].length;
        }
        return result;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) throws MissingGradeException, InvalidDataException {
        if (lower < 0 || higher < 0 || lower > higher) {
            return new int[0];
        }
        double[] gpa;
        //For Task 3
        try {
            gpa = calculateGPA(studentIdList, studentsGrades);
        }
        catch(MissingGradeException e){
            throw new InvalidDataException(e.getMessage());
        }
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


