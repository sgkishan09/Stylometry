import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

public class WordFrequencyMetric implements Metric {
	public double THRESHOLD = 0.4;
	final static int N = 500;
	final static String commonWordsFile = "CommonWords.txt";
	Set<String> topNWords = new HashSet<String>();
	Set<String> commonWords;

	public WordFrequencyMetric(Book book) {
		readFile();
		calculate(book);
	}

	private void readFile() {
		try {
			commonWords = new HashSet<String>(Files.readAllLines(Paths.get(commonWordsFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void calculate(Book book) {
		Map<String, Integer> freqMap = createFreqMap(book.words);
		extractTopNWords(freqMap);
	}

	private void extractTopNWords(Map<String, Integer> freqMap) {
		int num = 0;
		List<Map.Entry<String, Integer>> freqList = new ArrayList<>(freqMap.entrySet());
		for(int i = freqList.size() - 1; i >= 0; i--) {
			if(num < N && !commonWords.contains(freqList.get(i).getKey())) {
				topNWords.add(freqList.get(i).getKey());
				num++;
			}
		}
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Map.Entry<K, V>> st = map.entrySet().stream();
		st.sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		return result;
	}

	public Map<String, Integer> createFreqMap(ArrayList<String> words) {
		Map<String, Integer> freqMap = new TreeMap<String, Integer>();
		for (String word : words) {
			if (!freqMap.containsKey(word)) {
				freqMap.put(word, 1);
			} else {
				freqMap.put(word, freqMap.get(word) + 1);
			}
		}
		return sortByValue(freqMap);
	}

	@Override
	public double getSimilarity(Metric metric2) {
		Set<String> topNWords = this.topNWords;
		topNWords.retainAll(((WordFrequencyMetric) metric2).topNWords);
		return (double)topNWords.size() / N;
	}
	
	@Override
	public boolean compare(Metric metric2) {
		return getSimilarity(metric2) >= THRESHOLD;
	}
	
	

	public String toString() {
		return "Word Frequency Metric : " + topNWords;
	}
}