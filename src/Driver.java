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
			Book book = new Book("A Child's Dream of a Star", "Charles Dickens", "ChildsDream.txt");
			Metric metric = new WordFrequencyMetric(book);
			System.out.println(metric);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
