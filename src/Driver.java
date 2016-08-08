import java.util.List;
import java.util.*;

import entities.Author;
import entities.Book;
import metrics.*;
import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;

public class Driver {
	public static HashMap<String, Integer> countMap = new HashMap<>();

	public static void addToMap(String p) {
		if (countMap.containsKey(p))
			countMap.put(p, countMap.get(p) + 1);
		else
			countMap.put(p, 1);

	}

	public static void main(String[] args) {
		try {
			/*
			 * Author author = new Author("Shakespeare", SentenceMetric.class);
			 * author.addBook(new Book("Othello", "Shakespeare",
			 * "Othello.txt")); author.addBook(new Book("JuliusCaesar",
			 * "Shakespeare", "Julius Caesar.txt")); author.addBook(new
			 * Book("Macbeth", "Shakespeare", "Macbeth.txt"));
			 */
			Book book = new Book("Othello", "Shakespeare", "Othello.txt");
			NGramModel model = new NGramModel();
			book.words.forEach(p -> model.add(p, 2, 2));
			Spliterator<StringList> iter = model.spliterator();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
