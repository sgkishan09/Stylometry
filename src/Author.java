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
		if (isValid(book)) {
			books.add(book);
		}

	}

	public boolean isValid(Book book) throws Exception {
		Metric metric = (Metric) metricType.getConstructor(Book.class).newInstance(book);
		// return aggregateMetric.compare(metric) >= 0.5;
		return true;
	}

	private Metric getMetricInstance(Book book) throws Exception {
		return (Metric) metricType.getConstructor(Book.class).newInstance(book);
	}

	public void printAll() throws Exception {
		for (Book book : books)
			System.out.println(book + "\n" + getMetricInstance(book));
	}
}
