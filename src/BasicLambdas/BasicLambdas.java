package BasicLambdas;

import FunctionalInterfaces.Evaluate;
import FunctionalInterfaces.Functionable;
import FunctionalInterfaces.Printable;
import FunctionalInterfaces.Retrievable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BasicLambdas {
    public static void main(String[] args){
        consumer();
        supplier();
        predicate();
        String name = "Mr. Joe Bloggs";
        Predicate<Integer> p = x -> x%2==0;
        Predicate<String> ps = s -> s.startsWith("Mr.");
        Predicate<String> ps2 = name::startsWith;
        check(4,p);
        check(7,p);
        check("Mr. Joe Bloggs",ps);
        check("Ms. Ann Bloggs",ps);
        check("Mr",ps2);
        Person mike = new Person("Mike",33,1.8);
        Person ann = new Person("Ann",13,1.4);
        check(mike,Person::isAdult);
        check(ann,Person::isAdult);
        function();
        var listPeople = getPeople();

        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }

    private static void sortHeight(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getHeigth));
        listPeople.forEach(System.out::println);
    }

    private static void sortName(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getName));
        listPeople.forEach(System.out::println);
    }

    private static void sortAge(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(Person::getAge));
        listPeople.forEach(System.out::println);
    }

    private static List<Person> getPeople() {

        List<Person> result = new ArrayList<>();

        result.add(new Person("Mike", 33, 1.8));

        result.add(new Person("Mary", 25, 1.4));

        result.add(new Person("Alan", 34, 1.7));

        result.add(new Person("Zoe", 30, 1.5));

        return result;

    }

    private static void consumer(){
        // Using FunctionalInterfaces.Printable Interface
        Printable<String> p = s -> System.out.println(s);
        Printable<String> p2 = System.out::println;
        p2.print("Printable lambda");
        p.print("Printable lambda");

        // Using Consumer
        Consumer<String> c = s -> System.out.println(s);
        Consumer<String> c2 = System.out::println;
        c.accept("Printable lambda");
        c2.accept("Printable lambda");
    }

    private static void supplier(){
        // Using FunctionalInterfaces.Retrievable Interface
        Retrievable<Integer> r = () -> 77;
        r.retrieve();

        // Using a Supplier
        Supplier<Integer> s = () -> 77;
        s.get();
    }

    private static void predicate() {
        Evaluate<Integer> e = x -> x<0;
        e.isNegative(-1);
        e.isNegative(1);

        Predicate<Integer> p = x -> x<0;
        p.test(-1);
    }
    private static <T> boolean check(T t, Predicate<T> p){
        return p.test(t);
    }

    private static void function() {
        String string = "Number is: ";
        Functionable<Integer,String> f = n -> string + n;

        Function<Integer,String> f2 = n -> string + n;
        f.m(25);
        f2.apply(25);
    }
}
