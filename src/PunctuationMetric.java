import java.util.ArrayList;
import java.util.stream.Collectors;

public class PunctuationMetric implements Metric {
	double punctuationDensity = 0;

	public PunctuationMetric(Book book) {
		calculate(book);
	}

	@Override
	public void calculate(Book book) {
		int punctuationsCount = (int) book.tokens.stream().filter(p -> !book.words.contains(p) || !p.matches("\\.")).count();
		
		punctuationDensity = (double) (punctuationsCount) / book.tokens.size();
	}


	@Override
	public double compare(Metric metric2) {
		PunctuationMetric metric = (PunctuationMetric) metric2;
		return Math.abs(metric.punctuationDensity - punctuationDensity)
				/ ((metric.punctuationDensity > punctuationDensity) ? metric.punctuationDensity : punctuationDensity);
	}

	@Override
	public String toString() {
		return "Punctuation Density: " + punctuationDensity;
	}
}
