package metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Book;

public class SentenceMetric implements Metric {

	public double THRESHOLD = 0.7;
	public double avgNoOfWords;
	public double avgNoOfLetters;
	private Book book;
	private int totalNoOfWords = 0;
	private int totalNoOfLetters = 0;

	public SentenceMetric(Book book) {
		calculate(book);
		this.book = book;
		addTotalNoOfWords();
	}

	public double getLettersPerWord() {
		return (double) totalNoOfLetters / totalNoOfWords;
	}

	public void addTotalNoOfLetters(List<String> words) {
		for (String word : words) {
			totalNoOfLetters += word.length();
		}
	}

	public void addTotalNoOfWords() {
		for (String sentence : this.book.sentences) {
			totalNoOfWords += sentence.split(" ").length;
			addTotalNoOfLetters(Arrays.asList((sentence.split(" "))));
		}
	}

	public double getWordsPerSentence() {
		return (double) totalNoOfWords / this.book.sentences.size();
	}

	@Override
	public void calculate(Book book) {
		double totalNoOfWords = 0, totalNoOfLetters = 0;
		ArrayList<String> sentences = book.sentences;
		for (String sentence : sentences) {
			totalNoOfWords += sentence.split(" ").length;
			totalNoOfLetters += sentence.length();
		}
		avgNoOfWords = totalNoOfWords / sentences.size();
		avgNoOfLetters = totalNoOfLetters / sentences.size();

	}

	@Override
	public double getSimilarity(Metric metric2) {
		SentenceMetric metric = (SentenceMetric) metric2;
		double avgWordDiff = Math.abs(avgNoOfWords - metric.avgNoOfWords)
				/ ((avgNoOfWords > metric.avgNoOfWords) ? avgNoOfWords : metric.avgNoOfWords);
		double avgLetterDiff = Math.abs(avgNoOfLetters - metric.avgNoOfLetters)
				/ ((avgNoOfLetters > metric.avgNoOfLetters) ? avgNoOfLetters : metric.avgNoOfLetters);

		double percentageMatch = 1 - (avgWordDiff + avgLetterDiff) / 2;
		return percentageMatch;
	}

	@Override
	public boolean compare(Metric metric2) {
		return getSimilarity(metric2) >= THRESHOLD;
	};

	@Override
	public String toString() {
		return "Sentence Metric : [" + avgNoOfLetters + "\t" + avgNoOfWords + "]";
	}

}
