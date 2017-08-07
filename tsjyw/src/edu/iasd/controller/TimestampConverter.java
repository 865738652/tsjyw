package edu.iasd.controller;

import java.sql.Timestamp;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class TimestampConverter implements Converter<String, Timestamp>{
	 
    @Override
	public Timestamp convert(String timeStr) {
	    // TODO Auto-generated method stub
	    Timestamp t=null;
	    if(!StringUtils.isEmpty(timeStr)){
	        long time=Long.valueOf(timeStr);
	        t=new Timestamp(time);
	    }
	    return t;
	}
}