package ws.holybook.documents.referencelibrary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ws.holybook.model.*;
import ws.holybook.utils.IdUtil;

public class CrawlReferenceLibrary {

	private static Pattern LEAF = Pattern.compile(".*-(\\d+)\\.html");
	private static Pattern LINK = Pattern.compile("/en/t/.*/.*/");

	private List<URL> getChildren(URL url) throws IOException {
		System.out.format("%s\n", url.toString());

		org.jsoup.nodes.Document htmlDoc = Jsoup.connect(url.toString()).get();
		Elements links = htmlDoc.select("a[href]");
		URL baseUrl = url;

		Set<String> hrefs = new HashSet<String>();
		List<URL> result = new ArrayList<URL>();

		for (Element e : links) {

			String href = e.attr("href");

			if (!hrefs.contains(href) && (LINK.matcher(href).matches() || LEAF.matcher(href).matches())) {
				URL childUrl = new URL(baseUrl, href);
				result.add(childUrl);
				hrefs.add(href);
			}
		}

		return result;
	}

	private void parseInput(Book document, URL url) throws IOException {
		System.out.format("%s\n", url.toString());
		org.jsoup.nodes.Document htmlDoc = Jsoup.connect(url.toString()).get();

		String title = htmlDoc.select(".pageTitle").first().text();

		if (document.getMeta().getTitle() == null) {
			document.setId(IdUtil.encode(title));
			document.getMeta().setTitle(title);
		} else if (!document.getMeta().getTitle().equals(title)) {
			// document has already a title, but it's different from the title
			// on this page
			throw new IllegalStateException(String.format("Trying to add data from %s to %s", title, document.getMeta().getTitle()));
		}

		Elements workinfo = htmlDoc.select("#workinfo");
		String fieldName = "unknown";
		for (Element w : workinfo.first().children()) {
			if (w.className().equals("workinfoname")) {
				fieldName = w.text();
				fieldName = fieldName.toLowerCase().substring(0, fieldName.length() - 1);
			} else if (w.className().equals("workinfovalue")) {
				switch (fieldName) {
				case "author":
					String authorName = w.text();
					document.getMeta().setAuthor(new Author(IdUtil.encode(authorName), authorName));
					break;
				case "source":
					document.getMeta().setSource(new Source(w.text()));
					break;
				case "pages":
					document.getMeta().setPages(Integer.parseInt(w.text()));
					break;
				}
			}
		}

		String chapter = htmlDoc.select("[class^=StextHead]").first().text();

		Section section = null;
		if (chapter.startsWith("[Pages")) {
			if (document.getContent().getText().size() > 0) {
				section = document.getContent().getText().get(0);
			} else {
				section = new Section("all");
				document.getContent().getText().add(section);
			}
		} else {
			section = new Section(chapter);
			document.getContent().getText().add(section);
		}

		Elements paragraphs = htmlDoc.select("#workselectiontext tbody div");
		int relativeParagraphNumber = 1;
		for (Element paragraph : paragraphs) {
			if (paragraph.attr("class").equals("Stext2") || section.getParagraphs().size() == 0 || relativeParagraphNumber != 1) {
				section.getParagraphs().add(paragraph.ownText());
			} else if (paragraph.attr("class").equals("Stext2Noindent")) {
				int lastIndex = section.getParagraphs().size() - 1;
				String lastParagraph = section.getParagraphs().get(lastIndex);
				section.getParagraphs().set(lastIndex, lastParagraph + " " + paragraph.ownText());
			}
			relativeParagraphNumber++;
		}

	}

	public void crawl(File directory, URL bookUrl) throws IOException {
		Book doc = new Book();
		doc.getMeta().setLanguage("en");
		doc.getMeta().setReligion(new Religion(IdUtil.encode("Bahá'í"), "Bahá'í"));
		for (URL section : getChildren(bookUrl)) {
			try {
				parseInput(doc, section);
			} catch (Exception e) {
				System.err.println("Error for parsing: " + section.toString());
				e.printStackTrace();
			}
		}
		System.out.println("Writing " + new File(directory, doc.getId()).toString() + "...");
		try {
			doc.writeXML(directory);
		} catch (JAXBException e) {
			e.printStackTrace();
			System.err.format("Error while writing %s: %s\n", doc.getId(), e.getLocalizedMessage());
		}
	}

	public void crawl(File directory) throws IOException {
		URL root = new URL("http://reference.bahai.org/en/t/alpha.html");
		for (URL book : getChildren(root)) {
			crawl(directory, book);
		}
	}

	public static void main(String[] args) throws IOException, JAXBException {
		CrawlReferenceLibrary crawler = new CrawlReferenceLibrary();
		File directory = new File(".");
		if (args.length > 0) {
			// first argument directory
			directory = new File(args[0]);
			if (!directory.exists()) {
				directory.mkdirs();
			}
		}
		if (args.length > 1) {
			// second argument link to book
			crawler.crawl(directory, new URL(args[1]));
		} else {
			crawler.crawl(directory);
		}

	}

}
