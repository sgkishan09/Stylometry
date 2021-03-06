package metrics;
import entities.Book;

public class PunctuationMetric implements Metric {
       double punctuationDensity = 0;
       public double THRESHOLD = 0.85;
       public PunctuationMetric(Book book) {
              calculate(book);
       }

       @Override
       public void calculate(Book book) {
    	   	  int periodCount = (int) book.tokens.stream().filter(p -> p.startsWith(".")).count();
              int punctuationCount = book.tokens.size() - periodCount - book.words.size();
              punctuationDensity = (double) (punctuationCount) / book.tokens.size();
       }

       @Override
       public double getSimilarity(Metric metric2) {
              PunctuationMetric metric = (PunctuationMetric) metric2;
              return 1.0 - (Math.abs(metric.punctuationDensity - punctuationDensity))
                           / ((metric.punctuationDensity > punctuationDensity) ? metric.punctuationDensity : punctuationDensity);
       }
       
       public boolean compare(Metric metric2) {
    	   return getSimilarity(metric2) >= THRESHOLD;
       }

       @Override
       public String toString() {
              return "Punctuation Density: " + punctuationDensity;
       }

}