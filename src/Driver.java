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
			Author author = new Author("Leo Tolstoy", WordFrequencyMetric.class);
			Book book1 = new Book("A Child's Dream of a Star", "Charles Dickens", "ChildsDream.txt");
			Book book2 = new Book("War And Peace", "Leo Tolstoy", "WarAndPeace.txt");
			author.addBook(book2);
			author.printAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
