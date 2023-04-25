package home.test.javapure;

import home.test.javapure.randm.ThreadExecutorServiceQuickSort;
import home.test.javapure.randm.SequentialQuickSort;
import lombok.val;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PureJavaMultithread {

    public static void main(String[] args) {
        val rand = new Random();
        val arrSize = 100;

        val list = IntStream.generate(rand::nextInt)
                .limit(arrSize)
//                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());
        System.out.println();

        System.out.println("START SORTING!!!!!!!!!!!!!!!!!!!!");
        val sort = new SequentialQuickSort<Integer>();
        val seqStart = Instant.now();
        val sorted = sort.sort(list);
        val seqEnd = Instant.now();
        System.out.println("SORTED!!!!!!!!!!!!!!!!!!!!");
        long timeElapsed = Duration.between(seqStart, seqEnd).toMillis();
        System.out.printf("SEQUENTIAL TIME: %sms\n", timeElapsed);
//        sorted.forEach(System.out::println);
        System.out.println();


        System.out.println("START SORTING!!!!!!!!!!!!!!!!!!!!");
        val cachedThreadPool = Executors.newCachedThreadPool();
        try {
            val executorServiceQuickSort = new ThreadExecutorServiceQuickSort<Integer>(cachedThreadPool);
            val executorServiceStart = Instant.now();
            val executorServiceSorted = executorServiceQuickSort.sort(list);
            val executorServiceEnd = Instant.now();
            System.out.println("SORTED!!!!!!!!!!!!!!!!!!!!");
            long cachedThreadPoolTimeElapsed = Duration.between(executorServiceStart, executorServiceEnd).toMillis();
            System.out.printf("cachedThreadPool TIME: %sms\n", cachedThreadPoolTimeElapsed);
//            executorServiceSorted.forEach(System.out::println);
            System.out.println();
        } finally {
            cachedThreadPool.shutdown();
        }

        System.out.println("START SORTING!!!!!!!!!!!!!!!!!!!!");
        val coresCont = 4;
        val fixedThreadPool = Executors.newFixedThreadPool(coresCont);
        try {
            val executorServiceQuickSort = new ThreadExecutorServiceQuickSort<Integer>(fixedThreadPool);
            val executorServiceStart = Instant.now();
            val executorServiceSorted = executorServiceQuickSort.sort(list);
            val executorServiceEnd = Instant.now();
            System.out.println("SORTED!!!!!!!!!!!!!!!!!!!!");
            long cachedThreadPoolTimeElapsed = Duration.between(executorServiceStart, executorServiceEnd).toMillis();
            System.out.printf("fixedThreadPool TIME: %sms\n", cachedThreadPoolTimeElapsed);
//            executorServiceSorted.forEach(System.out::println);
            System.out.println();
        } finally {
            fixedThreadPool.shutdown();
        }
    }

}
