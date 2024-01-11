package kdu.backend4;

public class Swap {
    private Swap(){}

    public static <T> T[] swapping(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;

        return array;
    }

}
