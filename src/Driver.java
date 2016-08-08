import java.util.List;

import entities.Author;
import entities.Book;
import metrics.GodFather;

public class Driver {
	public final static String NL = (char) 13 + "";

	public static void main(String[] args) {

		try {
			Author author = new Author("Shakespeare", GodFather.class);
			author.addBook(new Book("Hamlet", "Shakespeare", "Hamlet.txt"));
			author.addBook(new Book("JuliusCaesar", "Shakespeare", "Julius Caesar.txt"));
			author.addBook(new Book("Macbeth", "Shakespeare", "Macbeth.txt"));
			author.generateCorpusList();
			author.generateLetterList();
			System.out.println(author.wordCorpus);
			System.out.println(author.letterCorpus);
/*			System.out.println(author.hasWritten(new Book("War And Peace", "Tolstoy", "WarAndPeace.txt")));
			System.out.println(author.hasWritten(new Book("A Child's Dream of a Star", "Charles Dickens", "ChildsDream.txt")));
			System.out.println(author.hasWritten(new Book("Macbeth", "Shakespeare", "Macbeth.txt")));
*/			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
