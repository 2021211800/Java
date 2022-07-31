package week03;

import java.util.Iterator;
import java.util.LinkedList;

class AnimalShelf {

    LinkedList<int[]> list;
    int[] EMPTY = {-1, -1};

    public AnimalShelf() {
        list = new LinkedList<>();
    }

    public void enqueue(int[] animal) {
        list.add(animal);
    }

    public int[] dequeueAny() {
        if (list.isEmpty()) {
            return EMPTY;
        }
        return list.pollFirst();
    }

    public int[] dequeueDog() {
        Iterator<int[]> iterator = list.iterator();
        while (iterator.hasNext()){
            int[] next = iterator.next();
            if (next[1]==1){
                iterator.remove();
                return next;
            }
        }
        return EMPTY;
    }

    public int[] dequeueCat() {
        Iterator<int[]> iterator = list.iterator();
        while (iterator.hasNext()){
            int[] next = iterator.next();
            if (next[1]==0){
                iterator.remove();
                return next;
            }
        }
        return EMPTY;
    }
}