import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Book {
	String author;
	String name;
	String content;
	ArrayList<String> paragraph;
	ArrayList<String> tokens;
	ArrayList<String> sentences;
	ArrayList<String> words;

	public Book() {
		author = "";
		name = "";
		content = "";
	}

	public Book(String name, String author, String path) throws Exception {
		this.author = author;
		this.name = name;
		readFile(path);
		extractParagraph();
		extractSentences();
		extractTokens();
		extractWords();
	}

	private void extractWords() {
		words = tokens.stream().filter(p -> p.matches("[a-z].*")).collect(Collectors.toCollection(ArrayList::new));
	}

	private void readFile(String path) throws Exception {
		this.content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		content = content.replaceAll("\n", "");
	}

	private void extractParagraph() {
		final String NL = (char) 13 + "";
		paragraph = new ArrayList<>();
		for (String para : content.split(NL + NL)) {
			paragraph.add(para.replaceAll(NL, " "));
		}
	}

	private void extractTokens() throws Exception {
		InputStream is = new FileInputStream("en-token.bin");
		TokenizerModel model = new TokenizerModel(is);
		Tokenizer tokenizer = new TokenizerME(model);
		tokens = new ArrayList<String>(Arrays.asList(tokenizer.tokenize(content)));
		tokens = tokens.stream().map(p -> p.toLowerCase()).collect(Collectors.toCollection(ArrayList::new));
		is.close();
	}

	private void extractSentences() throws Exception {
		InputStream modelIn = new FileInputStream("en-sent.bin");
		SentenceModel model = new SentenceModel(modelIn);
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		sentences = new ArrayList<String>();
		paragraph.stream().forEach(content -> sentences
				.addAll(new ArrayList<String>(Arrays.asList(sentenceDetector.sentDetect(content)))));
		if (modelIn != null) {
			modelIn.close();
		}

	}

	public String toString() {
		String out = author + "\t" + name + "\tCharacters : " + content.length() + "\tSentences : " + sentences.size()
				+ "\tWords: " + tokens.size();
		return out;
	}
}
