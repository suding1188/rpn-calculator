package sud.calculator.rpn.output.impl;

import org.springframework.stereotype.Component;

import sud.calculator.rpn.output.Printer;
import sud.calculator.rpn.storage.Storage;

@Component
public class RpnPrinter implements Printer {
	
	public static final String INSUFFICIENT_PARAMENTS = "operator %s (position: %s): insufficient parameters";
	
	public static final String ILLEGAL_OPERATOR = "operator %s : illegal operator";
	
	public static final String ILLEGAL_DIVIDEND = "operator / : illegal dividend 0";

	@Override
	public void printInsufficientParameters(String operator, int position) {
		System.out.println(String.format(INSUFFICIENT_PARAMENTS, operator, position));
	}
	
	@Override
	public void printIllegalOperator(String illegalOperator) {
		System.out.println(String.format(ILLEGAL_OPERATOR, illegalOperator));
	}

	@Override
	public String printStorage(Storage storage) {
		String print = "";
		if(storage != null) {
			print = storage.toString();
			System.out.println(print);
		}
		return print;
	}

	@Override
	public void printIllegalDividend() {
		System.out.println(ILLEGAL_DIVIDEND);
	}

}
