package com.yy.EmployeeManagement.Domain;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * This is used to customize date format comes from sql server.
 * this project will use Jackson to serialize object to Json string.
 * @author yong.yang
 *
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);
	}
}
