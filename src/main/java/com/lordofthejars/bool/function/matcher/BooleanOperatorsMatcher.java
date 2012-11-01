package com.lordofthejars.bool.function.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;


public abstract class BooleanOperatorsMatcher<T> extends BaseMatcher<T> {

	@SuppressWarnings("unchecked")
	public final BooleanOperatorsMatcher<T> and(Matcher<T> matcher) {
		return AndMatcher.and(this, matcher);
	}

	@SuppressWarnings("unchecked")
	public final BooleanOperatorsMatcher<T> and(boolean result) {
		return AndMatcher.and(this, new BooleanMatcher(result));
	}

	@SuppressWarnings("unchecked")
	public final BooleanOperatorsMatcher<T> or(Matcher<T> matcher) {
		return OrMatcher.or(this, matcher);
	}
	
	@SuppressWarnings("unchecked")
	public final BooleanOperatorsMatcher<T> or(boolean result) {
		return OrMatcher.or(this, new BooleanMatcher(result));
	}

	
	public void describeTo(Description description) {
	}

	public static class BooleanMatcher<T> extends BaseMatcher<T> {

		private boolean result;
		
		public BooleanMatcher(boolean result) {
			this.result = result;
		}
		
		public boolean matches(Object item) {
			return result;
		}

		public void describeTo(Description description) {
		}
	}

}
