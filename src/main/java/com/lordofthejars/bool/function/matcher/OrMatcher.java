package com.lordofthejars.bool.function.matcher;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;


public final class OrMatcher<T> extends BooleanOperatorsMatcher<T> {

	private final Matcher<T>[] matchers;

	private OrMatcher(Matcher<T>... matchers) {
		this.matchers = matchers;
	}
	
    /**
     * {@inheritDoc}
     */
	public boolean matches(Object item) {
		for (Matcher<T> matcher : matchers) { if (matcher.matches(item)) return true; }
		return false;
	}

    @Factory
    public static <T> OrMatcher<T> or(Matcher<T>... matchers) {
    	return new OrMatcher<T>(matchers);
    }

}
