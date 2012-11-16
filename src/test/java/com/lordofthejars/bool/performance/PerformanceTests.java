package com.lordofthejars.bool.performance;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static com.lordofthejars.bool.Bool.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;

@AxisRange(min = 0, max = 1)
@BenchmarkMethodChart(filePrefix = "benchmark-lists")
public class PerformanceTests {

	private String currentValue;
	private String auxiliaryValue;
	private Random randoms = new Random();

	private static final String[] values = new String[] { "Alex", "Soto",
			"Bueno" };

	private static final List<Integer> ages = Arrays.asList(21, 25, 30);
	
	@Before
	public void before() {
		currentValue = values[randoms.nextInt(3)];
		auxiliaryValue = values[randoms.nextInt(3)];
	}

	@Rule
	public MethodRule benchmarkRun = new BenchmarkRule();

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void bool_simple_comparision() {
		boolean result = the(currentValue, is("Alex"));
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void native_simple_comparision() {
		boolean result = currentValue.equals("Alex");
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void bool_simple_negated_comparision() {
		boolean result = the(currentValue, is(not("Alex")));
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void native_simple_negated_comparision() {
		boolean result = !currentValue.equals("Alex");
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void bool_composed_comparision() {
		boolean result = the(currentValue, be(equalTo("Alex")).or(be(equalTo("Ada"))));
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void native_composed_comparision() {
		boolean result = currentValue.equals("Alex")||currentValue.equals("Ada");
	}
	
	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void bool_multi_values_composed_comparision() {
		boolean result = the(currentValue, be(equalTo("Alex")).and(the(auxiliaryValue, equalTo("Soto"))));
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void native_multi_values_composed_comparision() {
		boolean result = currentValue.equals("Alex")&&auxiliaryValue.equals("Soto");
	}
	
	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void bool_collection_comparision() {
		boolean result = the(ages, is(everyItem(greaterThan(18))));
	}

	@BenchmarkOptions(benchmarkRounds = 50000, warmupRounds = 1)
	@Test
	public void native_collection_comparision() {
		boolean result = areAllPersonsAdult(ages);
	}
	
	private boolean areAllPersonsAdult(List<Integer> ages) {

		for (int age : ages) {
			if (age < 18) {
				return false;
			}
		}

		return true;

	}

}
