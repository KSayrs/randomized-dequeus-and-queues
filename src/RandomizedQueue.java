import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Katyana on 4/15/2016.
 *
 * Randomized Queue: whatever you want to do with it, it will be random. It's like a bag, except you can take stuff
 * out of it, and you won't know what you're getting.
 *
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int itemCount; // keep track of items
    private Item[] array;

    /** construct an empty randomized queue */
    public RandomizedQueue(){
        itemCount = 0;
        array = (Item[]) new Object[8]; // initial size of 8 just to not keep resizing in the early stages
    }

    /** move items to a bigger array of size newSize */
    // N
    private void resize(int newSize){
        Item[] temp = (Item[]) new Object[newSize]; //newsize
        for(int i=0; i<itemCount; i++){
            temp[i] = array[i];         // fill temp with array's stuff
        }
        array = temp;                   // reset array to temp
    }

    /** returns true if the queue is empty */
    public boolean isEmpty(){
        return itemCount == 0;
    }

    /** return the number of items on the queue */
    public int size(){ return itemCount;}

    /**  Add the item to the queue. Null items cannot be added. */
    public void enqueue(Item item){
        if (item == null){ throw new NullPointerException("Cannot add a null item"); }

        // fill the nulls
        if(itemCount < array.length) {
            for(int i=0;i<array.length;i++) {
                if(array[i] == null) {
                    array[i] = item;
                    break;
                }
            }
        }
        // resize when too big
        if(itemCount == array.length){
            resize(2*itemCount);
            array[itemCount] = item;
        }
        itemCount++;      // Item added!!
    }

    /**  Remove and return a random item. This will throw an error if called when the queue is empty. */
    public Item dequeue(){
        if(itemCount == 0){ throw new NoSuchElementException("Queue is empty"); }

        int rand = StdRandom.uniform(itemCount);
        while(array[rand] == null){                 // if it's a null value (only happens after dequeing)
            rand = StdRandom.uniform(array.length);  // pick another one
        }
        Item item = array[rand];                    // get the item
        array[rand] = null;                         // D E S T R O Y
        itemCount--;                                // decrement the item count
        return item;                                // return the item
    }

    /** return a random item (but do not remove it).
     * This will throw an error if called when the queue is empty.
     * */
    public Item sample(){
        if(itemCount == 0){
            throw new NoSuchElementException("Queue is empty");
        }
        int rand = StdRandom.uniform(array.length);
        while(array[rand] == null){                 // if it's a null value
            rand = StdRandom.uniform(array.length);  // pick another one
        }
        return array[rand];
    }

    /** return an independent iterator over items in random order */
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    // Private Iterator class
    private class ArrayIterator implements Iterator<Item>{
        private Item[] temp; // array full of the returned items
        private int itemsRemaining; // items left

        // copy the array, adds N
        private ArrayIterator(){
            itemsRemaining = itemCount;
            temp = (Item[]) new Object[itemCount]; //slightly less than N
            reset();
            StdRandom.shuffle(temp);
        }

        // not strictly necessary to have a separate method here, but hey it helped in the process
        private void reset(){
            int j=0;
            for(int i=0; i<array.length; i++){
                if(j == itemCount) {return;}
                if(array[i]!=null){
                    temp[j] = array[i];
                    j++;
                }
            }
        } //reset

        public boolean hasNext() {
           // StdOut.print(itemsRemaining);
            return itemsRemaining>0; }

        public Item next() {
            if (!this.hasNext()) { throw new NoSuchElementException("No more items left in queue"); }

            int noot = itemCount-itemsRemaining; //spot in the array
            itemsRemaining--;        // decrement
            return temp[noot];       // return the item
        }
        public void remove() { throw new UnsupportedOperationException("Cannot use remove()");}
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        StdOut.println("# of items in queue: " + queue.size());
        StdOut.println("Is Empty? " + queue.isEmpty());

        // read in from standard input - test with Whose woods these are I think I know
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            queue.enqueue(item);
        } //while

        StdOut.println("# of items in queue: " + queue.size());
        StdOut.println("Random Sample 1: " + queue.sample());
        StdOut.println("Random item removal: " + queue.dequeue());
        StdOut.println("New item count: " + queue.size());
        if(!queue.isEmpty()) {
            StdOut.println("Random Sample 2: " + queue.sample());
            StdOut.println("Random Sample 3: " + queue.sample());
        }
        StdOut.println("Is Empty? " + queue.isEmpty());

        // make some iterators
        Iterator it = queue.iterator();
        Iterator it2 = queue.iterator();

        // print out the iterators
        StdOut.println("\n-----------------");
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        StdOut.println("\n-----------------");
        while(it2.hasNext()){
            System.out.print(it2.next() + " ");
        }

        StdOut.println("\n-----------------");

        // empty it out
        while (!queue.isEmpty()){
            StdOut.print(queue.dequeue() + " ");
        }

        // add some noots
       /* for(int i=0; i<4; i++){
            queue.enqueue("noot");
        }

        // add some waffles
        for(int i=0; i<4; i++){
            queue.enqueue("waffle");
        }*/

        // scramble line 2
        String phrase = "His house is in the village though";
        String[] words = phrase.split(" ");
        for(String word : words){
            queue.enqueue(word);
        }

        // make another iterator
        // Iterator it3 = queue.iterator();
        // reset the iterators
        it = queue.iterator();
        it2 = queue.iterator();
        StdOut.println("\n-----------------");
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }

        StdOut.println("\n-----------------");

        while(it2.hasNext()){
            System.out.print(it2.next() + " ");
        }
    }
}