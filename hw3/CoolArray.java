package homework;

import java.util.Arrays;

public class CoolArray {
    private int[] arr;
    private int length;

    public CoolArray() {
        arr = new int[0];
        length = 0;
    }

    public void add(int val) {
        if (length >= arr.length) {
            arr = Arrays.copyOf(arr, (arr.length*3/2) + 1);    
        }
        arr[length++] = val;
    }

    public int get(int ind) {
        return arr[ind];
    }

    public void set(int ind, int val) {
        arr[ind] = val;
    }

    public int length() {
        return length;
    }
}