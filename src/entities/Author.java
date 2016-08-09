package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import metrics.*;
import metrics.Metric;
import opennlp.tools.ngram.NGramGenerator;
import opennlp.tools.ngram.NGramModel;

public class Author {
	String name;
	ArrayList<Book> books;
	public List<String> sentenceCorpus = new ArrayList<String>();
	public List<String> wordCorpus = new ArrayList<String>();
	public List<String> bigrams;
	public HashMap<String, Integer> startWordMap;
	public double avgWordsPerSentence;
	Class metricType;

	public Author() {
		books = new ArrayList<>();
	}

	public void setupCorpus() {
		int totalSentenceCount = 0;
		for (Book book : books) {
			sentenceCorpus.addAll(book.sentences);
			avgWordsPerSentence += (new SentenceMetric(book).getWordsPerSentence() * book.sentences.size());
			totalSentenceCount += book.sentences.size();
			wordCorpus.addAll(book.words);
		}
		avgWordsPerSentence /= totalSentenceCount;
		bigrams = NGramGenerator.generate(wordCorpus, 5, " ");
		bigrams = bigrams.stream().map(p -> p.toLowerCase()).collect(Collectors.toList());
		startWordMap = startSentence(sentenceCorpus);
	}

	public String generateSentence() {
		int count = (int) avgWordsPerSentence + ((int) Math.random() * 100 % 10);
		String out = "";
		String current = nextWord(startWordMap);
		out = current;
		for (int i = 0; i < count; i++) {
			String temp = nextWord(frequency(current));
			while (temp == null) {
				temp = nextWord(addToMap(wordCorpus));
			}
			current = temp;
			out += " " + current;
		}
		return out;
	}

	public HashMap<String, Integer> addToMap(List<String> list) {
		HashMap<String, Integer> countMap = new HashMap<>();
		for (String word : list) {
			if (countMap.containsKey(word))
				countMap.put(word, countMap.get(word) + 1);
			else
				countMap.put(word, 1);
		}
		return countMap;
	}

	public HashMap<String, Integer> frequency(String currentWord) {
		List<String> nextWordList = new ArrayList<String>();
		nextWordList = bigrams.stream().map(p -> p.split(" ")[p.split(" ").length - 1]).collect(Collectors.toList());
		return addToMap(nextWordList);
	}

	public HashMap<String, Integer> startSentence(List<String> sentences) {
		return addToMap(
				sentences.stream().map(p -> p.split(" ")[0]).map(p -> p.toLowerCase()).collect(Collectors.toList()));
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

	public String nextWord(Map<String, Integer> freqMap) {
		Map<String, Double> probMap = new LinkedHashMap<String, Double>();
		int totalSum = 0;
		double sumProb = 0.0;
		for (Integer i : freqMap.values()) {
			totalSum += i;
		}

		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			probMap.put(entry.getKey(), sumProb + (double) entry.getValue() / totalSum);
			sumProb += (double) entry.getValue() / totalSum;
		}

		double rnd = Math.random();
		for (Map.Entry<String, Double> entry : probMap.entrySet()) {
			if (entry.getValue() >= rnd)
				return entry.getKey();
		}
		return null;
	}

}
