package StreamAPI;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsLab {
    public static void main(String[] args) {

        // Question (QID 2.2023)
        System.out.println("\n(QID 2.2023) : ");
        // range is exclusive for the second parameter
        OptionalDouble avg = IntStream.range(0,5).average();
        System.out.println(avg.orElse(0));
        // rangeClosed is inclusive for the second parameter
        OptionalDouble avgClosed = IntStream.rangeClosed(0,5).average();
        System.out.println(avgClosed.orElse(0));

        // Question (QID Q2.1762)
        System.out.println("\n(QID Q2.1762) : ");
        List<Item> items = List.of(new Item(1,"Screw"),new Item(2,"Nail"),new Item(3,"Bolt"));
        items.stream()
                .map(Item::getName)
                .sorted()
                .forEach(System.out::print);

        //(QID 2.1787)
        System.out.println("\n(QID 2.1787) : ");
        Stream.of(Arrays.asList("a", "b"), Arrays.asList("a", "c"),Arrays.asList("d", "c"))
                .filter(e -> e.contains("c"))
                .flatMap(Collection::stream)
                .forEach(System.out::println);
        //(QID 2.1738)
        System.out.println("\n(QID 2.1738) : ");
        // a.
        List<Integer> l = List.of(1,2,3);
        int s = l.stream()
                .mapToInt(n->n)
                .sum();
        OptionalInt max = l.stream()
                .mapToInt(n->n)
                .max();
        System.out.println(s + " max: " + max.orElse(0));
        // b.
        List<Person> personList = List.of(new Person("Alan", "Burke", 22),new Person("Zoe", "Peters", 20),new Person("Peter", "Castle", 29));
        Person oldest = personList.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElseThrow();
        System.out.println(oldest);
        // c.
        List<Integer> list = List.of(10,47,33);
        int maxWithReduce = list.stream()
                .reduce(Integer::max).orElseThrow();
        System.out.println(maxWithReduce);
        maxWithReduce = list.stream()
                .reduce(Integer.MIN_VALUE,Integer::max);
        System.out.println(maxWithReduce);

        //(QID 2.1826)
        System.out.println("\n(QID 2.1826) : ");
        Optional<String> grade1 = getGrade(50);
        System.out.println(grade1.orElse("UNKNOWN"));
        Optional<String> grade2 = getGrade(55);
        if(grade2.isPresent()) grade2.ifPresent(System.out::println);
        else System.out.println(grade1.orElse("Empty"));

        //(QID 2.1809
        System.out.println("\n(QID 2.1809 : ");
        List<Book> books = Arrays.asList(new Book("Thinking in Java", 30.0), new Book("Java in 24 hrs", 20.0),new Book("Java Recipes", 10.0));

        double average = books.stream()
                .filter(b->b.getPrice()>90)
                .mapToDouble(Book::getPrice)
                .average()
                .orElse(0);
        System.out.println(average);

        //(QID 2.1846)
        System.out.println("\n(QID 2.1846) : ");
        books = Arrays.asList(new Book("Atlas Shrugged", 10.0), new Book("Freedom at Midnight", 5.0),new Book("Gone with the wind", 10.0));

        Map<String,Double> bookMap = books.stream().collect(Collectors.toMap(Book::getTitle,Book::getPrice));
        bookMap.forEach((k,v) -> {if(k.startsWith("A")) System.out.println(v);});

        //(QID 2.1847)
        System.out.println("\n(QID 2.1847) : ");
        books = Arrays.asList(new Book("Gone with the wind", 5.0),new Book("Gone with the wind", 10.0),new Book("Atlas Shrugged", 15.0));

        books.stream()
                .collect(Collectors
                        .toMap(Book::getTitle,Book::getPrice,Double::sum))
                .forEach((k,v) -> System.out.println(k+" "+ v));

        //(QID 2.1810)
        System.out.println("\n(QID 2.1810) : ");
        personList = List.of(new Person("Bob",31),new Person("Paul", 32),new Person("John", 33));
        average = personList.stream()
                        .filter(p -> p.getAge()<30)
                                .mapToInt(Person::getAge)
                                        .average().orElse(0);
        System.out.println(average);

        //(QID 2.1849)
        System.out.println("\n(QID 2.1849) : ");
        // i.
        Optional<Double> price = Optional.ofNullable(20.0);
        price.ifPresent(System.out::println);
        System.out.println(price.orElse(0.0));
        System.out.println(price.orElseGet(() ->0.0));
        // ii.
        Optional<Double> price2 = Optional.ofNullable(null);
        System.out.println(price2);
        if (price2.isEmpty()) System.out.println("empty");
        price2.ifPresent(v -> {if (price2.isEmpty()) System.out.println("empty");});
        //iii
        Double x = price2.orElse(44.0);
        System.out.println(x);
        //c
        Optional<Double> price3 = Optional.ofNullable(null);
        /*Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code"));
        System.out.println(z);*/

        //(QID 2.1858)
        System.out.println("\n(QID 2.1858) : ");
        List<AnotherBook> books2 = Arrays.asList(new AnotherBook("Gone with the wind", "Fiction"),new AnotherBook("Bourne Ultimatum","Thriller"),new AnotherBook("The Client","Thriller"));
        List<String> genreList = new ArrayList<>();
        books2.stream()
                .map(AnotherBook::getGenre)
                .forEach(genreList::add);
        System.out.println(genreList);

        //(QID 2.2024)
        System.out.println("\n(QID 2.2024) : ");
        System.out.println(
                DoubleStream.of(0,2,4)
                    .filter(d -> d%2!=0)
                    .sum()
        );
        System.out.println(
                Stream.of(1,3)
                    .mapToDouble( d-> d)
                    .filter(d -> d%2==0)
                    .average()
                    .orElse(0.0)
        );

        //(QID 2.1840)
        System.out.println("\n(QID 2.1840) : ");
        List<Integer> ls = Arrays.asList(11, 11, 22, 33, 33, 55, 66);
        System.out.println(
            ls.stream()
                    .peek(System.out::println)
                    .distinct()
                    .anyMatch(e -> e==11)
        );
        System.out.println("\n(QID 2.1840) None Match : ");
        System.out.println(
                ls.stream()
                        .peek(System.out::println)
                        .noneMatch(e -> e%11>0)
        );

        //(QID 2.1841)
        System.out.println("\n(QID 2.1841) : ");
        // a.
        AtomicInteger ai = new AtomicInteger();
        Stream.of(11,11,22,33)
                .parallel()
                .filter(n -> {
                    ai.incrementAndGet();
                    return n%2==0;
                });
        System.out.println(ai); // print out 0 as filter isn't a terminal operation and the incrementAndGet() method was never effectively called
        // b.
        Stream<Integer> stream = Stream.of(11,11,22,33).parallel();
        Stream<Integer> stream2 = stream.filter(n -> {
                    ai.incrementAndGet();
                    return n%2==0;
                });
        stream2.forEach(System.out::println);
        System.out.println(ai);


    }

    //used in (QID 2.1826)
    public static Optional<String> getGrade(int marks){
        Optional<String> grade = Optional.empty();
        //return grade = marks > 50 ? Optional.of("PASS"): Optional.of("FAIL");
        if (marks > 50) {
            grade = Optional.of("PASS");
        }
        else {
            grade.of("FAIL"); // Optionals are immuatable
        };
        return grade;
    }

}