import java.util.Arrays;

public class HelloWorld {
    private static void increaseArray(int[] arr) {
        arr = new int[10];
    }

    private static void fuckArray(int[] arr) {
        arr[0] = -13;
    }

    public static void main(String[] args) {
        int[] arr = new int[5];

        System.out.println(arr.length);
        increaseArray(arr);
        System.out.println(arr.length);

        System.out.println(arr[0]);
        fuckArray(arr);
        System.out.println(arr[0]);
    }
}