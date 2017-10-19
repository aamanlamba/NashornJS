package org.aamanlamba.NashornJS;

import org.apache.commons.csv.CSVRecord;

/**
 * @author aamanlamba
 * Example of record class - could be used to load a CSV record with different columns
 */
public class Record {
	 int id;
	String city;
	Long pop;
	
	public Record(CSVRecord rec) {
		id = Integer.parseInt(rec.get(0));
		city = rec.get(1);
		pop = Long.parseLong(rec.get(2));
	}
}
