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

			Book book1 = new Book("War And Peace", "Leo Tolstoy", "WarAndPeace.txt");
			System.out.println(book1);
			
			Metric sm = new SentenceMetric(book1);
			Metric pm = new PunctuationMetric(book1);

			System.out.println(sm + "\n SENTENCE Second Metric: " + sm);
			System.out.println(sm.compare(sm));
			
			System.out.println(pm.compare(pm));
			System.out.println(pm + "\n Second Metric: " + pm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
