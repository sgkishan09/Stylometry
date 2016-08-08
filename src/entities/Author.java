package entities;

import java.util.ArrayList;

import metrics.Metric;

public class Author {
	String name;
	ArrayList<Book> books;
	Class metricType;

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

	public boolean hasWritten(Book check) throws Exception {
		Metric metric = getMetricInstance(check);
		int count = 0;
		for (Book book : books) {
			if (getMetricInstance(book).compare(metric))
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
				if (j != i) {
					Book book2 = books.get(j);
					System.out.println(book.name + "\t" + book2.name);
					System.out.println(metric.compare(getMetricInstance(book2)));
					System.out.println("--------------------");

				}
			}
		}
	}
}
