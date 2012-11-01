package com.lordofthejars.bool;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.CoreMatchers.anything;
import static com.lordofthejars.bool.Bool.be;
import static com.lordofthejars.bool.Bool.the;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lordofthejars.bool.function.matcher.BeMatcher;
public class WhenBoolIsUsedInConditions {


	@Test
	public void simple_conditionals_should_be_evaluated() {
		
		String name = "Alex";
		assertThat(the(name, is(equalTo("Alex"))), is(true));
		
	}
	
	@Test
	public void collection_conditions_should_be_evaluated() {
		
		List<String> elements = Arrays.asList("Soto", "Alex");
		assertThat(the(elements, containsInAnyOrder("Alex", "Soto")),is(true));
	}

	@Test
	public void collection_with_integers_should_be_evaluated() {
		List ages = Arrays.asList(21, 25,30);
	    assertThat(the(ages, everyItem(greaterThan(18))), is(true));
	}
	
	@Test
	public void and_matcher_execution_should_be_true_if_all_elements_are_true() {
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).and(be(startsWith("A")))), is(true));
	}
	
	@Test
	public void and_matcher_execution_should_be_false_if_one_element_is_false() {
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).and(be(startsWith("B")))), is(false));
	}
	
	@Test
	public void and_matcher_execution_should_be_false_if_all_elements_are_false() {
		String name = "Alex";
		assertThat(the(name, be(equalTo("Soto")).and(be(startsWith("B")))), is(false));
	}
	
	@Test
	public void or_matcher_execution_should_be_true_if_all_elements_are_true() {
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).or(be(startsWith("A")))), is(true));
	}
	
	@Test
	public void or_matcher_execution_should_be_true_if_one_element_is_true() {
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).or(be(startsWith("B")))), is(true));
	}
	
	@Test
	public void or_matcher_execution_should_be_false_if_all_elements_are_false() {
		String name = "Alex";
		assertThat(the(name, be(equalTo("Soto")).or(be(startsWith("B")))), is(false));
	}
	
	@Test
	public void concat_of_and_and_or_should_be_evaluated() {
		
		String name = "Alex";
		assertThat(the(name, be(equalTo("Soto")).and(be(startsWith("B"))).or(be(anything()))), is(true));
	}
	
	@Test
	public void concat_of_and_and_or_should_be_evaluated_with_some_fails() {
		
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).and(be(startsWith("B"))).or(be(anything()))), is(true));
	}

	@Test
	public void concat_of_and_and_or_should_be_evaluated_with_or_fails() {
		
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).and(be(startsWith("B"))).or(be(startsWith("C")))), is(false));
	}
	
	@Test
	public void concat_of_and_and_or_should_be_evaluated_with_or_fails_and_true() {
		
		String name = "Alex";
		assertThat(the(name, be(equalTo("Alex")).and(be(startsWith("A"))).or(be(startsWith("C")))), is(true));
	}
	
	@Test
	public void multiple_vars_can_be_used_with_and_and_should_be_true_if_all_are_true() {
		
		String name = "Alex";
		String surname = "Soto";
		
		assertThat(the(name, be(equalTo("Alex")).and(the(surname, equalTo("Soto")))), is(true));
		
	}
	
	@Test
	public void multiple_vars_can_be_used_with_and_and_should_be_false_if_one_is_false() {
		
		String name = "Alex";
		String surname = "Soto";
		
		assertThat(the(name, be(equalTo("Alex")).and(the(surname, equalTo("Alex")))), is(false));
		
	}
	
	@Test
	public void multiple_vars_can_be_used_with_or_and_should_be_true_if_one_is_true() {
		
		String name = "Alex";
		String surname = "Soto";
		
		assertThat(the(name, be(equalTo("Soto")).or(the(surname, equalTo("Soto")))), is(true));
		
	}
	
	@Test
	public void multiple_vars_can_be_used_with_or_and_should_be_false_if_all_are_false() {
		
		String name = "Alex";
		String surname = "Soto";
		
		assertThat(the(name, be(equalTo("Soto")).or(the(surname, equalTo("Alex")))), is(false));
		
	}
	
	@Test
	public void is_keyword_should_return_an_BeMatcher_instance() {
		BeMatcher<Object> isMatcher = Bool.is(equalTo("Alex"));
		assertThat(isMatcher, is(instanceOf(BeMatcher.class)));
	}
	
	@Test
	public void shouldBe_keyword_should_return_an_BeMatcher_instance() {
		BeMatcher<Object> isMatcher = Bool.shouldBe(equalTo("Alex"));
		assertThat(isMatcher, is(instanceOf(BeMatcher.class)));
	}
	
	@Test
	public void having_lambdaj_method_should_get_attribute_of_a_class() {
		
		MyBean bean = new MyBean("alex");
		
		assertThat(the(bean, having(on(MyBean.class).getName(), be(equalTo("alex")))), is(true));
	}

	
	private class MyBean {
		
		private String name;
		
		public MyBean(String name) {
			this.name = name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
	}
	
}
