package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import metrics.Metric;

public class Author {
	String name;
	ArrayList<Book> books;
	public HashMap<String, Integer> wordCorpus = new HashMap<String, Integer>();
	public HashMap<String, Integer> letterCorpus = new HashMap<String, Integer>();
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

	public void generateCorpusList() {
		for (Book book : books) {
			ArrayList<String> words = book.words;
			for (String word : words) {
				if (wordCorpus.containsKey(word))
					wordCorpus.put(word, wordCorpus.get(word) + 1);
				else
					wordCorpus.put(word, new Integer(1));
			}
		}

	}

	public void generateLetterList() {
		for (char i = 'a'; i < 'z'; i++) {
			letterCorpus.put(String.valueOf(i), 0);
		}

		for (Book book : books) {
			for (Map.Entry<String, Integer> entry : letterCorpus.entrySet()) {
				letterCorpus.put(entry.getKey(), entry.getValue()
						+ (book.content.length() - book.content.replaceAll(entry.getKey(), "").length()));
			}
		}
	}
	
	public String nextWord(Map<String, Integer> freqMap) {		
		Map<String, Double> probMap = new LinkedHashMap<String, Double>();
		int totalSum = 0;
		double sumProb = 0.0;
		for(Integer i : freqMap.values()) {
			totalSum += i;
		}
		
		for(Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			probMap.put(entry.getKey(), sumProb + (double)entry.getValue() / totalSum);
			sumProb += (double)entry.getValue() / totalSum;
		}
		
		
		double rnd = Math.random();
		for(Map.Entry<String, Double> entry : probMap.entrySet()) {
			if(entry.getValue() >= rnd)
				return entry.getKey();
		}
		return null;
	}

}
