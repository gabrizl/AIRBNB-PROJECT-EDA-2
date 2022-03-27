package algoritms;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CountingSort implements SortInterface {
  
  private int column = 0;
  private Metrics metrics;
  private boolean sortForInt;
  private Pattern pattern;

  public CountingSort(boolean sortForInt) {
    this.column = 0;
    this.metrics = null;
    this.sortForInt = sortForInt;
    this.pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
  }

  @Override
  public String[][] sort(String[][] matrix, int colomunIndex, String pathToSaveMetrics) {

    this.column = colomunIndex;

    this.metrics = new Metrics(pathToSaveMetrics);
    this.metrics.start();
    this.metrics.writeMetrics();

    if (this.sortForInt) {
      countSortInt(matrix);
    } else {
      voidForSpace(matrix);
      countSortString(matrix);
    }

    this.metrics.start();
    this.metrics.writeMetrics();

    return matrix;
  }

  private void countSortInt(String[][] matrix) {

    int [] auxCountingArray = new int[matrix.length];
    for (int i = 0; i < matrix.length; i++) {
      auxCountingArray[i] = Integer.parseInt(matrix[i][this.column]);
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    int max = Arrays.stream(auxCountingArray).max().getAsInt();
    int min = Arrays.stream(auxCountingArray).min().getAsInt();
    int range = max - min + 1;
    int count[] = new int[range];
    String output[][] = new String[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      count[Integer.parseInt(matrix[i][this.column]) - min]++;
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    for (int i = matrix.length - 1; i >= 0; i--) {
      output[count[Integer.parseInt(matrix[i][this.column]) - min] - 1] = matrix[i];
      count[Integer.parseInt(matrix[i][this.column]) - min]--;
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = output[i];
      this.metrics.start();
      this.metrics.writeMetrics();
    }

  }
  
  private void countSortString(String[][] matrix) {

    int [] auxCountingArray = new int[matrix.length];
    for (int i = 0; i < matrix.length; i++) {
      auxCountingArray[i] = (int) (semAcento(matrix[i][this.column])
                                    .toLowerCase()).toCharArray()[0];
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    int max = Arrays.stream(auxCountingArray).max().getAsInt();
    int min = Arrays.stream(auxCountingArray).min().getAsInt();
    int range = max - min + 1;
    int count[] = new int[range];
    String output[][] = new String[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      count[((int) (semAcento(matrix[i][this.column])
                    .toLowerCase()).toCharArray()[0]) - min]++;
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    for (int i = matrix.length - 1; i >= 0; i--) {
      output[count[((int) (semAcento(matrix[i][this.column])
                          .toLowerCase()).toCharArray()[0]) - min] - 1] = matrix[i];
      count[((int) (semAcento(matrix[i][this.column]).
                    toLowerCase()).toCharArray()[0]) - min]--;
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = output[i];
      this.metrics.start();
      this.metrics.writeMetrics();
    }

  }

  private String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    return this.pattern.matcher(nfdNormalizedString).replaceAll("");
  }

  private void voidForSpace(String [][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i][this.column].length() == 0) {
        matrix[i][this.column] = " ";
      }
    }
  }

}
