package hw8.A;

import java.util.Scanner;

public class ProblemA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();

        System.out.println(2 * divUp(n-b, b-a) + 1);
    }

    public static int divUp(int a, int b) {
        return (a / b) + (((a % b) > 0) ? 1 : 0);
    }
}