package com.lordofthejars.bool.function.matcher;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;


public final class AndMatcher<T> extends BooleanOperatorsMatcher<T>{

	private final Matcher<T>[] matchers;

	private AndMatcher(Matcher<T>... matchers) {
		this.matchers = matchers;
	}
	
    /**
     * {@inheritDoc}
     */
	public boolean matches(Object item) {
		for (Matcher<T> matcher : matchers) { if (!matcher.matches(item)) return false; }
		return true;
	}

    @Factory
    public static <T> AndMatcher<T> and(Matcher<T>... matchers) {
    	return new AndMatcher<T>(matchers);
    }
	
}
