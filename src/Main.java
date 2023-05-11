import arrayOperationsCore.MeasurementOperations;
import twoThreeTreeCore.TwoThreeTree;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        TwoThreeTree twoThreeTree = new TwoThreeTree();
        int[] randInts = MeasurementOperations.randomArr();
        long[][] insertTimesContainer = MeasurementOperations.insertAndCounts(twoThreeTree, randInts);
        long[][] searchTimesContainer = MeasurementOperations.searchCalls(twoThreeTree, randInts);
        long[][] removeTimesContainer = MeasurementOperations.removeAndCalls(twoThreeTree, randInts);
        writer("insertBuildings.txt", insertTimesContainer);
        writer("searchBuildings.txt", searchTimesContainer);
        writer("removeBuildings.txt", removeTimesContainer);


    }
    public static void writer(String filename, long[][] arr) throws IOException {
        PrintWriter writer = new PrintWriter(filename);
        for (int i = 0; i < arr.length-1; i++) {
            writer.print("\n");
            for (int j = 0; j < 2; j++){
                writer.print(arr[i][j] + " ");
            }
        }
        writer.print("\n");
        writer.println("average time in nanoseconds is " + MeasurementOperations.avgCalculator(arr)[0]);
        writer.println("average operations is " + MeasurementOperations.avgCalculator(arr)[1]);
        writer.close();
    }
}
