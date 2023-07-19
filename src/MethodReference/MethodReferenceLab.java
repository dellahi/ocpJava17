package MethodReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class MethodReferenceLab {
    public static void main(String[] args){
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    private static void staticMR() {

        List<Integer> myList = Arrays.asList(1,2,7,4,5);

        Consumer<List<Integer>> c = l -> Collections.sort(l);
        c.accept(myList);
        System.out.println(myList);
        myList = Arrays.asList(1,2,7,4,5);
        Consumer<List<Integer>> c2 = Collections::sort;
        c2.accept(myList);
        System.out.println(myList);
    }
    private static void boundMR(){
        String name =  "Mr. Joe Bloggs";
        Predicate<String> p = s -> name.startsWith(s);
        System.out.println(p.test("Mr."));
        System.out.println(p.test("Ms."));
        // Method ref
        Predicate<String> p2 = name::startsWith;
        System.out.println(p2.test("Mr."));
        System.out.println(p2.test("Ms."));
    }
    private static void unboundMR(){
        Predicate<String> p = s -> s.isEmpty();
        System.out.println(p.test(""));
        System.out.println(p.test("xyz"));

        Predicate<String> p2 = String::isEmpty;
        System.out.println(p2.test(""));
        System.out.println(p2.test("xyz"));

        BiPredicate<String,String> bp = (x,y) ->x.startsWith(y);

        System.out.println(bp.test("Mr. Joe Bloggs","Mr."));
        System.out.println(bp.test("Mr. Joe Bloggs","Ms."));
        // Method ref
        BiPredicate<String,String> bp2 = String::startsWith;
        System.out.println(bp2.test("Mr. Joe Bloggs","Mr."));
        System.out.println(bp2.test("Mr. Joe Bloggs","Ms."));
    }

    private static void constructorMR(){
        Supplier<List<String>> arr = () -> new ArrayList();
        List<String> list = arr.get();
        list.add("lambda");
        System.out.println(list);

        Supplier<List<String>> arr2 = ArrayList::new;
        list = arr2.get();
        list.add("Method Reference");
        System.out.println(list);

        Function<Integer,List<String>> f = (n) -> new ArrayList<>(n);
        list = f.apply(10);
        list.add("lambda");
        System.out.println(list);
        Function<Integer,List<String>> f2 = ArrayList::new;
        list = f.apply(10);
        list.add("Method Reference");
        System.out.println(list);
    }
}