package sud.calculator.rpn.storage;

public interface Storage {

	boolean store(Double number);
	
	Double[] pop(int count);
	
	void clear();
	
	MementoIF createMemento();
	
	void restoreMemento(MementoIF memento);
	
	int getSize();
	
}
