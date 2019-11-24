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
import java.io.PrintWriter;
import java.io.File;


public class Main
{
    public static void main(String[] args)
    {
        //get int from args
        int size = Integer.parseInt(args[0]);

        //create new arrayList using quickSorter
        ArrayList<Integer> toSort = QuickSorter.generateRandomList(size);

        //record unsorted array into unsorted.txt
        // file output
        try
        {
            File output_file = new File(args[2]);
            PrintWriter unsortedOut;
            unsortedOut = new PrintWriter(output_file);
            toSort.forEach(n -> unsortedOut.println(n));
            unsortedOut.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
        //unsortedOut.println();
        
        //print the unsorted array to unsorted.txt
        

        //Save duration of sort using FIRST_ELEMENT,RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
        // Duration test = timedQuickSort(toSort, FIRST_ELEMENT);
        // Duration test2 = timedQuickSort(toSort, RANDOM_ELEMENT);
        // Duration test3 = timedQuickSort(toSort, MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        // Duration test4 = timedQuickSort(toSort, MEDIAN_OF_THREE_ELEMENTS);


    }
}