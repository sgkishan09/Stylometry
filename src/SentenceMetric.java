import java.util.ArrayList;

public class SentenceMetric implements Metric {

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
	public double compare(Metric metric2) {
		SentenceMetric metric = (SentenceMetric) metric2;
		double avgWordDiff = Math.abs(avgNoOfWords - metric.avgNoOfWords)
				/ ((avgNoOfWords > metric.avgNoOfWords) ? avgNoOfWords : metric.avgNoOfWords);
		double avgLetterDiff = Math.abs(avgNoOfLetters - metric.avgNoOfLetters)
				/ ((avgNoOfLetters > metric.avgNoOfLetters) ? avgNoOfLetters : metric.avgNoOfLetters);

		double percentageMatch = 1 - (avgWordDiff + avgLetterDiff) / 2;
		return percentageMatch;
	}

	@Override
	public String toString() {
		return "Sentence Metric : [" + avgNoOfLetters + "\t" + avgNoOfWords + "]";
	}

}
