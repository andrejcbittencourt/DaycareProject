package kea.dat20i.libraries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// class used to handle several kea.dat20i.libraries.FileIO classes
public class FileHandler implements Handler {
	private final ArrayList<FileIO> files;

	// constructor
	public FileHandler() {
		this.files = new ArrayList<>();
	}

	// methods
	@Override
	public String loadFile(int index) throws FileNotFoundException {
		return files.get(index).read();
	}

	@Override
	public void saveFile(int index, String objects) throws FileNotFoundException {
		files.get(index).write(objects);
	}

	@Override
	public Boolean createFile(int index) throws IOException {
		return files.get(index).create();
	}

	@Override
	public int newFile(String filename) {
		this.files.add(new FileIO(filename));
		return files.size()-1;
	}

	@Override
	public Boolean fileExists(int index) {
		return this.files.get(index).exists();
	}
}