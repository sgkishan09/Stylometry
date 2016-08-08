import java.nio.file.Paths;
import java.util.*;

public class Book {
	String author;
	String name;
	String content;
	ArrayList<String> tokens;
	ArrayList<String> sentences;

	public Book() {
		author = "";
		name = "";
		content = "";
	}

	public Book(String name, String author, String path) throws Exception {
		this.author = author;
		this.name = name;
		readFile(path);
	}

	private void readFile(String path) {
		// read Content from file;
	}

	private void extractTokens() {

	}

	private void extractSentences() {

	}

	public String toString() {
		String out = author + "\t" + name + "\t" + content.length();
		return out;
	}
}
