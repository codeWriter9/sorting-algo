package org.ghosh.sanjay.algos;

import static java.lang.invoke.MethodHandles.lookup;
import static java.time.Instant.now;
import static java.util.Objects.hash;
import static org.ghosh.sanjay.algos.DateUtils.betweenInMillis;
import static org.ghosh.sanjay.algos.XCollections.hashMap;
import static org.ghosh.sanjay.algos.XCollections.initialize;
import static org.ghosh.sanjay.algos.XCollections.mapPrintConsumer;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class HashMapTest  {
	
	private static final Logger LOGGER = getLogger(lookup().lookupClass());
	
	/**
	 * 
	 * Returns a Map of the given list of Strings to their lengths
	 * 
	 * @param strings
	 * @return
	 */	
	public static Map<String, Integer> lengthMapping(String... strings) {
		return initialize(hashMap(), (k) -> k == null ? 0 : ((String) k).length(), Arrays.asList(strings));
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public static String[] names() {
		return new String[] { "Alpha", "Beta", "Gamma", "Delta", "Si", "Phi", "Pi", "Epsilon" };
	}

	/**
	 * 
	 * Generic Domain Class to test out
	 * 
	 * @author Sanjay Ghosh
	 *
	 */
	private static class Employee {
		private String name;
		private Double salary;
		private Integer ageInYears;

		/**
		 * 
		 * 
		 * 
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * 
		 * 
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 
		 * 
		 * 
		 * @return
		 */
		public Double getSalary() {
			return salary;
		}

		/**
		 * 
		 * 
		 * @param salary
		 */
		public void setSalary(Double salary) {
			this.salary = salary;
		}

		/**
		 * 
		 * 
		 * 
		 * 
		 * @return
		 */
		public Integer getAgeInYears() {
			return ageInYears;
		}

		/**
		 * 
		 * 
		 * 
		 * @param ageInYears
		 */
		public void setAgeInYears(Integer ageInYears) {
			this.ageInYears = ageInYears;
		}
	}

	/**
	 * 
	 * Sub classed to add the equals and the hash code method
	 * 
	 * @author Sanjay Ghosh
	 *
	 */
	private static class UniqueEmployee extends Employee {

		/**
		 * 
		 * 
		 */
		@Override
		public int hashCode() {
			return hash(getSalary(), getName(), getAgeInYears());
		}

		/**
		 * 
		 * 
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Employee) {
				Employee otherEmp = (Employee) obj;
				return this.getAgeInYears().equals(otherEmp.getAgeInYears())
						&& this.getName().equals(otherEmp.getName()) && this.getSalary().equals(otherEmp.getSalary());
			}
			return false;
		}

		/**
		 * 
		 * 
		 */
		@Override
		public String toString() {
			return "[" + getName() + ":" + getSalary() + ":" + getAgeInYears() + "]";
		}
	}

	/**
	 * 
	 * Sub classed again to add compareTo method
	 * 
	 * 
	 * @author Sanjay Ghosh
	 *
	 */
	private static class ComparableEmployee extends UniqueEmployee implements Comparable<Employee> {

		/**
		 * 
		 * 
		 * 
		 */
		@Override
		public int compareTo(Employee o) {
			if (!this.equals(o)) { // first if not same
				if (!this.getAgeInYears().equals(o.getAgeInYears()) && !this.getSalary().equals(o.getSalary())) {
					return this.getSalary().compareTo(o.getSalary());
				} else if (this.getSalary().equals(o.getSalary()))
					return this.getAgeInYears().compareTo(o.getAgeInYears());
			}
			return 0;
		}
	}

	/**
	 * 
	 * Static method to return an employee
	 * 
	 * @param name
	 * @param salary
	 * @param ageInYears
	 * @return
	 */
	public static Employee employee(String name, Double salary, Integer ageInYears) {
		HashMapTest.Employee e1 = new Employee();
		e1.setSalary(salary);
		e1.setName(name);
		e1.setAgeInYears(ageInYears);
		return e1;
	}

	/**
	 * 
	 * Static method to return an unique employee
	 * 
	 * @param name
	 * @param salary
	 * @param ageInYears
	 * @return
	 */
	private static Employee uniqueEmployee(String name, Double salary, Integer ageInYears) {
		HashMapTest.Employee e1 = new UniqueEmployee();
		e1.setSalary(salary);
		e1.setName(name);
		e1.setAgeInYears(ageInYears);
		return e1;
	}

	/**
	 * 
	 * Static method to return a comparable employee
	 * 
	 * @param name
	 * @param salary
	 * @param ageInYears
	 * @return
	 */
	private static Employee comparableEmployee(String name, Double salary, Integer ageInYears) {
		HashMapTest.Employee e1 = new ComparableEmployee();
		e1.setSalary(salary);
		e1.setName(name);
		e1.setAgeInYears(ageInYears);
		return e1;
	}

	/**
	 * 
	 * Creates and Returns a Salary Incrementer which will give 10% hike unless the
	 * Salary has breached the 1M
	 * 
	 * @param employee
	 * @return Function<Employee, Employee>
	 */
	private static BiFunction<Employee, Employee, Employee> salaryIncrementer(final Employee employee) {
		return new BiFunction<Employee, Employee, Employee>() {
			@Override
			public Employee apply(Employee t, Employee u) {
				t.setSalary(t.getSalary() * 1.10 > 10000000 ? t.getSalary() : t.getSalary() * 1.10);
				return t;
			}
		};
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testHashMap() {
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new HashMap<>();
		employees.put(employee("Sanjay", 100.00, 10), HashMapTest.employee("Sanjay", 100.00, 10));
		employees.put(employee("Sanjay", 100.00, 10), HashMapTest.employee("Sanjay", 100.00, 10));
		assertEquals(false, employees.size() == 1);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testTreeMap() {
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new TreeMap<>();
		try {
			employees.put(employee("Sanjay", 100.00, 10), HashMapTest.employee("Sanjay", 100.00, 10));
			employees.put(employee("Sanjay", 100.00, 10), HashMapTest.employee("Sanjay", 100.00, 10));
		} catch (ClassCastException e) {
			assertEquals(true, e != null);
		}
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testHashMap2() {
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new HashMap<>();
		employees.put(uniqueEmployee("Sanjay", 100.00, 10), HashMapTest.uniqueEmployee("Sanjay", 100.00, 10));
		employees.put(uniqueEmployee("Sanjay", 100.00, 10), HashMapTest.uniqueEmployee("Sanjay", 100.00, 10));
		assertEquals(true, employees.size() == 1);

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testTreeMap2() {
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new TreeMap<>();
		try {
			employees.put(uniqueEmployee("Sanjay", 100.00, 10), uniqueEmployee("Sanjay", 100.00, 10));
			employees.put(uniqueEmployee("Sanjay", 100.00, 10), uniqueEmployee("Sanjay", 100.00, 10));
		} catch (ClassCastException e) {
			assertEquals(true, e != null);
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testHashMap3() {
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new HashMap<>();
		employees.put(comparableEmployee("Sanjay", 100.00, 10), comparableEmployee("Sanjay", 100.00, 10));
		employees.put(comparableEmployee("Sanjay", 100.00, 10), comparableEmployee("Sanjay", 100.00, 10));
		assertEquals(true, employees.size() == 1);

	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testTreeMap3() {
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new TreeMap<>();
		employees.put(comparableEmployee("Sanjay", 100.00, 10), comparableEmployee("Sanjay", 100.00, 10));
		employees.put(comparableEmployee("Sanjay", 100.00, 10), comparableEmployee("Sanjay", 100.00, 10));
		assertEquals(true, employees.size() == 1);
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testHashMap4() {
		LOGGER.debug("-------------Using Hash Map--------------");
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new HashMap<>();
		employees.put(comparableEmployee("Sanjay", 100.00, 10), comparableEmployee("Sanjay", 100.00, 10));
		employees.put(comparableEmployee("Hrishikesh", 200.00, 10), comparableEmployee("Hrishikesh", 200.00, 10));
		LOGGER.debug("-------------Before Compute--------------");
		employees.forEach(mapPrintConsumer());
		employees.compute(comparableEmployee("Sanjay", 100.00, 10),
				salaryIncrementer(comparableEmployee("Sanjay", 100.00, 10)));
		LOGGER.debug("-------------After Compute--------------");
		employees.forEach(mapPrintConsumer());
		LOGGER.debug("----------------------------------------");

	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testTreeMap4() {
		LOGGER.debug("-------------Using Tree Map--------------");
		Map<HashMapTest.Employee, HashMapTest.Employee> employees = new TreeMap<>();
		employees.put(comparableEmployee("Sanjay", 100.00, 10), comparableEmployee("Sanjay", 100.00, 10));
		employees.put(comparableEmployee("Hrishikesh", 200.00, 10), comparableEmployee("Hrishikesh", 200.00, 10));
		LOGGER.debug("-------------Before Compute--------------");
		employees.forEach(mapPrintConsumer());
		employees.compute(comparableEmployee("Sanjay", 100.00, 10),
				salaryIncrementer(comparableEmployee("Sanjay", 100.00, 10)));
		LOGGER.debug("-------------After Compute--------------");
		employees.forEach(mapPrintConsumer());
		LOGGER.debug("----------------------------------------");
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testLoadHashMap() {
		Instant start = now();
		LOGGER.debug("-------------Load Hash Map--------------");
		Map<String, Integer> numbers = new HashMap<>(16, 0.75f);
		LOGGER.debug("Inital Capacity : 16\t Load Factor : 0.75f");
		for (Integer loop = 0; loop < 1000000; loop++)
			numbers.put(loop.toString(), loop);
		LOGGER.debug(" Size: " + numbers.size());
		Instant end = now();
		LOGGER.debug(" Time Taken: " + betweenInMillis(start, end) + " ms ");
		numbers = null;
		LOGGER.debug("----------------------------------------");
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testLoadHashMap2() {
		Instant start = now();
		LOGGER.debug("-------------Load Hash Map--------------");
		LOGGER.debug("Inital Capacity : 1000\t Load Factor : 0.75f");
		Map<String, Integer> numbers = new HashMap<>(1000, 0.75f);
		for (Integer loop = 0; loop < 1000000; loop++)
			numbers.put(loop.toString(), loop);
		LOGGER.debug(" Size: " + numbers.size());
		Instant end = now();
		LOGGER.debug(" Time Taken: " + betweenInMillis(start, end) + " ms ");
		numbers = null;
		LOGGER.debug("----------------------------------------");
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testLoadHashMap3() {
		Instant start = now();
		LOGGER.debug("-------------Load Hash Map--------------");
		LOGGER.debug("Inital Capacity : 100000\t Load Factor : 0.75f");
		Map<String, Integer> numbers = new HashMap<>(100000, 0.75f);
		for (Integer loop = 0; loop < 1000000; loop++)
			numbers.put(loop.toString(), loop);
		LOGGER.debug(" Size: " + numbers.size());
		Instant end = now();
		LOGGER.debug(" Time Taken: " + betweenInMillis(start, end) + " ms ");
		numbers = null;
		LOGGER.debug("----------------------------------------");
	}

	/**
	 * 
	 * 
	 * 
	 */
	@Test
	public void testMap1() {
		Map<String, Integer> map = lengthMapping(names());
		List<Map.Entry<String, Integer>> listOfEntries = new LinkedList<>(map.entrySet());
		listOfEntries.sort(Map.Entry.comparingByValue());
		map = new LinkedHashMap<String, Integer>();
		Map<String, Integer> otherMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : listOfEntries)
			map.put(entry.getKey(), entry.getValue());
		listOfEntries.sort(Map.Entry.comparingByKey());
		for (Map.Entry<String, Integer> entry : listOfEntries)
			otherMap.put(entry.getKey(), entry.getValue());

		LOGGER.debug("--------------------");
		LOGGER.debug(map.toString());
		LOGGER.debug("--------------------");

		LOGGER.debug("--------------------");
		LOGGER.debug(otherMap.toString());
		LOGGER.debug("--------------------");
	}
}