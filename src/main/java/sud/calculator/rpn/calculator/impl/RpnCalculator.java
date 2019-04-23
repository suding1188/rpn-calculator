package sud.calculator.rpn.calculator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sud.calculator.rpn.calculate.BinaryCalculateCore;
import sud.calculator.rpn.calculate.UnaryCalculateCore;
import sud.calculator.rpn.calculate.impl.RpnCalculateCore;
import sud.calculator.rpn.calculator.Calculator;
import sud.calculator.rpn.utils.CommonUtils;
import sud.calculator.rpn.input.InputCollector;
import sud.calculator.rpn.output.Printer;
import sud.calculator.rpn.storage.Caretaker;
import sud.calculator.rpn.storage.Storage;

@Component
public class RpnCalculator implements Calculator {
	
	private static Logger log = LoggerFactory.getLogger(RpnCalculator.class);
	
	@Autowired
	private Storage storage;
	
	@Autowired
	private Caretaker caretaker;
	
	@Autowired
	private InputCollector inputCollector;
	
	@Autowired
	private Printer printer;
	
	@Autowired
	private RpnCalculateCore calculateCore;
	
	@Override
	public void startup() {
		initCaretaker();
		while(true) {
			input();
		}
	}
	
	public void initCaretaker() {
		caretaker.saveMemento(storage.createMemento());
	}
	
	@Override
	public void input() {
		String[] inputs = inputCollector.collector();
		interpret(inputs);
	}
	
	@Override
	public String interpret(String[] inputs) {
		if(inputs != null) {
			int index = 0;
			for (String input : inputs) {
				if(CommonUtils.isNumeric(input)) {
					store(Double.valueOf(input));
				} else if(UNDO.equals(input)) {
					undo();
				} else if(CLEAR.equals(input)) {
					clear();
				} else {
					int numberCount = 0;
					if(UnaryCalculateCore.OPERATORS.contains(input)) {
						numberCount = 1;
					} else if(BinaryCalculateCore.OPERATORS.contains(input)) {
						numberCount = 2;
					} else {
						printer.printIllegalOperator(input);
						break;
					}
					if(numberCount > storage.getSize()) {
						printer.printInsufficientParameters(input, index + 1);
						break;
					} else {
						Double result = calculate(input);
						if(result == null || result.isInfinite() || result.isNaN()) {
							break;
						}
					}
				}
				index += input.length() + 1;
			}
		}
		return printer.printStorage(storage);
	}
	
	@Override
	public void store(Double number) {
		if(storage.store(number)) {
			caretaker.saveMemento(storage.createMemento());
		}
	}
	
	@Override
	public Double calculate(String operator) {
		Double result = null;
		try {
			Double[] numbers = null;
			switch (operator) {
				case BinaryCalculateCore.ADD:
					numbers = storage.pop(2);
					result = calculateCore.add(numbers[1], numbers[0]);
					break;
				case BinaryCalculateCore.SUBTRACT:
					numbers = storage.pop(2);
					result = calculateCore.subtract(numbers[1], numbers[0]);
					break;
				case BinaryCalculateCore.MULTIPLY:
					numbers = storage.pop(2);
					result = calculateCore.multiply(numbers[1], numbers[0]);
					break;
				case BinaryCalculateCore.DIVIDE:
					numbers = storage.pop(2);
					if(numbers[0] != null && numbers[0] == 0) {
						storage.store(numbers[1]);
						storage.store(numbers[0]);
						printer.printIllegalDividend();
						break;
					} 
					result = calculateCore.divide(numbers[1], numbers[0]);
					break;
				case UnaryCalculateCore.SQRT:
					numbers = storage.pop(1);
					result = calculateCore.sqrt(numbers[0]);
					break;
			}
			if(storage.store(result)) {
				caretaker.saveMemento(storage.createMemento());
			}
		} catch (Exception e) {
			result = null;
			log.error("Exception in calculate", e);
		}
		return result;
	}
	
	@Override
	public void undo() {
		storage.restoreMemento(caretaker.popMemento());
	}

	@Override
	public void clear() {
		storage.clear();
		caretaker.saveMemento(storage.createMemento());
	}

}
