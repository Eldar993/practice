package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    enum Sex {
        MAN, WOMEN;
    }


    static class People {
        private String name;
        private int age;
        private Sex sex;

        People(String name, int age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> list = List.of("a1", "a2", "a3", "a1");

        int count = (int) list.stream()
                .filter(e -> e.equals("a1"))
                .count();


        String firstString = String.valueOf(list.stream().findFirst());


        Optional<String> thirdElement = list.stream()
                .filter(x -> list.indexOf(x) == 2).findAny();

        List<String> twoElements = list.stream()
                .filter(x -> list.subList(1, 3)
                        .contains(x)).collect(Collectors.toList());

        System.out.println("Количество вхождений объекта «a1» : " + count);
        System.out.println("Первый элемент коллекции или 0, если коллекция пуста : " + firstString);
        System.out.println("Третий элемент коллекции по порядку : " + thirdElement);
        System.out.println("Два элемента начиная со второго : " + twoElements);


        System.out.println("---------------------------");

        List<People> peoples = Arrays.asList(new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN),
                new People("Иван Иванович", 69, Sex.MAN));


        List<People> armyMen = peoples.stream()
                .filter(x -> (x.getAge() >= 18) && (x.getAge() <= 27))
                .collect(Collectors.toList());

        System.out.println("Военнообязанные мужчины: ");
        for (People men : armyMen) {
            System.out.println(men);
        }

        int[] averageMenAge = peoples.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .map(People::getAge)
                .mapToInt(x -> x)
                .toArray();

        System.out.println("\nCредний возраст среди мужчин:");
        System.out.println(Arrays.stream(averageMenAge).average());
    }
    
}




