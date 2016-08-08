import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Driver {
	public final static String NL = (char) 13 + "";

	public static void print(List<String> list, int start, int N) {
		for (int i = 0; i < N; i++)
			System.out.println(list.get(start + i) + "\t");
	}

	public static void main(String[] args) {

		try {
			Author author = new Author("Shakespeare", WordFrequencyMetric.class);
			author.addBook(new Book("Hamlet", "Shakespeare", "Hamlet.txt"));
			author.addBook(new Book("JuliusCaesar", "Shakespeare", "Julius Caesar.txt"));
			author.addBook(new Book("Macbeth", "Shakespeare", "Macbeth.txt"));
			author.printAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
