package algoritms;

import java.text.Collator;
import java.util.LinkedList;

public class QuickSort2 implements SortInterface3 {

  private int indexColumn;
  private Metrics metrics;
  private boolean sortForInt;
  private final Collator instance;

  public QuickSort2(boolean sortForInt) {
    this.indexColumn = 1;
    this.metrics = null;
    this.sortForInt = sortForInt;
    this.instance = Collator.getInstance();
    this.instance.setStrength(Collator.NO_DECOMPOSITION);
  }

  @Override
  public LinkedList<String[]> sort(LinkedList<String[]> matrix, int colomunIndex, String pathToSaveMetrics) {
    this.indexColumn = colomunIndex;

    this.metrics = new Metrics(pathToSaveMetrics);
    this.metrics.start();
    this.metrics.writeMetrics();

    quickSort(matrix, 0, matrix.size() - 1);

    this.metrics.start();
    this.metrics.writeMetrics();

    return matrix;
  }

  private void swap(LinkedList<String[]> matrix, int i, int j) {
    // String[] temp = matrix[i];
    String[] temp = matrix.get(i);
    // matrix[i] = matrix[j];
    matrix.set(i, matrix.get(j));
    // matrix[j] = temp;
    matrix.set(j, temp);
  }

  private int partitionForInt(LinkedList<String[]> matrix, int low, int high) {
    // int pivot = Integer.parseInt(matrix[high][this.indexColumn]);
    int pivot = Integer.parseInt(matrix.get(high)[this.indexColumn]);
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
      if (Integer.parseInt(matrix.get(j)[this.indexColumn]) < pivot) {
        i++;
        swap(matrix, i, j);
      }
    }

    swap(matrix, i + 1, high);
    return (i + 1);
  }

  private int partitionForString(String[][] matrix, int low, int high) {
    String pivot = matrix[high][this.indexColumn].toLowerCase();
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
      if (!(this.instance.compare(matrix[j][this.indexColumn].toLowerCase(),
          pivot) > 0)) {
        i++;
        // swap(matrix, i, j);
      }
    }

    // swap(matrix, i + 1, high);
    return (i + 1);
  }

  private void quickSort(LinkedList<String[]> matrix, int low, int high) {

    this.metrics.start();
    this.metrics.writeMetrics();

    int pi = 0;
    if (low < high) {
      pi = partitionForInt(matrix, low, high);
      // if (this.sortForInt) {
      // } else {
      //   // pi = partitionForString(matrix, low, high);
      // }

      quickSort(matrix, low, pi - 1);
      quickSort(matrix, pi + 1, high);
    }

    this.metrics.start();
    this.metrics.writeMetrics();
  }

}
