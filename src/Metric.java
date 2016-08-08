
public interface Metric {

	static double THRESHOLD = 0.0;

	abstract public void calculate(Book book);

	abstract public boolean compare(Metric metric2);

	abstract public double getSimilarity(Metric metric2);

	abstract public String toString();
}