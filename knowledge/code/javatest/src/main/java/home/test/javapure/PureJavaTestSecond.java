package home.test.javapure;

import lombok.SneakyThrows;
import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class PureJavaTestSecond {

    public static void main(String[] args) throws InterruptedException {
        val t = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getId() + " sleeping");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Before");
        t.start();
        System.out.println("Operation");
        t.join();
        System.out.println("Ended");

        Collections.synchronizedList(List.of());
        new ConcurrentSkipListSet<String>();
    }

}
