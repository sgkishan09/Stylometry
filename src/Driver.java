import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

import entities.Author;
import entities.Book;
import metrics.*;
import opennlp.tools.ngram.NGramModel;
import opennlp.tools.util.StringList;
import opennlp.tools.ngram.NGramGenerator;

public class Driver {

	public static HashMap<String, Integer> addToMap(List<String> list) {
		HashMap<String, Integer> countMap = new HashMap<>();
		for (String word : list) {
			if (countMap.containsKey(word))
				countMap.put(word, countMap.get(word) + 1);
			else
				countMap.put(word, 1);
		}
		return countMap;
	}

	public static HashMap<String, Integer> frequency(List<String> bigram, String currentWord) {
		List<String> startingWords = bigram.stream().filter(p -> p.split(" ")[0].equals(currentWord))
				.map(p -> p.split(" ")[1]).collect(Collectors.toList());
		return addToMap(startingWords);
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
			Book book = new Book("War and Peace", "Tolstoy", "WarAndPeace.txt");
			NGramGenerator generator = new NGramGenerator();
			List<String> words = NGramGenerator.generate(book.words, 1, " ");
			List<String> bigrams = NGramGenerator.generate(book.words, 2, " ");
			System.out.println(frequency(bigrams, "child"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
