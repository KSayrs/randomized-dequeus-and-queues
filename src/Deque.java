import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Katyana on 4/15/2016.
 *
 * Deque - a data type that can function as both a stack and a queue, depending on how the user wants to
 * implement it.
 *
 *
 * The words "next' and 'prev' can be confusing depending on how one visualizes a stack/queue. For this,
 * imagine the stack lying down.
 *
 * | pointHead | next |  |  |  |  | prev | pointTail |
 *
 */

public class Deque<Item> implements Iterable<Item> {

    private int itemCount; // keep track of items in deque
    private Node pointHead;
    private Node pointTail;

    /** construct an empty deque */
    public Deque(){
        this.itemCount = 0;
        this.pointHead = null;
        this.pointTail = null;
    }

    // private node class
    private class Node {
        private Item item;
        private Node next; // when adding/removing from front
        private Node prev; // when adding/removing from end

        // everything is empty, like my heart
        Node(Item item){
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    /** Returns true if the deque is empty */
    public boolean isEmpty(){
        return itemCount == 0;
    }

    /** return the number of items on the deque */
    public int size(){
        return itemCount;
    }

    /** add the item to the front of the deque. Cannot add a null item. */
    public void addFirst(Item item){
        if(item == null){ throw new NullPointerException("Cannot add a null item."); }

        if(itemCount == 0){               // resetting nodes back to one
            pointHead = new Node(item);
            pointTail = pointHead;
        } else {
            Node oldHead = pointHead;     //old data
            pointHead = new Node(item);   // replacing the head pointer
            pointHead.next = oldHead;     // old value is the next one in the line
            oldHead.prev = pointHead;
        }
        itemCount++;              // adding an item =]
    }

    /** add the item to the end of the deque */
    public void addLast(Item item){
        if(item == null){ throw new NullPointerException("Cannot add a null item."); }
        if(isEmpty()){           // resetting nodes back to one
            pointTail = new Node(item);
            pointHead = pointTail;
        } else {
            Node oldTail = pointTail;
            pointTail = new Node(item);
            oldTail.next = pointTail;
            pointTail.prev = oldTail;
        }
        itemCount++;
    }

    /** remove and return the item from the front of the deque. Throws an error if called when the deque is empty. */
    public Item removeFirst(){
        if(isEmpty()){ throw new NoSuchElementException("Cannot remove an item from an empty deque."); }

        Item item = pointHead.item; // old
        if(itemCount == 1){     // reset when empties
            pointTail = null;
            pointHead = null;
        } else {
            pointHead = pointHead.next; // moving the node dowwwwn
            pointHead.prev = null;      // nothing before, now
        }
        itemCount--;                // removing an item =[
        return item;                // return the removed item
    }

    /** remove and return the item from the end of the deque. Throws an error if called when the deque is empty. */
    public Item removeLast(){
        if(isEmpty()){ throw new NoSuchElementException("Cannot remove an item from an empty deque."); }
        Item item = pointTail.item; // old
        if(itemCount == 1){
            pointTail = null;
            pointHead = null;
        } else {
            pointTail = pointTail.prev; // moving the node uuuuup
            pointTail.next = null;      // nothing after, now
        }
        itemCount--;
        return item;                // return the removed item
    }

    /** return an iterator over items in order from front to end */
    public Iterator<Item> iterator(){
        return new InputIterator();
    }

    // private iterator class - goes from front to end
    private class InputIterator implements Iterator<Item>{
        private Node current = pointHead;                   // start at head
        public boolean hasNext() { return current!=null; }  // is hasnext?
        public Item next() {                                // moving current up
            if (!this.hasNext()) { throw new NoSuchElementException("No more items left in queue"); }
            else {
                if(itemCount == 1){ current = pointHead; }      // reset after being emptied
                Item item = current.item;
                current = current.next;
                return item;
            }
        } //next
        public void remove() { throw new UnsupportedOperationException("Cannot use remove()"); } // neh
    }

    // unit testing (required)
    public static void main(String[] args){

        // Create a queue and enqueue/dequeue ints.
        Deque<Integer> q = new Deque<>();
        StdOut.println("Deque size: " + q.size());

        //weird test
        // if a 5 is entered, it will remove the first integer from the queue.
        // If a 0 is entered, it will remove the last integer fo the queue
        // right now, it will still print 0 and 5. Because we want it to
        // test with 0 1 2 3 4 5 6 7 8 9 0
        /* while (!StdIn.isEmpty()) {
            int item = StdIn.readInt();
            if (item < 5){
                q.addFirst(item);
               // StdOut.println("Deque size: " + q.size());
            }
            if (item >= 5){
                q.addLast(item);
               // StdOut.println("Deque size: " + q.size());
            }
            else if (item == 0) {
                StdOut.println(q.removeLast() + " removed.");
            }
            else if (item == 5) {
                StdOut.println(q.removeFirst() + " removed.");
            }
        } // while */

        // recommended tests - both will show in ascending order
        // addFirst, removeLast - queue empties and then adds more
        for(int i=0; i<9; i++){
            q.addFirst(i);
        }

        StdOut.println("Deque size: " + q.size());
        Iterator it = q.iterator();

        while(it.hasNext()){
            System.out.print(" " + it.next());
        }
        StdOut.println();
        int N = q.size();
        for(int i=0; i<N; i++){
            StdOut.print(q.removeLast() + " ");
        }

        StdOut.println();
        StdOut.println("Deque size: " + q.size());
        StdOut.println();

        for(int i=10; i<19; i++){
            q.addFirst(i);
        }
        StdOut.println("Deque size: " + q.size());
        it = q.iterator();
        while(it.hasNext()){
            System.out.print(" " + it.next());
        }
        StdOut.println();
        int r = q.size();
        for(int i=0; i<r; i++){
            StdOut.print(q.removeLast() + " ");
        }
        StdOut.println();


        // addLast, removeFirst with 0-9
        for(int i=0; i<9; i++){
            q.addLast(i);
        }

        StdOut.println("Deque size: " + q.size());
        Iterator it2 = q.iterator();

        while(it2.hasNext()){
            System.out.print(" " + it2.next());
        }
        StdOut.println();
        int M = q.size();
        for(int i=0; i<M; i++){
            StdOut.print(q.removeFirst() + " ");
        }
        StdOut.println();
        StdOut.println("Deque size: " + q.size());
    }

}
