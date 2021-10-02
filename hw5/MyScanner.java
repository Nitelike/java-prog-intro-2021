package homework;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import homework.CoolArray;

public class MyScanner {
	private Reader in;
	private final int BUFFER_SIZE = 1024;
	private char[] buffered = new char[0];
	private int ptr = 0;
	private boolean endOfInput = false;
	private boolean hasNextChar = true;

	public MyScanner() {
		in = new InputStreamReader(System.in);
	}

	public MyScanner(String filename) throws FileNotFoundException, IOException {
		in = new FileReader(filename, StandardCharsets.UTF_8);
	}

	private void read() throws IOException {
		char[] buffer = new char[BUFFER_SIZE];
		int read = in.read(buffer);

		buffered = new char[Math.max(read, 0)];
		ptr = 0;

		if (read < BUFFER_SIZE) {
			endOfInput = true;
		}

		for (int i = 0; i < buffered.length; i++) {
			buffered[i] = buffer[i];
		}
	}

	private void updateBuffered() throws IOException {
		if (!endOfInput && ptr >= buffered.length) {
			read();
		}
	}

	private boolean hasNextChar() throws IOException {
		updateBuffered();
		if (ptr >= buffered.length) {
			hasNextChar = false;
		}
		return hasNextChar;
	}

	private char nextChar() throws IOException {
		updateBuffered();
		if (ptr >= buffered.length) {
			throw new IOException("Stream doesn't have next char");
		} else {
			return buffered[ptr++];
		}
	}

	public boolean hasNext() throws IOException {
		while (true) {
			for (int i = ptr; i < buffered.length; i++) {
				if (!Character.isWhitespace(buffered[i])) {
					return true;
				}
			}

			if (!endOfInput) {
				updateBuffered();
			} else {
				return false;
			}
		}
	}

	public String next() throws IOException {
		boolean wasNotWhitespace = false;
		CoolArray buf = new CoolArray();

		while (hasNextChar()) {
			char c = nextChar();
			if (Character.isWhitespace(c) && wasNotWhitespace) {
				break;
			} else {
				wasNotWhitespace = true;
				buf.add(c);
			}
		}

		if (buf.length() == 0) {
			throw new IOException("Stream doesn't have next word");
		} else {
			return buf.toString();
		}
		
	}
	
	public void close() throws IOException {
		in.close();
	}	
}