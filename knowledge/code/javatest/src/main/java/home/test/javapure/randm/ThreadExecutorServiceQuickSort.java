package home.test.javapure.randm;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExecutorServiceQuickSort<Type extends Comparable<Type>> extends BaseQuickSort<Type> {

    private final ThreadPoolExecutor threadPool;

    public ThreadExecutorServiceQuickSort(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public List<Type> sort(List<Type> list) {
        try {
            //Костыль))0)
            int maximumPoolSize = threadPool.getMaximumPoolSize();
            int poolSize = threadPool.getPoolSize();
            if (poolSize < maximumPoolSize) {
                return threadPool.submit(
                        () -> sortInternal(list)
                ).get();
            } else {
                return sortInternal(list);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
