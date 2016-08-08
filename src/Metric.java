
public interface Metric {
	public Object metric = null;

	abstract public void calculate(Book book);

	abstract public boolean compare(Metric metric2);
}