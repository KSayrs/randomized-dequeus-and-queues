import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by Katyana on 4/15/2016.
 *
 * Takes an integer argument k and reads in N strings from StdInput.
 * It then prints out exactly k of the strings in uniformly random order.
 *
 * We're assuming 0<=k<=N. Otherwise it will throw errors.
 *
 */

public class Subset {

    public static void main(String[] args){

        int k = Integer.parseInt(args[0]);

        RandomizedQueue rq = new RandomizedQueue();
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            rq.enqueue(item);
        }
        //Stopwatch timer = new Stopwatch();

        for(int i=0; i<k; i++){
            StdOut.println(rq.dequeue());
        }
        //StdOut.print(timer.elapsedTime());
    }
}
