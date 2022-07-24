package 蓝山02;

import java.util.Stack;

import org.junit.Test;

public class Calculator {
	
	public void calculate(String exp) {
		Stack<String> numStack = new Stack<String>();
		Stack<String> opsStack = new Stack<String>();
		if(exp.startsWith("-")) exp = "0" + exp;
		String[] nums = exp.split("[+\\-*/()]");
		try{
			for(int i = 0, j = 0; i < exp.length(); i++) {
				String ch = exp.substring(i, i + 1);
				if(ch.matches("[+\\-*/]")) {
					if(i < exp.length() - 1 && exp.substring(i + 1, i + 2).matches("[+\\-*/]")) 
						throw new CalException("存在相邻的运算符");
					if(opsStack.isEmpty()) {
						opsStack.push(ch);					
					}else if(priority(ch) < priority(opsStack.peek())) {
						String num1 = numStack.pop();
						String num2 = numStack.pop();
						String op = opsStack.pop();
						numStack.push(count(num1, num2, op));
						opsStack.push(ch);
					}else {
						opsStack.push(ch);					
					}
				}else if(ch.equals("(")) {
					opsStack.push(ch);
				}else if(ch.equals(")")) {
					while(!opsStack.isEmpty() && !opsStack.peek().equals("(")) {
						String num1 = numStack.pop();
						String num2 = numStack.pop();
						String op = opsStack.pop();
						numStack.push(count(num1, num2, op));
					}
					if(opsStack.isEmpty()) throw new CalException("表达式有误");
					opsStack.pop();
				}else if(ch.matches("\\d")){
					while(i < exp.length() - 1) {
						if(exp.substring(i + 1, i + 2).matches("[\\d.]")) i++;
						else if(exp.substring(i + 1, i + 2).matches("[+\\-*/()]")) break;
						else throw new CalException("存在无效符号");
					}
					while(nums[j].equals("")) j++;
					if(!opsStack.isEmpty() && opsStack.peek().equals("-")) {
						opsStack.pop();
						if(!(!opsStack.isEmpty() && opsStack.peek().equals("(")))
							opsStack.push("+");
						numStack.push("-" + nums[j++]);
					}
					else  
						numStack.push(nums[j++]);
				}
				else throw new CalException("存在无效符号");
			}
			
			while(!opsStack.isEmpty()) {
				String num1 = numStack.pop();
				String num2 = numStack.pop();
				String op = opsStack.pop();
				numStack.push(count(num1, num2, op));
			}
			System.out.println(Float.parseFloat(numStack.pop()));
		}catch(CalException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int priority(String op) throws CalException {
		if(op.matches("[+\\-]")) return 1;
		else if (op.matches("[*/]")) return 2;
		else if (op.matches("[()]")) return 0;
		else throw new CalException("存在无效符号");
	}
	
	public String count(String num1, String num2, String op) throws CalException {
		float n1 = Float.parseFloat(num1);
		float n2 = Float.parseFloat(num2);
		switch(op.charAt(0)) {
			case '+':
				return n1 + n2 + "";
			case '-':
				return n2 - n1 + "";
			case '*':
				return n1 * n2 + "";
			case '/':
				if(n1 == 0) throw new CalException("不能除以0");
				return n2 / n1 + "";
			default:
				throw new CalException("表达式有误");
		}	
	}
	
	/**
	 * 
	 * @Description 测试方法
	 */
	@Test
	public void test() {
		Calculator cal =  new Calculator();
		cal.calculate("1.1+5.5*6.9-(5.5+7.9*8.2)+1.7/2.4");//-31.938334
		cal.calculate("4++4");//存在相邻的运算符
		cal.calculate("5/0");//不能除以0
		cal.calculate("6>+7");//存在无效符号
		cal.calculate("(7+7))");//表达式有误
		cal.calculate("7(9");//表达式有误
	}
}

class CalException extends Exception {
	
	static final long serialVersionUID = -338751699312422994L;
	
	public CalException() {
		
	}
	
	public CalException(String msg) {
		super(msg);
	}
}

