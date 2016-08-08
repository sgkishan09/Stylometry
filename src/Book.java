import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

	public Book() {
		author = "";
		name = "";
		content = "";
	}

	public Book(String name, String author, String path) throws Exception {
		this.author = author;
		this.name = name;
		readFile(path);
		extractSentences();
		extractTokens();
	}

	private void readFile(String path) throws Exception {
		this.content = new String(Files.readAllBytes(Paths.get(path)));
	}

	private void extractTokens() throws Exception {
		InputStream is = new FileInputStream("en-token.bin");
		TokenizerModel model = new TokenizerModel(is);
		Tokenizer tokenizer = new TokenizerME(model);
		tokens = new ArrayList<String>(Arrays.asList(tokenizer.tokenize(content)));
		is.close();
	}

	private void extractSentences() throws Exception {
		InputStream modelIn = new FileInputStream("en-sent.bin");
		SentenceModel model = new SentenceModel(modelIn);
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
		sentences = new ArrayList<String>(Arrays.asList(sentenceDetector.sentDetect(content)));
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
