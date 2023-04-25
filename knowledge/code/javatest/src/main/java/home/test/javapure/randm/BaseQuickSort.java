package home.test.javapure.randm;

import lombok.val;

import java.util.ArrayList;
import java.util.List;


/**
 * Максимально тупая реализация квиксорта, но хоть многопоточку прогоню
 * @param <Type>
 */
abstract public class BaseQuickSort<Type extends Comparable<Type>> {

    public abstract List<Type> sort(List<Type> list);

    protected List<Type> sortInternal(List<Type> list) {
        int size = list.size();
        if (size <= 1) {
            return list;
        }
        //костыль))0)
        if (size == 2) {
            Type first = list.get(0);
            Type second = list.get(1);
            if (first.compareTo(second) > 0) {
                return List.of(second, first);
            } else {
                return List.of(first, second);
            }
        }

        var currentPivotIndex = getPivotIndex(list);
        val pivot = list.get(currentPivotIndex);

        //внимание, тупость. Памяти пиздос (лучше сразу стек пошире сделать):
        val rebasedList = new ArrayList<Type>(size);

        val listLess = list.stream()
                .filter((it) -> it.compareTo(pivot) < 0)
                .toList();
        rebasedList.addAll(listLess);
        rebasedList.add(pivot);
        currentPivotIndex = listLess.size();

        //Naming))00)
        val listMore = list.stream()
                .filter((it) -> it.compareTo(pivot) >= 0)
                .toList();
        rebasedList.addAll(listMore);
        rebasedList.remove(
                rebasedList.lastIndexOf(pivot)
        );

        val leftSorted = sort(rebasedList.subList(0, currentPivotIndex));
        val rightSorted = sort(rebasedList.subList(currentPivotIndex, size));
        val result = new ArrayList<Type>(size);
        result.addAll(leftSorted);
        result.addAll(rightSorted);
        return result;
    }

    private int getPivotIndex(List<Type> list) {
        return list.size() / 2;
    }

}
