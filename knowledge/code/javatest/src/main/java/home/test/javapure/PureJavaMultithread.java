package home.test.javapure;

import home.test.javapure.randm.SequentialQuickSort;
import lombok.val;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PureJavaMultithread {

    public static void main(String[] args) {
        val rand = new Random();
        val arrSize = 100;

        val list = IntStream.generate(rand::nextInt)
                .limit(arrSize)
                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("SORTED!!!!!!!!!!!!!!!!!!!!");
        val sort = new SequentialQuickSort<Integer>();
        val sorted = sort.sort(list);
        sorted.forEach(System.out::println);
    }

}
