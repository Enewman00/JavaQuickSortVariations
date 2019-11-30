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
        if (list == null || strategy == null)
        {
            throw new NullPointerException("illegal null parameter");
        }

        //start the timer
        long startTime = System.nanoTime();
        
        //start the quick sort
        quickSortHelper(list, strategy, 0, list.size() - 1);


        long finishTime = System.nanoTime();
        Duration elapsedTime = Duration.ofNanos(finishTime - startTime);

        return elapsedTime;
    }

    //quick sort recursion helper
    public static <E extends Comparable<E>> void quickSortHelper(ArrayList<E> list, PivotStrategy strategy, int start, int end)
    {
        //pick pivot
        int pivotIndex = pickPivotIndex(list, strategy, start, end);

        //change pivot to the rightmost element
        swapElements(list, pivotIndex, end);
        pivotIndex = end;

        //i at start, j at end, loop until i and j cross
        //inside there, loop until i gets to a element > pivot and j gets to element > pivot
        //swap those two elements
        int i = start;
        int j = end - 1;

        //insertion sort if subarray smaller than 20
        if (start + 20 > end)
        {
            insertionSort(list, start, end);
            //insertion sort
            //insertionSort(list, start, end)
        }
        else //quicksort
        {
            //infinite loop
            while (true)
            {
                //loop through until i gets to a number greater than the pivot
                while (list.get(i).compareTo(list.get(pivotIndex)) < 0 && i < end - 1)
                {
                    i++;
                }
                //loop through until j gets to a number less than the pivot
                while (list.get(j).compareTo(list.get(pivotIndex)) > 0 && j > 0)
                {
                    j--;
                }

                //if i < j, swap them, else break
                if (i < j)
                {
                    swapElements(list, i, j);
                }
                else
                {
                    break;
                }
            }
            //put the pivot back in the correct spot
            swapElements(list, i, end);
            pivotIndex = i;

            //quick sort recursively left and right
            quickSortHelper(list, strategy, start, pivotIndex - 1);
            quickSortHelper(list, strategy, pivotIndex + 1, end);

        }
    }


    // insertion sort for subarrays that are less than 20 items 
    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list, int start, int end)
    {
        //loop through, get next element to insert in sorted part of array
        for (int i = start; i <= end; i++)
        {
            //value to insert
            E toInsert = list.get(i);

            //loop through from start to i, find good place for item at i
            for (int j = start; j < i; j++)
            {
                if (toInsert.compareTo(list.get(j)) < 0)
                {
                    //insert toInsert at index j and delete old one at j
                    //E temp = list.get(j);
                    list.add(j, toInsert);
                    list.remove(i + 1); //delete old first to not mess up order
                    break;
                }
            }
        }
    }

    //swap function to swap two elements in the arraylist
    private static <E extends Comparable<E>> void swapElements(ArrayList<E> list, int indexOne, int indexTwo)
    {
        E temp = list.get(indexOne);
        list.set(indexOne, list.get(indexTwo));
        list.set(indexTwo, temp);
    }

    //quicksort helper functions
    // (int)(Math.random() * (max - min) + min)
    private static <E extends Comparable<E>> int pickPivotIndex(ArrayList<E> list, PivotStrategy strategy, int start, int end)
    {
        
        // if it's the first element, just use index 0
        if (strategy == PivotStrategy.RANDOM_ELEMENT)
        {
            return (int) (Math.random() * (end - start) + start);
        }
        if (strategy == PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS)
        {
            //get three different elements
            int first = (int) (Math.random() * (end - start) + start);
            int second, third;
                     
            second = (int) (Math.random() * (end - start) + start);

            third = (int) (Math.random() * (end - start) + start);


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
            int first = start;
            int second = (start + end) / 2;
            int third = end;


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
        return start;
    }

    



    //if negative input, throw IllegalArgumentException
    public static ArrayList<Integer> generateRandomList(int size)
    {
        if (size < 0)
        {
            throw new IllegalArgumentException("size is negative");
        }
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

}