package kr.or.ddit.config.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateStringConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date to = null;
		try {
			to = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return to;
	}
}
