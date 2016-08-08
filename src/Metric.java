
public interface Metric {
	abstract public void calculate(Book book);

	abstract public boolean compare(Metric metric2);
}