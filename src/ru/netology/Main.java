package ru.netology;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> firstStream = persons.stream();
        long count = firstStream.filter(w -> w.getAge() >= 18).count();
        System.out.println(count);

        Stream<Person> secondStream = persons.stream();
        List<String> surnamesOfСonscripts = secondStream.filter(w -> w.getAge() >= 18)
                .filter(w -> w.getAge() <= 27)
                .map(Person::getFamily).collect(Collectors.toList());

        Stream<Person> thirdStream = persons.stream();
        List<Person> workers = thirdStream
                .filter(w -> (w.getSex().equals(Sex.MAN) && w.getAge() >= 18 && w.getAge() <= 65)
                        || (w.getSex().equals(Sex.WOMAN) && w.getAge() >= 18 && w.getAge() <= 60))
                .filter(w -> w.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
