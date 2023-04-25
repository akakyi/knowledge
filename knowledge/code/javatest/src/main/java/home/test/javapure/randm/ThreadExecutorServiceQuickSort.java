package home.test.javapure.randm;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorServiceQuickSort<Type extends Comparable<Type>> extends BaseQuickSort<Type> {

    private final ExecutorService threadPool;

    public ThreadExecutorServiceQuickSort(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public List<Type> sort(List<Type> list) {
        try {
            return threadPool.submit(
                    () -> sortInternal(list)
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
