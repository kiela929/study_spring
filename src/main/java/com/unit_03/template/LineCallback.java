package com.unit_03.template;

public interface LineCallback<T> {
	Integer doSomethingWithLine(String line, T value);
}
