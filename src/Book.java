import java.nio.file.Paths;
import java.util.*;

public class Book {
	String author;
	String name;
	ArrayList<String> lines;
	
	public Book() {
		author = "";
		name = "";
		lines = new ArrayList<>();
	}

	public Book(String author, String name, String path) throws Exception {
		this.author = author;
		this.name = name;
		this.lines = Util.readFile(path);
	}
	
}
