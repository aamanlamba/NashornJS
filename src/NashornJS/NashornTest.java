package NashornJS;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private void go() {
		Timer t = new Timer(1000, new Ticker());
		t.start();
		JOptionPane.showMessageDialog(null,okMessage);
		System.exit(0);
	}


	//inner class
	class Ticker implements ActionListener {
		private boolean tick = true;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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


