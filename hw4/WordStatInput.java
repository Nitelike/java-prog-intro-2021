import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordStatInput {
	public static void main(String[] args) {
		String inputName = "input.txt", outputName = "output.txt";
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();

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

								int cnt = 1;
								if (map.containsKey(word)) {
									cnt += map.get(word);
								}
								map.put(word, cnt);
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
				for (Map.Entry<String, Integer> mapElement : map.entrySet()) {
					String word = mapElement.getKey();
					Integer cnt = mapElement.getValue();
					out.write(word + " " + cnt);
					out.newLine();
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