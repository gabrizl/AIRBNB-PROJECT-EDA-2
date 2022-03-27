package algoritms;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class InsertionSort implements SortInterface {

  private int column;
  private Metrics metrics;
  private boolean sortForInteger;
  private String pathToSaveMetrics;
  private Pattern pattern;

  public InsertionSort(boolean sortForInteger) {
    this.column = 0;
    this.metrics = null;
    this.sortForInteger = sortForInteger;
    this.pathToSaveMetrics = null;
    this.pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
  }

  @Override
  public String[][] sort(String[][] matrix, int colomunIndex, String pathToSaveMetrics) {
    
    this.pathToSaveMetrics = pathToSaveMetrics;
    this.column = colomunIndex;

    if (this.sortForInteger) {
      sortForInt(matrix);
    } else {
      sortForStr(matrix);
    }

    return matrix;
  }

  private void sortForInt(String[][] matrix) {
    this.metrics = new Metrics(this.pathToSaveMetrics);
    
    this.metrics.start();
    this.metrics.writeMetrics();
    
    int key, j;
    for (int i = 0; i < matrix.length; i++) {
      key = Integer.parseInt(matrix[i][this.column]);
      String [] keyRow = matrix[i];
      j = i - 1;

      while ((j >= 0) && (Integer.parseInt(matrix[j][this.column]) > key)) {
        matrix[j+1] = matrix[j];
        j = j - 1;
      }
      
      matrix[j+1] = keyRow;
      
      this.metrics.start();
      this.metrics.writeMetrics();
    }

    this.metrics.start();
    this.metrics.writeMetrics();
  }

  private void sortForStr(String[][] matrix) {
    this.metrics = new Metrics(this.pathToSaveMetrics);

    this.metrics.start();
    this.metrics.writeMetrics();
    
    String key;
    int j;
    for (int i = 0; i < matrix.length; i++) {
      key = matrix[i][this.column];
      String [] keyRow = matrix[i];
      j = i - 1;

      while ((j >= 0) && ((semAcento(matrix[j][this.column]).toLowerCase())
                          .compareTo(semAcento(key).toLowerCase()) > 0)) {
        matrix[j+1] = matrix[j];
        j = j - 1;
      }
      
      matrix[j+1] = keyRow;
      
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

}
