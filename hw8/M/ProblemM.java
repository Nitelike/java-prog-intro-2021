import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class ProblemM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int q = sc.nextInt();
        while (q-- > 0) {
            int n = sc.nextInt(), ans = 0;
            int[] a = new int[n];
            Map<Integer, Integer> mp = new HashMap<>();

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            for (int j = n-1; j >= 0; j--) {
                for (int i = 0; i < j; i++) {
                    int need = 2 * a[j] - a[i];
                    if (mp.get(need) != null) {
                        ans += mp.get(need);
                    }
                }
                mp.put(a[j], mp.computeIfAbsent(a[j], k -> 0) + 1);
            }

            System.out.println(ans);
        }
    }
}