package com.acs.adventofcode.puzzle7;

public class Equation {
	public enum Type {
		NOT,
		AND,
		OR,
		LSHIFT,
		RSHIFT,
		VALUE
	}
	
	private Type type;
	private String value;
	private String input1;
	private String input2;
	
	public void setType(Type value) {
		type = value;
	}

	public void setValue(String inputValue) {
		value = inputValue;
	}
	
	@Override
	public String toString() {
		switch (type) {
		case VALUE:
			return value;
		case AND:
			return input1 + " AND " + input2;

		default:
			break;
		}
		return "";
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}
	public void setInput2(String input1) {
		this.input2 = input1;
	}
}
