package sud.calculator.rpn.calculate.impl;

import org.springframework.stereotype.Component;

import sud.calculator.rpn.calculate.BinaryCalculateCore;
import sud.calculator.rpn.calculate.UnaryCalculateCore;

@Component
public class RpnCalculateCore implements BinaryCalculateCore, UnaryCalculateCore {
	
	@Override
	public Double  add(Double number1, Double number2) {
		if(number1 != null && number2 != null) {
			return number1 + number2;
		}
		return null;
	}


	@Override
	public Double subtract(Double number1, Double number2) {
		if(number1 != null && number2 != null) {
			return number1 - number2;
		}
		return null;
	}

	@Override
	public Double multiply(Double number1, Double number2) {
		if(number1 != null && number2 != null) {
			return number1 * number2;
		}
		return null;
	}


	@Override
	public Double divide(Double number1, Double number2) {
		if(number1 != null && number2 != null && number2 != 0) {
			return number1 / number2;
		}
		return null;
	}

	@Override
	public Double sqrt(Double number) {
		if(number != null) {
			return Math.sqrt(number);
		}
		return null;
	}

}
