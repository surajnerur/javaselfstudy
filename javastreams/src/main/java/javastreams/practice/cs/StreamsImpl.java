package javastreams.practice.cs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsImpl {

	static List<Person> listPerson = new ArrayList<Person>();

	static {
		listPerson.add(new Person("abcd", 23,1000));
		listPerson.add(new Person("defh", 35,2000));
		listPerson.add(new Person("ijkl", 63,1400));
		listPerson.add(new Person("lmno", 53,1040));
		listPerson.add(new Person("opqr", 53,1001));
		listPerson.add(new Person("rstu", 52,1006));
		listPerson.add(new Person("uvwx", 26,1007));
		listPerson.add(new Person("xyza", 46,1600));
		listPerson.add(new Person("bcde", 75,1700));
		listPerson.add(new Person("fghi", 36,1040));
		listPerson.add(new Person("jklm", 45,2000));
		listPerson.add(new Person("mnop", 35,2030));
		listPerson.add(new Person("qrst", 63,1030));
		listPerson.add(new Person("uvwx", 65,1200));
		listPerson.add(new Person("yzab", 17,3000));
		listPerson.add(new Person("cdef", 75,4090));
		listPerson.add(new Person("ghij", 63,5007));
		listPerson.add(new Person("klmn", 25,1004));
		listPerson.add(new Person("opqr", 37,1009));
		listPerson.add(new Person("stuv", 44,1003));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("-----------PRINT ALL---------------");
		
		listPerson.stream().filter(p -> p.getAge() > 55).forEach(p -> System.out.println(p));
		
		System.out.println("-----------List to MAP ---------------");
		
		Map<String, Integer> map = listPerson.stream().filter(p -> p.getAge() > 55).collect(Collectors.toMap(Person::getName, Person::getAge));
		System.out.println(map);
		
		System.out.println("-----------PRINT using MergeFunction---------------");
		
		Map<Integer, String> map2 = listPerson.stream().filter(p -> p.getAge() > 55)
				.collect(Collectors.toMap(Person::getAge, Person::getName, 
						(oldValue, newValue) -> {
							//return both values seperated by ":"
							return oldValue + ":" + newValue;
						}
				));
		
		System.out.println(map2);
		
		System.out.println("-----------Get Various Map Data Structures ---------------");
		
		TreeMap<Integer, String> collectedMap = listPerson.stream().filter(p -> p.getAge() > 55).collect(Collectors.toMap(Person::getAge, Person::getName, (v1, v2) -> v1 + ":" + v2, TreeMap::new));
		
		System.out.println(collectedMap);
		
		System.out.println("-----------Merge 2 Data Structure---------------");
		
		List<Person> collect = listPerson.stream().filter(p -> p.getAge() > 55).collect(Collectors.toList());
		
		List<Person> list2 = Arrays.asList(
				new Person("Harshal", 32, 3434343), 
				new Person("Suraj", 35, 232343));		
		
		Stream<Person> mergedStream = Stream.concat(list2.stream(), collect.stream());
		
		mergedStream.forEach(System.out::println);
		
		System.out.println("-----------Calculate Total Salary with Conditions ---------------");

		double sumOfAllSalary = listPerson.stream().filter(p -> p.getAge() > 55).mapToDouble(p -> p.getSalary()).sum();
		
		System.out.println(sumOfAllSalary);
		
		System.out.println("-----------Calculate Total Salary with Conditions - Type 2---------------");
		
		//double sumOfAllSalary = listPerson.stream().filter(p -> p.getAge() > 55).reduce
		
		System.out.println("-----------REDUCE---------------");
		listPerson.stream().filter(p -> p.getAge() > 50).mapToDouble(Person::getSalary)
				.reduce((val1, val2) -> val1 > val2 ? val1 : val2).ifPresent(System.out::println);
		System.out.println("-----------REDUCE WITH IDENTITY---------------");
		
		double doubleValue = listPerson.stream().filter(p -> p.getAge() > 55).mapToDouble(Person::getSalary).reduce(23, Double::sum);
		System.out.println(doubleValue);
	}

}
