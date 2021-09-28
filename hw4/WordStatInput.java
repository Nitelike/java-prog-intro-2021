import java.util.*;
import java.io.*;
import homework.CoolStringArray;
import java.nio.charset.StandardCharsets;

public class WordStatInput {
	public static void main(String[] args) {
		String inputName = "input.txt", outputName = "output.txt";
		CoolStringArray input = new CoolStringArray();

		try {
			inputName = args[0];
			outputName = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Not all arguments were provided. Can't set right filenames.");
		}

		try {
			BufferedReader in = new BufferedReader(new FileReader(inputName, StandardCharsets.UTF_8));

			try {
				String line = in.readLine();

				while (line != null) {
					line = line.toLowerCase() + " ";
					int leftPtr = 0;

					for (int i = 0; i < line.length(); i++) {
						char c = line.charAt(i);
						if (!canBeInWord(c)) {
							if (i - leftPtr >= 1) {
								String word = line.substring(leftPtr, i);
								input.add(word);
							}
							leftPtr = i+1;
						}
					}

					line = in.readLine();
				}
			} finally {
				in.close();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Can't find " + inputName);
		} catch (IOException e) {
			System.err.println("Can't read from " + inputName);
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputName, StandardCharsets.UTF_8));

			try {
				Integer[] indexes = new Integer[input.length()];
				int[] cnt = new int[input.length()];

				for (int i = 0; i < indexes.length; i++) {
					indexes[i] = i;
				}
				Arrays.sort(indexes, new Comparator<Integer>() {
				    public int compare(Integer o1, Integer o2) {
				        return input.get(o2).compareTo(input.get(o1));
				    }
				});

				for(int i = 0; i < indexes.length; i++) {
					int j = i;
					int minInd = indexes[i];
					while(j < indexes.length && input.get(indexes[i]).equals(input.get(indexes[j]))) {
						minInd = Math.min(minInd, indexes[j]);
						j++;
					}
					cnt[minInd] = j-i;
					i = j-1;
				}

				for(int i = 0; i < input.length(); i++) {
					if(cnt[i] > 0) {
						out.write(input.get(i) + " " + cnt[i]);
					    out.newLine();
					}
				}

			} finally {
				out.close();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Can't find " + outputName);
		} catch (IOException e) {
			System.err.println("Can't write to " + outputName);
		}
	}

	private static boolean canBeInWord(char c) {
		return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
	}
}