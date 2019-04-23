package sud.calculator.rpn;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sud.calculator.rpn.calculator.Calculator;
import sud.calculator.rpn.utils.CommonUtils;


/**
 * Reverse Polish Notation Calculator
 * @author suding
 * @version 1.0.0
 *
 */
@SpringBootApplication
public class RpnCalculatorApplication {
	
	private static Logger log = LoggerFactory.getLogger(CommonUtils.class);
	
	@Autowired
	private Calculator calculator;

	public static void main(String[] args) {
		try {
			SpringApplication.run(RpnCalculatorApplication.class, args);
		} catch (Exception e) {
			log.error("Exception in RpnCalculatorApplication", e);
		}
		
	}
	
	@PostConstruct
	public void startup() {
		try {
			System.out.println("============RpnCalculator Started============");
			calculator.startup();
		} catch (Exception e) {
			log.error("Exception in startup", e);
		}
	}

}
