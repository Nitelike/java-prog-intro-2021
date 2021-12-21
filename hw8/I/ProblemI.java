package hw8.I;

import java.util.Scanner;

public class ProblemI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int lx = 1<<30, rx = -(1<<30), by = 1<<30, ty = -(1<<30);

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), h = sc.nextInt();

            lx = Math.min(lx, a-h);
            rx = Math.max(rx, a+h);
            by = Math.min(by, b-h);
            ty = Math.max(ty, b+h);
        }

        System.out.println((rx+lx)/2 + " " + (by+ty)/2 + " " + divUp(Math.max(rx-lx, ty-by), 2));
    }

    public static int divUp(int a, int b) {
        return (a / b) + (((a % b) > 0) ? 1 : 0);
    }
}