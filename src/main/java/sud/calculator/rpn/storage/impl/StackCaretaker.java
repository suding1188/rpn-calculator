package sud.calculator.rpn.storage.impl;

import java.util.Stack;

import org.springframework.stereotype.Component;

import sud.calculator.rpn.storage.Caretaker;
import sud.calculator.rpn.storage.MementoIF;

/**
 * Implement with black box Memento pattern
 * @author Shengxiaoqi
 *
 */
@Component
public class StackCaretaker implements Caretaker {
	
	private Stack<MementoIF> mementos = new Stack<MementoIF>();
	
	@Override
	public void saveMemento(MementoIF memento) {
		if(memento != null) {
			mementos.push(memento);
		}
	}
	
	@Override
	public MementoIF popMemento() {
		if(mementos.size() > 1) {
			mementos.pop();
			return mementos.peek();
		} else {
			return null;
		}
		
	}

}
