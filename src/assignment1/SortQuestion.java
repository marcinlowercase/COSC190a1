package assignment1;

import java.util.Random;

public class SortQuestion {


    public static void main(String[] args) {

        int size = 1000000;
        int accuracyLevel = 1000;
        roughEstimate(size, accuracyLevel);
    }


    private static void roughEstimate(int size, int accuracyLevel){
        long modTime = 0;
        long merTime = 0;
        long bubTime = 0;
        long insTime = 0;


        for (int i = 0; i < accuracyLevel ; i++){


            // wrong
            int[] test = generateArr(size);

            long startMod = System.nanoTime();
            modifiedMergeSort(test);
            long endMod = System.nanoTime();

            long startMer = System.nanoTime();
            mergeSort(test);
            long endMer = System.nanoTime();

            long startBub = System.nanoTime();
            bubbleSort(test, test.length);
            long endBub = System.nanoTime();

            long startIns = System.nanoTime();
            insertionSort(test,test.length);
            long endIns = System.nanoTime();

            modTime += (endMod - startMod);
            merTime += (endMer - startMer);
            bubTime += (endBub - startBub);
            insTime += (endIns - startIns);

        }
        System.out.println( size +"...Mod: " + modTime/accuracyLevel);
        System.out.println( size +"...Mer: " + merTime/accuracyLevel);
        System.out.println( size +"...Bub: " + bubTime/accuracyLevel);
        System.out.println( size +"...Ins: " + insTime/accuracyLevel);
    }

    private static  int[] generateArr(int size){
        int[] temp = new int[size];

        for (int i = 0; i < temp.length; i++){
            temp[i] = new Random().nextInt(1000);
        }
        return temp;
    }

    private static void modifiedMergeSort(int[] list) {

        // base case
        if (list.length > 1) {

            if (list.length < 50){
                insertionSort(list, list.length);
            } else {
                // merge sort the left half of the list
                int leftLength = list.length / 2;
                int[] leftList = new int[leftLength];
                System.arraycopy(list, 0, leftList, 0, leftLength);
                mergeSort(leftList);

                // merge sort the right half of the list
                int rightLength = list.length - list.length / 2;
                int[] rightList = new int[rightLength];
                System.arraycopy(list, leftLength, rightList, 0, rightLength);
                mergeSort(rightList);

                merge(leftList, rightList, list);
            }




        }

    }
    private static void mergeSort(int[] list) {

        // base case
        if (list.length > 1) {


            // merge sort the left half of the list
            int leftLength = list.length / 2;
            int[] leftList = new int[leftLength];
            System.arraycopy(list, 0, leftList, 0, leftLength);
            mergeSort(leftList);

            // merge sort the right half of the list
            int rightLength = list.length - list.length / 2;
            int[] rightList = new int[rightLength];
            System.arraycopy(list, leftLength, rightList, 0, rightLength);
            mergeSort(rightList);

            merge(leftList, rightList, list);

        }

    }
    private static void merge(int[] leftList, int[] rightList, int[] list) {

        int currentLeft = 0, currentRight = 0, currentMerge = 0;

        while (currentLeft < leftList.length && currentRight < rightList.length) {
            if (leftList[currentLeft] < rightList[currentRight]) {
                list[currentMerge++] = leftList[currentLeft++];
            } else {
                list[currentMerge++] = rightList[currentRight++];
            }
        }

        while (currentLeft < leftList.length) {
            list[currentMerge++] = leftList[currentLeft++];
        }

        while (currentRight < rightList.length) {
            list[currentMerge++] = rightList[currentRight++];
        }

    }
    private static void bubbleSort(int[] iValues, int length) {
        if (length == 1) {
            return;
        }

        int count = 0;
        for (int i = 0; i < length - 1; i++) {
            if (iValues[i] > iValues[i + 1]) {
                int temp;
                temp = iValues[i];
                iValues[i] = iValues[i + 1];
                iValues[i + 1] = temp;

                count++;
            }
        }

        // Check if any recursion happens or not
        // If any recursion is not happen then return
        if (count == 0)
            return;

        // Largest element is fixed,
        // recur for remaining array
        bubbleSort(iValues, length - 1);
    }
    public static void insertionSort(int[] values, int length) {

        // base case
        if (length <= 1) {
            return;
        }

        // sort first n-1 values
        insertionSort(values, length - 1);

        // insert last element in the current position in sorted array

        int lastValue = values[length - 1];

        // shift the values[0 .. length-1], which are larger than last value,
        // to one index ahead of their current position
        int shiftingIndex;
        for (shiftingIndex = length - 2;
             shiftingIndex >= 0 && values[shiftingIndex]>lastValue;
             shiftingIndex--) {

            values[shiftingIndex + 1] = values[shiftingIndex];
        }

        values[shiftingIndex + 1] = lastValue;

    }

}
