package homework;

import java.util.Arrays;

public class CoolArray {
    private char[] arr = new char[0];
    private int length;

    public void add(char val) {
        if (length == arr.length) {
            arr = Arrays.copyOf(arr, (arr.length * 3 / 2) + 1);    
        }
        arr[length++] = val;
    }

    public char get(int ind) {
        return arr[ind];
    }

    public void set(int ind, char val) {
        arr[ind] = val;
    }

    public int length() {
        return length;
    }

    public String toString() {
        return String.valueOf(arr).substring(0, length);
    }
}