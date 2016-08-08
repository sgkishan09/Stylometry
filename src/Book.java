import java.nio.file.Paths;
import java.util.*;

public class Book {
	String author;
	String name;
	ArrayList<String> lines;
	ArrayList<String> words;

	public Book() {
		author = "";
		name = "";
		words = new ArrayList<>();
	}

	public Book(String author, String name, String path) throws Exception {
		this.author = author;
		this.name = name;
		this.lines = Util.readFile(path);
	}

}
