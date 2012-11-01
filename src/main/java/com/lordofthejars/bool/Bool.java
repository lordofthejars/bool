package com.lordofthejars.bool;


import org.hamcrest.Matcher;

import com.lordofthejars.bool.function.matcher.BeMatcher;

public class Bool {
	
	public static <T> boolean the(T element, Matcher<?> matcher) {
		return matcher.matches(element);
	}

	public static <T> BeMatcher<T> be(Matcher<?> matcher) {
		return new BeMatcher<T>(matcher);
	}

	public static <T> BeMatcher<T> is(Matcher<?> matcher) {
		return be(matcher);
	}

	public static <T> BeMatcher<T> shouldBe(Matcher<?> matcher) {
		return be(matcher);
	}
	
}
