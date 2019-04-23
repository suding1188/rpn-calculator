package sud.calculator.rpn.calculator;

public interface Calculator {

	static final String UNDO = "undo";
	static final String CLEAR = "clear";

	void startup();

	void input();

	String interpret(String[] inputs);

	void store(Double number);

	Double calculate(String operator);

	void undo();

	void clear();
	
}
