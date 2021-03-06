package org.aamanlamba.NashornJS;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * @author aamanlamba
 * Tester class to test various Java 8 functions
 */
public class NashornTest {
	
private String tickMessage="Tick...";
private String tockMessage="Tock...";
private String okMessage = "Press OK to exit program";

	public static void main(String[] args) throws ScriptException, SAXException, IOException, ParserConfigurationException, InterruptedException {
		
		//create anonymous class that implements Ball interface
		Ball b = new Ball() {
			public void hit() {
				System.out.println("Hit ball!");
			}
		};
		//lambda expression - creates anonymous class based on functional interface with single abstract method
		//Lambda ex: (params)->{statement;...};
		Ball b2 = () -> {
			System.out.println("Hit Lambda ball");
		};
		
		b.hit();
		b2.hit();

		//method reference to functional interface - use java.util.function.Consumer<Integer>
		Consumer<Integer> printer = System.out::println;
		Consumer<Number> printer2 = System.out::println;
		Stream.of(1,43,52,23,123)
				.forEach(printer);
		
		//method reference to a Supplier static method
		Stream.generate(Math::random)
			.limit(10)
			.forEach(printer2);
		
		//demonstrate importing csv to JSON
		ImportRunnable imp = new ImportRunnable("src/cities.csv");
		JSONUtils jsonUtils = new JSONUtils();
		imp.run();
		//output csv as list
		for(Record rec: imp.listOfRecords) {
			System.out.println(rec.id+"-"+rec.city+"-->"+rec.pop);
		}
		//output csv as map
		System.out.println(imp.mapOfRecords.get("Rome").pop.toString());
		List<JSONObject> jsonObjs = new ArrayList<JSONObject>();
		jsonUtils.readJSONObjectFromFile("src/blockchainTest.json", jsonObjs);
		for(JSONObject jObj: jsonObjs)
			System.out.println(jObj);
		//Do String and Stream stuff
		sortStrings();
		
		// use java.nio.file
		findWordsinFiles();
		
		
		//load json array
		//jsonUtils.readJSONArrayFromFile("src/JsonArray.json", jArr);
		//System.out.println(jArr);
		//Demonstrate calling an action event listener of an inner class
		NashornTest nt = new NashornTest();
		nt.go();
		
		//demonstrate invoking nashorn Javascript engine inside Java function
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
		
		DocumentBuilderFactory dbFactory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element el = doc.createElement("test");
		doc.appendChild(el);


		String xml = "<foo bar=\"1\">Hi <baz>there</baz></foo>";
		Document doc2 = dBuilder.parse(new ByteArrayInputStream(xml.getBytes()));

		Node node = doc.importNode(doc2.getDocumentElement(), true);
		el.appendChild(node);
		System.out.println(el);
		
		scriptEngine.eval("load('http://visjs.org/dist/vis.min.js')");
		//scriptEngine.eval("load('src/hello.js')");
		
		// demonstrate iteration on List
		List<Integer> intList = Arrays.asList(1,3,5,2,5);
		intList.forEach(el1 -> System.out.println(el1));
		
		
	}

	/*
	 * Use java.nio.file functions
	 * on Webster's dictionary
	 */
	private static void findWordsinFiles() {
		//try-catch-with-resources
		// Load dictionary file as stream of strings for each line
		try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/web2"))){
			lines.filter(s -> s.length()>20)
							.sorted(Comparator.comparingInt(String::length).reversed())
							.limit(10)
							.forEach(w ->System.out.println(w));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Examples for java.util.function with Strings and streams
	 */
	private static void sortStrings() {
		List<String> bonds = Arrays.asList("Moore","Connery","Craig","Lazeby","Allen","Dalton","Brosnan");
		List<String> sorted = bonds.stream()
								.sorted(Comparator.naturalOrder())
								.collect(Collectors.toList());
		System.out.println("Sorted collection");
		System.out.println(sorted);
		
		System.out.println("Print original collection using anonymous implementation of Consumer interface");
		bonds.forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				System.out.println(s);
			}
		});
		System.out.println("sort using lambda expression");
		sorted.forEach(s -> System.out.println(s));
		System.out.println("-------");
		// Filter stream and store in Optional container object which may or may not contain a value - check with orElse, orElseGet, get, isPresent
		// Note the use of predicate in filter
		Optional<String> filteredStrings = bonds.stream()
											.filter(name -> name.startsWith("X"))
											.findFirst();
		//Output matched result or entire list
		
		System.out.println(filteredStrings.orElseGet(()->String.format("Result not found", bonds.stream().collect(Collectors.joining(",")))));
		System.out.println("Partition list by condition (length=5)");
		System.out.println(bonds.stream()
							.collect(Collectors.partitioningBy(s->s.length()==5)));
		System.out.println("-------");
		System.out.println("Group list by condition (length=5) - create a map between condition and result group");
		System.out.println(bonds.stream()
							.collect(Collectors.groupingBy(s->s.length()==5)));
		System.out.println("-------");
		System.out.println("Partition list by condition (length=5) and show counts in each partition");
		System.out.println(bonds.stream()
							.collect(Collectors.partitioningBy(s->s.length()==5,
													Collectors.counting())));
		System.out.println("-------");
		System.out.println("Partition list by condition (length=5)");
		System.out.println(bonds.stream()
							.collect(Collectors.groupingBy(s->s.length()==5,
															Collectors.counting())));
		System.out.println("-------");
		System.out.println("Use map-filter-reduce paradigm on array");
		System.out.println("Number of Bonds:" + bonds.stream()
													.map(s->s.length())
													.count());	
		System.out.println("-------");
		System.out.println("Check Palindrome:" + String.valueOf(isPalindrome("Able was I ere I saw Elba")));								
	}

	/*
	 * convert String to stream using chars or codePoints and then apply stream-type pipeline functions
	 */
	private static boolean isPalindrome(String s) {
		String forward = s.toLowerCase().codePoints()
						  .filter(Character::isLetterOrDigit)
						  .collect(StringBuilder::new,
								  StringBuilder::appendCodePoint,
								  StringBuilder::append)
						  .toString();
		String backward = new StringBuilder(forward).reverse().toString();
		return forward.equals(backward);
	}

	private void go() {
		Timer t = new Timer(1000, new Ticker());
		t.start();
		JOptionPane.showMessageDialog(null,okMessage);
		System.exit(0);
	}


	/*
	 * Inner class implementing actionListener
	 */
	class Ticker implements ActionListener {
		private boolean tick = true;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(tick) {
				System.out.println(tickMessage);
			}
			else {
				System.out.println(tockMessage);
			}
			tick = !tick;
		}
		
	}
}


