
public class ComplexMetric {
	Metric wordFrequency;
	Metric sentence;
	Metric punctuation;
	int numOfMetrics = 3;

	public ComplexMetric(Book book) {
		wordFrequency = new WordFrequencyMetric(book);
		sentence = new SentenceMetric(book);
		punctuation = new PunctuationMetric(book);
	}

	public boolean compare(ComplexMetric complexMetric2) {

		int trueMetrics = 0;
		if (wordFrequency.compare(complexMetric2.wordFrequency)) {
			trueMetrics++;
		}

		if (sentence.compare(complexMetric2.sentence)) {
			trueMetrics++;
		}

		if (sentence.compare(complexMetric2.punctuation)) {
			trueMetrics++;
		}

		if (numOfMetrics % 2 == 0) {
			return (numOfMetrics / 2) <= trueMetrics;
		}

		return (numOfMetrics / 2) < trueMetrics;

	}

}
