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
    public static void main(String[] args)
    {
        //get int from args
        int size = Integer.parseInt(args[0]);

        //create new arrayList using quickSorter
        ArrayList<Integer> toSort = QuickSorter.generateRandomList(size);



    }
}