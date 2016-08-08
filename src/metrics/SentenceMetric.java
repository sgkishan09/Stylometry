package metrics;
import java.util.ArrayList;

import entities.Book;

public class SentenceMetric implements Metric {

	public double THRESHOLD = 0.7;
	public double avgNoOfWords;
	public double avgNoOfLetters;

	public SentenceMetric(Book book) {
		calculate(book);
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
