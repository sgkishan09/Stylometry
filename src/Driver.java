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

	public static void main(String[] args) {
		try {
			Author author = new Author("Shakespeare", SentenceMetric.class);
			author.addBook(new Book("Othello", "Shakespeare", "Othello.txt"));
			author.addBook(new Book("JuliusCaesar", "Shakespeare", "Julius Caesar.txt"));
			author.addBook(new Book("Macbeth", "Shakespeare", "Macbeth.txt"));
			author.setupCorpus();
			for (int i = 0; i < 10; i++) {
				System.out.println(author.generateSentence());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
