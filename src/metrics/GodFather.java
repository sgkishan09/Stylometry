package metrics;
import entities.Book;

public class GodFather implements Metric {
	Metric wordFrequency;
	Metric sentence;
	Metric punctuation;
	int numOfMetrics = 3;

	public GodFather(Book book) {
		calculate(book);
	}

	@Override
	public boolean compare(Metric metric) {
		GodFather complexMetric2 = (GodFather) metric;
		int trueMetrics = 0;
		if (wordFrequency.compare(complexMetric2.wordFrequency)) {
			trueMetrics++;
		}

		if (sentence.compare(complexMetric2.sentence)) {
			trueMetrics++;
		}

		if (punctuation.compare(complexMetric2.punctuation)) {
			trueMetrics++;
		}

		if (numOfMetrics % 2 == 0) {
			return (numOfMetrics / 2) <= trueMetrics;
		}

		return (numOfMetrics / 2) < trueMetrics;

	}

	@Override
	public void calculate(Book book) {
		wordFrequency = new WordFrequencyMetric(book);
		sentence = new SentenceMetric(book);
		punctuation = new PunctuationMetric(book);

	}

	@Override
	public double getSimilarity(Metric metric2) {
		return 0;
	}

}
