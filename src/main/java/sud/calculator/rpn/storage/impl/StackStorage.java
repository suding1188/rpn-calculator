package sud.calculator.rpn.storage.impl;

import java.util.Stack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import sud.calculator.rpn.utils.CommonUtils;
import sud.calculator.rpn.storage.MementoIF;
import sud.calculator.rpn.storage.Storage;

@Component
public class StackStorage implements Storage {
	
	private Stack<Double> stack = new Stack<Double>();
	
	public static final String POINT = ".";

	public static final String SPACE = " ";
	
	public static final String EMPTY = "";
	
	public static final String STACK = "stack: ";
	
	public static final String REGEX1 = "0+?$";
	
	public static final String REGEX2 = "[.]$";
	
	@Value("${RPN.NUMBER.DECIMAL.STORAGE}")
	private int storageDecimal;
	
	@Value("${RPN.NUMBER.DECIMAL.DISPLAY}")
	private int displayDecimal;
	
	@Override
	public boolean store(Double number) {
		if(number == null || number.isInfinite() || number.isNaN()) {
			return false;
		} else {
			stack.push(CommonUtils.round(number, storageDecimal));
			return true;
		}
	}
	
	@Override
	public Double[] pop(int count) {
		Double numbers[] = new Double[count];
		for (int i = 0; i < count; i++) {
			numbers[i] = stack.pop();
		}
		return numbers;
	}
	
	@Override
	public void clear() {
		stack.clear();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public MementoIF createMemento() {
		return new StackMemento((Stack<Double>)this.stack.clone());
	}
	
	@Override
	public void restoreMemento(MementoIF memento) {
		if(memento != null) {
			this.stack = ((StackMemento)memento).getStack();
		}
	}

	private class StackMemento implements MementoIF {
		
		private Stack<Double> stack = new Stack<Double>();
		
		StackMemento(Stack<Double> stack) {
			this.stack = stack;
		}

		Stack<Double> getStack() {
			return stack;
		}

	}
	
	@Override
	public int getSize() {
		return stack.size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(STACK);
		stack.stream().forEach(number -> {
			String str = String.valueOf(CommonUtils.round(number, displayDecimal));
			if(str.indexOf(POINT) > 0 ){
				str = str.replaceAll(REGEX1, EMPTY);
				str = str.replaceAll(REGEX2, EMPTY);
			}
			sb.append(str).append(SPACE);
		});
		return sb.toString();
	}

}
