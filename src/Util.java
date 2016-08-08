import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {
	public static ArrayList<String> readFile(String path) throws Exception {
		return (ArrayList<String>) Files.readAllLines(Paths.get(path));
	}

}
