package home.test.javapure.randm;

import java.util.List;

public class SequentialQuickSort<Type extends Comparable<Type>> extends BaseQuickSort<Type> {

    @Override
    public List<Type> sort(List<Type> list) {
        return sortInternal(list);
    }

}
