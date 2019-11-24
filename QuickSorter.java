/*
 * Ethan Newman
 * EEN170000
 * CS 3345.005
 * The task of this project is to implement in Java several variations of the in-place QuickSort
 * algorithm, each with a different choice of pivot. You should note the impact on execution time
 * of different pivot selection strategies.
 */

 /*
The first argument is the array size 
the second one is filename for the reports file, 
the third one is the filename to store the unsorted array and
the fourth is the filename to store sorted array.

./Main_Class 100000 report.txt unsorted.txt sorted.txt
*/



import java.util.Random;
import java.time.Duration;
import java.util.ArrayList;

public class QuickSorter
{

    /*
     * This private constructor is optional, but it does help to prevent accidental client instantiation of QuickSorter
     * via the default constructor.  (defining any constructor prevents the compiler from creating a default constructor)
     * This particular anti-instantiation technique is exactly what {@link java.util.Collections} does.
     */
    private QuickSorter() { }

    //todo: Null pointer exception if either of the parameters are null
    // You may assume that the list argument is in a valid state
    //and that every element therein is non-null; however, you should not assume that the list
    //argument is non-empty.
    //You should time the actual sort itself using java.lang.System.nanoTime()
    //Remember the quicksort will use the recursive strategy and for small array (<20 elements) use insertion sort methodology. 
    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy)
    {
        long startTime = System.nanoTime();

        // TODO implement timedQuickSort(ArrayList<E>, PivotStrategy)

        long finishTime = System.nanoTime();
        Duration elapsedTime = Duration.ofNanos(finishTime - startTime);

        return elapsedTime;
    }

    //if negative input, throw IllegalArgumentException
    public static ArrayList<Integer> generateRandomList(int size)
    {
        //create a new arrayList to be filled with random integers
        ArrayList<Integer> randList = new ArrayList<Integer>();
        Random random = new Random();

        //add all the ints to the list
        random.ints(size).forEach(num -> randList.add(num));


        return randList;
    }

    //PivotStrategy strat = PivotStrategy.RANDOM_ELEMENT;
    //print(strat)
    //RANDOM_ELEMENT
    public static enum PivotStrategy
    {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE_ELEMENTS
    }

    //for testing random list generation
    public static void main(String[] args)
    {
        ArrayList<Integer> testList = generateRandomList(10);

        testList.forEach(n -> System.out.println(n));
    }

}