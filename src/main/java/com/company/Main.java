package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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


    public static String checkFirst(List<String> list) {
        return list.stream()
                .findFirst()
                .orElse("0");
    }

    public static void main(String[] args) {
        List<String> list = List.of("a1", "a2", "a3", "a1");

        long count = list.stream()
                .filter("a1"::equals)
                .count();

        System.out.println("Количество вхождений объекта «a1»: " + count);

        System.out.println("Первый элемент коллекции или 0, если коллекция пуста: " + checkFirst(list));

        String thirdElement = list.stream()
                .skip(2)
                .findFirst()
                .get();
        System.out.print("Третий элемент коллекции по порядку: " + thirdElement);


        String[] twoElements = list.stream()
                .skip(1)
                .limit(2)
                .toArray(String[]::new);

        System.out.println("Два элемента начиная со второго : " + twoElements);


        System.out.println("---------------------------");

        List<People> peoples = Arrays.asList(new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN),
                new People("Иван Иванович", 69, Sex.MAN));


        List<People> armyMen = peoples.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .collect(Collectors.toList());

        System.out.println("Военнообязанные мужчины: ");
        for (People men : armyMen) {
            System.out.println(men);
        }

        double averageMenAge = peoples.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .map(People::getAge)
                .mapToInt(x -> x)
                .average()
                .orElse(0.0);

        System.out.println("\nСредний возраст среди мужчин: " + averageMenAge);


        System.out.println("---------------------------");

        List<String> numbersList = Arrays.asList("1,2,0", "4,5");


        System.out.println("Все числа, перечисленные через запятую из всех элементов: ");

        numbersList.stream()
                .map(x -> x.split(","))
                .flatMap(Arrays::stream)
                .forEachOrdered(System.out::println);


        System.out.println("---------------------------");

        List<String> lettersList = Arrays.asList("a1", "b2", "c3", "a1");


        System.out.println("Группировка по первому символу строки : ");

        Map<Character, List<String>> grouped = lettersList.stream()
                .collect(Collectors.groupingBy(x -> x.charAt(0)));

        System.out.println(grouped);

    }
}

