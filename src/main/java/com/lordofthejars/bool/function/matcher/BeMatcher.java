package com.lordofthejars.bool.function.matcher;

import org.hamcrest.Matcher;

public class BeMatcher<T> extends BooleanOperatorsMatcher<T> {

	private final Matcher<?> matcher;
	
	public BeMatcher(Matcher<?> matcher) {
		this.matcher = matcher;
	}
	
	public boolean matches(Object object) {
		return this.matcher.matches(object);
	}

}
