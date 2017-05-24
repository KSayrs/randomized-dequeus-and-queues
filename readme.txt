/******************************************************************************
 *  Name: Kat Sayrs
 *
 *  Operating system: Windows 7
 *  Compiler: Javac
 *  Text editor / IDE: Intellij IDEA 2016.1.1
 *  Hours to complete assignment (optional): probably from around 10-13
 ******************************************************************************/



/******************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 *****************************************************************************/

 For the deque, because there was no need to randomize anything, I chose to create
 and implement a linked list because it is more time efficient than going through
 and array. I chose to have two nodes to keep track of the front 
 and back ends, since it is supposed to be two-way. Once an item is added or 
 removed, parts of the affected Node are also removed to prevent loitering (and 
 also other possible issues).
 
 For the RandomizedQueue, I chose to have an array simply because of the built-in 
 shuffle function in StdRandom, which I couldn't get to work at first, but since my
 implementation was pretty code-heavy, I figured out how to switch it over to get
 it to work. Good practice though. 
 It starts out with an array of size 8 to avoid doubling early on. Whenever it is
 full and wants to add another item, it doubles. 
 Enqueue fills up all the null spaces it finds in order. This helps with dequeue issues,
 even though it adds extra time.
 Dequeue selects a random number and nullifies it.
 The Iterator makes an array that is usually smaller than the main array, so that it
 only fills up with values and has no nulls. The new array is then shuffled and spat out.
 

/******************************************************************************
 *  How much memory (in bytes) do your data types use to store N items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 *****************************************************************************/

Randomized Queue:   ~  4N  bytes

Object array = 42 + 2N (String byes) (The actual number on the N here would depend
on what object we sent though it...)
temporary array <= 42 + 2N
The rest is < N.

Deque:              ~  N  bytes

Linked List in memory: ~N bytes
everything else is constant in time and memory, meaning it's negligible in tilde
notation.




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

 If someone tries to continue using an iterator after going through it and then adding
 more onto the queue, weird things can happen. I don't think one uses iterators
 like that, though.


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and exercises, but do
 *  include any help from people (including classmates and friends) and
 *  attribute them by name.
 *****************************************************************************/

 Talking about using arrays in RandomizedQueue with Dot and Jeremy helped me realize
 that one of my solutions was more inefficient than it had to be.
 My CS friend Gary explained to me that doubling the resizing array was best for
 the general case, so I avoided doing something convoluted in an attempt to conserve
 memory. He also explained that generally one starts with a size 8 or 16, in case
 one ever approaches the memory limit.

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/

 At first, my two iterators were spitting out the same output. After switching
 back and forth between methods, I kept getting null values in my results even
 though there reasonably should not have been any (sometimes).
 I fixed most of those isses, but there was one that still baffles me. After
 dequeueing one, and then queueing more, my new iterator would havea null value
 int it even though my iterator class should have taken care of that.
I created a workaround that seeems to be fine, though still I'm not sure why
it was needed.


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/
 
 The huge amount of dumb mistakes I made in this project made me quickly relearn 
 and remember a bunch of things I'd forgotten.
 It was kind of fun to make a custom linked list, even though I don't usually care
 for data structures.
A decently large chunk of time is spent on this writing part. There are times where
it is unclear whether they are looking for the time complexity of the program or how much
space it takes up.

 Also, sorry about the comment format, I ran out of time to make them more conventional.