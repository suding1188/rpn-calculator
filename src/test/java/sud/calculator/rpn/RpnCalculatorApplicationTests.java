package sud.calculator.rpn;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import sud.calculator.rpn.calculator.impl.RpnCalculator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RpnCalculatorApplicationTests {
	
	@Autowired
	private RpnCalculator calculator;
	
	@MockBean
	private RpnCalculatorApplication application;

	@Test
	public void test() {
		
		calculator.initCaretaker();
		
		assertEquals(calculator.interpret(new String[] {"1", "2", "3", "*", "5", "+", "*", "*", "6", "5"}), "stack: 11 ");
		assertEquals(calculator.interpret(new String[] {"clear"}), "stack: ");
		assertEquals(calculator.interpret(new String[] {"4", "5", "+"}), "stack: 9 ");
		assertEquals(calculator.interpret(new String[] {"clear"}), "stack: ");
		assertEquals(calculator.interpret(new String[] {"4", "5", "-"}), "stack: -1 ");
		assertEquals(calculator.interpret(new String[] {"clear"}), "stack: ");
		assertEquals(calculator.interpret(new String[] {"undo"}), "stack: -1 ");
		assertEquals(calculator.interpret(new String[] {"4", "5", "*"}), "stack: -1 20 ");
		assertEquals(calculator.interpret(new String[] {"undo"}), "stack: -1 4 5 ");
		assertEquals(calculator.interpret(new String[] {"sqrt"}), "stack: -1 4 2.2360679775 ");
		assertEquals(calculator.interpret(new String[] {"undo", "undo"}), "stack: -1 4 ");
		
		assertEquals(calculator.interpret(new String[] {"4", "2", "!"}), "stack: -1 4 4 2 ");
		assertEquals(calculator.interpret(new String[] {"+", "0", "/"}), "stack: -1 4 6 0 ");
		assertEquals(calculator.interpret(new String[] {"+", "-", "*" , "/"}), "stack: 2 ");
		assertEquals(calculator.interpret(new String[] {" "}), "stack: 2 ");
	}

}
