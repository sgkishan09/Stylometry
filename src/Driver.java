import java.util.List;

public class Driver {
	public final static String NL = (char) 13 + "";

	public static void main(String[] args) {

		try {
			Author author = new Author("Shakespeare", PunctuationMetric.class);
			author.addBook(new Book("Hamlet", "Shakespeare", "Hamlet.txt"));
			author.addBook(new Book("JuliusCaesar", "Shakespeare", "Julius Caesar.txt"));
			author.addBook(new Book("Macbeth", "Shakespeare", "Macbeth.txt"));
			System.out.println(author.isValid(new Book("War And Peace", "Tolstoy", "WarAndPeace.txt"), 0.7));
			author.printAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
