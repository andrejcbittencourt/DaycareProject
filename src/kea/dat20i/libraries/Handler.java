package kea.dat20i.libraries;

import java.io.FileNotFoundException;
import java.io.IOException;

// interface for handling several kea.dat20i.libraries.FileIO classes
public interface Handler {
	public int newFile(String fileName);
	public Boolean createFile(int index) throws IOException;
	public String loadFile(int index) throws FileNotFoundException;
	public void saveFile(int index, String objects) throws FileNotFoundException;
	public Boolean fileExists(int index);
}
