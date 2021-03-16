package kea.dat20i.libraries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

// class used for file IO operations
public class FileIO {
	private final File file;

	// constructor
	FileIO(String filename) {
		this.file = new File(filename);
	}

	// methods
	public String read() throws FileNotFoundException {
		Scanner scan = new Scanner(this.file);
		StringBuilder content = new StringBuilder();
		while(scan.hasNextLine()) {
			content.append(scan.nextLine()).append('\n');
		}
		scan.close();

		return content.toString();
	}

	public void write(String text) throws FileNotFoundException {
		PrintStream printer = new PrintStream(new FileOutputStream(file));
		printer.print(text);
		printer.close();
	}

	public Boolean exists() {
		return this.file.exists();
	}
}