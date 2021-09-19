package ca.bcit;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Homework - Sorting
 * Sort the list of doubles in the fastest possible way.
 * The only method you can change is the sort() method.
 * You can add additional methods if needed, without changing the load() and test() methods.
 */
public class Sorting {
    protected List list = new ArrayList<Integer>();

    /**
     * Loading the text files with double numbers
     */
    protected void load(){
        try (Stream<String> stream = Files.lines(Paths.get("numbers.txt"))) {
            stream.forEach(x -> list.add(Integer.parseInt(x)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing of your solution, using 100 shuffled examples
     * @return execution time
     */
    protected double test(){
        Stopwatch watch = new Stopwatch();
        for (int i=0; i<100; i++){
            Collections.shuffle(list, new Random(100));
            sort(list);
        }
        return watch.elapsedTime();
    }

    /**
     * Sorting method - add your code in here
     * @param list - list to be sorted
     */
    private void sort(List list){
        Object[] arr= list.toArray();
        //Collections.sort(list);
        QuickSort(arr);
    }

    private static void QuickSort(Object[] list) {
        QuickSort(list,0,list.length-1);
    }

    private static void QuickSort(Object[] list, int first, int last) {
        if(last>first){
            int PivotIndex=Partition(list,first,last);
            QuickSort(list,first,PivotIndex-1);
            QuickSort(list,PivotIndex+1,last);
        }
    }

    private static int Partition(Object[] list, int first, int last) {
        int low=first+1;
        int high=last;
        int pivot=(int)list[first];
        while (low<high){
            while (low<=high&&(int)list[low]<=pivot){
                low++;
            }
            while (low<=high&&(int)list[high]>pivot){
                high--;
            }
            //Swapping
            if(high>low){
                int temp=(int)list[low];
                list[low]=list[high];
                list[high]=temp;
            }
        }
        while (high>first&&(int)list[high]>=pivot){
            high--;
        }
        if((int)list[high]<pivot){
            list[first]=list[high];
            list[high]=pivot;
            return high;
        }
        return first;
    }
}
