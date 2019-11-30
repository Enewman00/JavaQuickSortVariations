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
        //create 3 copies of it
        ArrayList<Integer> toSortRandom = (ArrayList<Integer>) toSort.clone();
        ArrayList<Integer> toSortMedianRandom = (ArrayList<Integer>) toSort.clone();
        ArrayList<Integer> toSortMedian = (ArrayList<Integer>) toSort.clone();

        //record unsorted array into unsorted.txt
        // file output
        try
        {
            //open unsorted.txt (in args) for output
            File output_file = new File(args[2]);
            PrintWriter unsortedOut;
            unsortedOut = new PrintWriter(output_file);

            //for each item in the arraylist, output it to the file.
            toSort.forEach(n -> unsortedOut.println(n));
            unsortedOut.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }        
        

        //Save duration of sort using FIRST_ELEMENT,RANDOM_ELEMENT, MEDIAN_OF_THREE_RANDOM_ELEMENTS, MEDIAN_OF_THREE_ELEMENTS
        //sort with first element
        Duration firstTime = QuickSorter.timedQuickSort(toSort, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        Duration randomTime = QuickSorter.timedQuickSort(toSortRandom, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
        Duration randomMedTime = QuickSorter.timedQuickSort(toSortMedianRandom, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        Duration medianTime = QuickSorter.timedQuickSort(toSortMedian, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
        

        // output report (should be sorted now) to sorted.txt
        try {
            // open unsorted.txt (in args) for output
            File output_file = new File(args[1]);
            PrintWriter reportOut;
            reportOut = new PrintWriter(output_file);

            // for each item in the arraylist, output it to the file.
            reportOut.println("Array Size = " + args[0]);
            reportOut.println("FIRST_ELEMENT : " + firstTime);
            reportOut.println("RANDOM_ELEMENT : " + randomTime);
            reportOut.println("MEDIAN_OF_THREE_RANDOM_ELEMENTS : " + randomMedTime);
            reportOut.println("MEDIAN_OF_THREE_ELEMENTS : " + medianTime);

            reportOut.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }





        //output toSort (should be sorted now) to sorted.txt
        try
        {
            //open unsorted.txt (in args) for output
            File output_file = new File(args[3]);
            PrintWriter sortedOut;
            sortedOut = new PrintWriter(output_file);

            //for each item in the arraylist, output it to the file.
            toSort.forEach(n -> sortedOut.println(n));
            sortedOut.close();
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }   



        //print 
        // Duration test2 = timedQuickSort(toSort, RANDOM_ELEMENT);
        // Duration test3 = timedQuickSort(toSort, MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        // Duration test4 = timedQuickSort(toSort, MEDIAN_OF_THREE_ELEMENTS);


    }

}