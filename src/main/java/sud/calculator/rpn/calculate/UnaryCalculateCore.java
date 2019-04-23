package sud.calculator.rpn.calculate;

import java.util.Arrays;
import java.util.List;

public interface UnaryCalculateCore {
	
	static final String SQRT = "sqrt";
	
	static final List<String> OPERATORS = Arrays.asList(SQRT);
	
	Double sqrt(Double number);

}
