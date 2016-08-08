
public interface Metric {
	abstract public void calculate(Book book);

	abstract public double compare(Metric metric2);

	abstract public String toString();
}