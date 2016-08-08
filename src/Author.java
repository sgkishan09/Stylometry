import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Author {
	String name;
	ArrayList<Book> books;
	Class metricType;
	Metric aggregateMetric;

	public Author() {
		books = new ArrayList<>();
	}

	public Author(String name, Class metricType) {
		this.name = name;
		books = new ArrayList<>();
		this.metricType = metricType;
	}

	public void addBook(Book book) throws Exception {
		books.add(book);
	}

	public boolean isValid(Book check, double threshold) throws Exception {
		Metric metric = getMetricInstance(check);
		int count = 0;
		for (Book book : books) {
			if (getMetricInstance(book).compare(metric) >= threshold)
				count++;
		}
		return count == books.size();
	}

	private Metric getMetricInstance(Book book) throws Exception {
		return (Metric) metricType.getConstructor(Book.class).newInstance(book);
	}

	public void printAll() throws Exception {
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			Metric metric = getMetricInstance(book);
			System.out.println(book);
			for (int j = 0; j < books.size(); j++) {
				Book book2 = books.get(j);
				System.out.println(book.name + "\t" + book2.name);
				System.out.println(String.format("%.4f", metric.compare(getMetricInstance(book2))));
			}
		}
	}
}
