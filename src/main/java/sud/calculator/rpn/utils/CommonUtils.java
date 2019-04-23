package sud.calculator.rpn.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	
	private static Logger log = LoggerFactory.getLogger(CommonUtils.class);
	
	public static boolean isNumeric(String number) {
		boolean isNumeric = false;
		try {
			if(number != null) {
				Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
				isNumeric = pattern.matcher(number).matches();
			}
		} catch (Exception e) {
			isNumeric = false;
			log.error("Exception in isNumeric", e);
		}
		return isNumeric;
	}
	
	public static Double round(Double value, int scale) {
		Double round = null;
		try {
			String str = String.valueOf(value);
			BigDecimal bd = new BigDecimal(str);
			bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
			round = bd.doubleValue();
		} catch (Exception e) {
			log.error("Exception in round", e);
		}
		return round;
	}

}
