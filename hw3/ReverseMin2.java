import homework.CoolArray;
import java.util.Scanner;

public class ReverseMin2 {
	public static void main(String[] args) {
		CoolArray minInCol = new CoolArray();
		CoolArray numbers = new CoolArray();
		CoolArray indexes = new CoolArray();
		Scanner in = new Scanner(System.in);
		
		while (in.hasNextLine()) {
			int curMin = Integer.MAX_VALUE, numInd = 0;
			Scanner line = new Scanner(in.nextLine());

			while (line.hasNextInt()) {
				int num = line.nextInt();
				numbers.add(num);

				if (numInd >= minInCol.length()) {
					minInCol.add(num);
				} else {
					minInCol.set(numInd, Math.min(num, minInCol.get(numInd)));
				}

				curMin = Math.min(curMin, minInCol.get(numInd));
				System.out.print(curMin + " ");
				numInd++;
			}

			indexes.add(numbers.length());
			System.out.println();
		}
	}

	private static void printInput(CoolArray numbers, CoolArray indexes) {
		int numInd = 0;
		for (int i = 0; i < indexes.length(); i++) {
			while (numInd < indexes.get(i)) {
				System.err.print(numbers.get(numInd++) + " ");
			}
			System.err.println();
		}
	}
}