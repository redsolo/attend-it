package com.squeed.attendit.rest.mapper;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	
	public static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	private static final SimpleDateFormat iso8601Parse = new SimpleDateFormat(
			DateAdapter.ISO8601_PATTERN);
	
	public static synchronized String format(Date d) {
		return iso8601Parse.format(d);
	}
	
	public static synchronized Date parse(String s) throws ParseException {
		return iso8601Parse.parse(s);
	}
	
	@Override
	public String marshal(Date d) throws Exception {
		return format(d);
	}

	@Override
	public Date unmarshal(String s) throws Exception {
		return parse(s);
	}

}

