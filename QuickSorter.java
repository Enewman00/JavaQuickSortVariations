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
import java.lang.Math;


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
        //pick pivot
        int pivotIndex = pickPivotIndex(list, strategy);


        long finishTime = System.nanoTime();
        Duration elapsedTime = Duration.ofNanos(finishTime - startTime);

        return elapsedTime;
    }


    //quicksort helper functions
    private static <E extends Comparable<E>> int pickPivotIndex(ArrayList<E> list, PivotStrategy strategy)
    {
        
        // if it's the first element, just use index 0
        if (strategy == PivotStrategy.RANDOM_ELEMENT)
        {
            return (int) Math.random() * (list.size() - 1);
        }
        if (strategy == PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS)
        {
            //get three different elements
            int first = (int) Math.random() * (list.size() - 1);

            //keep looking for a second element that does not equal the first one
            do
            {
                int second = (int) Math.random() * (list.size() - 1);
            }
            while (second == first);

            //keep looking for a third element that does not equal the first two
            do
            {
                int third = (int) Math.random() * (list.size() - 1);
            }
            while (third == first || third == second);


            //now the three elements are picked...
            //return the median of the three
            if ((list.get(first).compareTo(list.get(second)) < 0 && list.get(second).compareTo(list.get(third)) < 0) || (list.get(third).compareTo(list.get(second)) < 0 && list.get(second).compareTo(list.get(first)) < 0))
            {
                return second;
            }

            if ((list.get(second).compareTo(list.get(first)) < 0 && list.get(first).compareTo(list.get(third)) < 0) || (list.get(third).compareTo(list.get(first)) < 0 && list.get(first).compareTo(list.get(second)) < 0))
            {
                return first;
            }

            else
            {
                return third;
            }
        }
        //median of the first element, the middle element, and the last element
        if (strategy == PivotStrategy.MEDIAN_OF_THREE_ELEMENTS)
        {
            int first = 0;
            int second = (list.size() - 1) / 2;
            int third = list.size() - 1;


            //now the three elements are picked...
            //return the median of the three
            //now the three elements are picked...
            //return the median of the three
            if ((list.get(first).compareTo(list.get(second)) < 0 && list.get(second).compareTo(list.get(third)) < 0) || (list.get(third).compareTo(list.get(second)) < 0 && list.get(second).compareTo(list.get(first)) < 0))
            {
                return second;
            }

            if ((list.get(second).compareTo(list.get(first)) < 0 && list.get(first).compareTo(list.get(third)) < 0) || (list.get(third).compareTo(list.get(first)) < 0 && list.get(first).compareTo(list.get(second)) < 0))
            {
                return first;
            }

            else
            {
                return third;
            }
        }
        //(else) if it's the first element, just use index 0
        return 0;
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