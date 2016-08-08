public class PunctuationMetric implements Metric {
       double punctuationDensity = 0;

       public PunctuationMetric(Book book) {
              calculate(book);
       }

       @Override
       public void calculate(Book book) {
              //int punctuationsCount = (int) book.tokens.stream().filter(p -> !book.words.contains(p) || !p.matches("\\."))
                //           .count();
              //System.out.println(punctuationsCount);
    	   	  int periodCount = (int) book.tokens.stream().filter(p -> p.startsWith(".")).count();
              int punctuationCount = book.tokens.size() - periodCount - book.words.size();
              System.out.println(punctuationCount);
              punctuationDensity = (double) (punctuationCount) / book.tokens.size();
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