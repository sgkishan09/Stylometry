
public class Driver {
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
