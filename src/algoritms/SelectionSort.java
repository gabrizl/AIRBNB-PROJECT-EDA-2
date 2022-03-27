package algoritms;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SelectionSort implements SortInterface {

  private int column;
  private Metrics metrics;
  private boolean sortForInt;
  private String pathToSaveMetrics;
  private Pattern pattern;

  public SelectionSort(boolean sortForInt) {
    this.column = 0;
    this.metrics = null;
    this.sortForInt = false;
    this.pathToSaveMetrics = null;
    this.pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
  }

  @Override
  public String[][] sort(String[][] matrix, int colomunIndex, String pathToSaveMetrics) {

    this.column = colomunIndex;
    this.pathToSaveMetrics = pathToSaveMetrics;

    if (this.sortForInt) {
      sortForInteger(matrix);
    } else {
      sortForString(matrix);
    }

    return matrix;
  }

  private void sortForInteger(String[][] matrix) {
    this.metrics = new Metrics(this.pathToSaveMetrics);
    this.metrics.start();
    this.metrics.writeMetrics();

    for (int i = 0; i < matrix.length - 1; i++) {
      int min_idx = i;
      for (int j = i + 1; j < matrix.length; j++) {
        if (Integer.parseInt(matrix[j][this.column]) < Integer.parseInt(matrix[min_idx][this.column])) {
          min_idx = j;
        }
      }

      String[] temp = matrix[min_idx];
      matrix[min_idx] = matrix[i];
      matrix[i] = temp;

      this.metrics.start();
      this.metrics.writeMetrics();
    }

    this.metrics.start();
    this.metrics.writeMetrics();
  }

  private String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    return this.pattern.matcher(nfdNormalizedString).replaceAll("");
  }

  private void sortForString(String[][] matrix) {
    this.metrics = new Metrics(this.pathToSaveMetrics);
    this.metrics.start();
    this.metrics.writeMetrics();

    for (int i = 0; i < matrix.length - 1; i++) {
      int min_idx = i;
      for (int j = i + 1; j < matrix.length; j++) {
        if (!((semAcento(matrix[j][this.column]).toLowerCase())
            .compareTo(semAcento(matrix[min_idx][this.column]).toLowerCase()) > 0)) {
          min_idx = j;
        }
      }

      String[] temp = matrix[min_idx];
      matrix[min_idx] = matrix[i];
      matrix[i] = temp;

      this.metrics.start();
      this.metrics.writeMetrics();
    }

    this.metrics.start();
    this.metrics.writeMetrics();
  }

}