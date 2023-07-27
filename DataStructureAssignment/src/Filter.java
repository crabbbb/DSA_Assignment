/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;

public class Filter {

    public static void main(String[] args) {

        Person p1 = new Person(34, "Michael", Gender.MALE);
        Person p2 = new Person(17, "Jane", Gender.FEMALE);
        Person p3 = new Person(28, "John", Gender.MALE);
        Person p4 = new Person(47, "Peter", Gender.MALE);
        Person p5 = new Person(27, "Lucy", Gender.FEMALE);

        Person persons = List.of(p1, p2, p3, p4, p5);

        Predicate<Person> byAge = person -> person.getAge() > 30;

        Person result = persons.stream().filter(byAge)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
