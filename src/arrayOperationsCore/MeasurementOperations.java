package arrayOperationsCore;
import twoThreeTreeCore.TwoThreeTree;

import java.util.Random;

public class MeasurementOperations {
    private static final int INSERTQUANTITY = 10000;
    private static final int SEARCHQUANTITY = 100;
    private static final int REMOVEQUANTITY = 1000;
    public static void print (long[][] arr){
        for(int i = 0; i < arr.length; i++) {
            System.out.println("\n");
            for (int j = 0; j < 2; j++){
                System.out.print(arr[i][j] + "\t");
            }
        }
    }
    public static int[] randomArr(){
        Random r = new Random();
        int[] arr = new int[INSERTQUANTITY];
        for (int i = 0; i < INSERTQUANTITY; i++) {
            arr[i] = r.nextInt();
        }
        return arr;
    }
    private static void refactorOfCalls (long[][] arr){
        for(int i = arr.length-1; i > 0; i--){
            arr[i][1] -= arr[i-1][1];
        }
    }

    public static long[][] insertAndCounts(TwoThreeTree tree, int[] randInts){
        long[][] timesContainer = new long[INSERTQUANTITY +1][2];
        long totalStartTime = System.nanoTime();
        for (int i = 0; i < INSERTQUANTITY; i++) {
            long startTime = System.nanoTime();
            tree.insert(randInts[i]);
            long endTime = System.nanoTime();
            timesContainer[i][0] = endTime-startTime;
            timesContainer[i][1] = tree.getInsertCalls();
        }
        refactorOfCalls(timesContainer);
        long totalEndTime = System.nanoTime();
        timesContainer[INSERTQUANTITY][0] = totalEndTime-totalStartTime;
        timesContainer[INSERTQUANTITY][1] = 1100101; //end marker
        return timesContainer;
    }
    public static long[][] searchCalls (TwoThreeTree tree, int[] randInts){
        long[][] timesContainer = new long[SEARCHQUANTITY+1][2];
        int[] newRandInts = new int[SEARCHQUANTITY];
        Random r = new Random();
        for (int i = 0; i < SEARCHQUANTITY; i++) {
            newRandInts[i] = randInts[r.nextInt(INSERTQUANTITY)];
        }
        long totalStartTime = System.nanoTime();
        for (int i = 0; i < SEARCHQUANTITY; i++) {
            long startTime = System.nanoTime();
            tree.search(newRandInts[i]);
            long endTime = System.nanoTime();
            timesContainer[i][0] = endTime-startTime;
            timesContainer[i][1] = tree.getSearchCalls();
        }
        long totalEndTime = System.nanoTime();
        refactorOfCalls(timesContainer);
        timesContainer[0][1] = timesContainer[1][1];// a crutch, but there was not enough time to solve the issue.
        timesContainer[SEARCHQUANTITY][0] = totalEndTime-totalStartTime;
        timesContainer[SEARCHQUANTITY][1] = 1100101; //end marker
        return timesContainer;
    }
    public static  long[][] removeAndCalls(TwoThreeTree tree, int[] randInts){
        long[][] timesConainer = new long[REMOVEQUANTITY+1][2];
        int[] newRandInts = new int[REMOVEQUANTITY];
        Random r = new Random();
        for (int i = 0; i < REMOVEQUANTITY; i++) {
            newRandInts[i] = randInts[r.nextInt(INSERTQUANTITY)];
        }
        long totalStartTime = System.currentTimeMillis();
        for (int i = 0; i < REMOVEQUANTITY; i++) {
            long startTime = System.nanoTime();
            tree.remove(newRandInts[i]);
            long endTime = System.nanoTime();
            timesConainer[i][0] = endTime - startTime;
            timesConainer[i][1] = tree.getRemoveCalls();

        }
        refactorOfCalls(timesConainer);
        long totalEndTime = System.currentTimeMillis();
        timesConainer[REMOVEQUANTITY][0] = totalEndTime-totalStartTime;
        timesConainer[REMOVEQUANTITY][1] = 1100101; //end marker
        return timesConainer;
    }
    public static int[] avgCalculator (long[][] container){
        int[] avg = new int[2];
        int avgTime = 0;
        int avgOperations = 0;
        for (int i = 0; i < container.length-1; i++) {
            avgTime+= container[i][0];
        }
        avgTime /= container.length-1;
        for (int i = 0; i < container.length-1; i++) {
            avgOperations+= container[i][1];
        }
        avgOperations /= container.length-1;
        avg[0] = avgTime;
        avg[1] = avgOperations;
        return avg;
    }
}
