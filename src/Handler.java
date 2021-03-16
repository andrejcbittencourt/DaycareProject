import java.io.FileNotFoundException;

// interface for handling several FileIO classes
public interface Handler {
	public int newFile(String fileName);
	public String loadFile(int index) throws FileNotFoundException;
	public void saveFile(int index, String objects) throws FileNotFoundException;
	public Boolean fileExists(int index);
}
