package homework;

import java.util.Arrays;

public class CoolStringArray {
    private String[] arr;
    private int length;

    public CoolStringArray() {
        arr = new String[0];
        length = 0;
    }

    public void add(String val) {
        if (length >= arr.length) {
            arr = Arrays.copyOf(arr, (arr.length*3/2) + 1);    
        }
        arr[length++] = val;
    }

    public String get(int ind) {
        return arr[ind];
    }

    public void set(int ind, String val) {
        arr[ind] = val;
    }

    public int length() {
        return length;
    }

    public void sort() {
    	Arrays.sort(arr, 0, length);
    }
}