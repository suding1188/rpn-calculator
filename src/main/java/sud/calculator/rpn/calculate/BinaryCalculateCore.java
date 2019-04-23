package sud.calculator.rpn.calculate;

import java.util.Arrays;
import java.util.List;

public interface BinaryCalculateCore {
	
	static final String ADD = "+";
	static final String SUBTRACT = "-";
	static final String MULTIPLY = "*";
	static final String DIVIDE = "/";
	
	static final List<String> OPERATORS = Arrays.asList(ADD, SUBTRACT, MULTIPLY, DIVIDE);
	
	Double add(Double number1, Double number2);
	
	Double subtract(Double number1, Double number2);
	
	Double multiply(Double number1, Double number2);
	
	Double divide(Double number1, Double number2);
	
}
