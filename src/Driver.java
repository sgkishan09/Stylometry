
public class Driver {
	public static void main(String[] args) {
		try {
			Book book1 = new Book("Book1", "Kishan", "C:\\Input\\Book1.txt");
			Book book2 = new Book("Book2", "Kishan", "C:\\Input\\Book1.txt");
			Metric metric1 = null;
			Metric metric2 = null;
			System.out.println(metric1);
			System.out.println(metric2);
			System.out.println(metric1.compare(metric2));
		} catch (Exception e) {

		}
	}
}
