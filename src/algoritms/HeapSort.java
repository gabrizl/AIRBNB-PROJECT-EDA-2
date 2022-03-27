package algoritms;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class HeapSort implements SortInterface {
  
  private int column;
  private Metrics metrics;
  private boolean sortForInt;
  private Pattern pattern;
  
  public HeapSort(boolean sortForInt) {
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

    run(matrix);

    this.metrics.start();
    this.metrics.writeMetrics();

    return matrix;
  }

  private void run(String matrix[][]) {
    int n = matrix.length;

    for (int i = n / 2 - 1; i >= 0; i--) {
      this.metrics.start();
      this.metrics.writeMetrics();
      heapify(matrix, n, i);
    }

    for (int i = n - 1; i > 0; i--) {
      this.metrics.start();
      this.metrics.writeMetrics();
      
      String [] temp = matrix[0];
      matrix[0] = matrix[i];
      matrix[i] = temp;

      this.metrics.start();
      this.metrics.writeMetrics();

      heapify(matrix, i, 0);
    }
  }

  private void heapify(String matrix[][], int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (this.sortForInt) {
      
      if (l < n && Integer.parseInt(matrix[l][this.column]) 
        > Integer.parseInt(matrix[largest][this.column])) {
        largest = l;
      }
  
      if (r < n && Integer.parseInt(matrix[r][this.column]) 
        > Integer.parseInt(matrix[largest][this.column])) {
        largest = r;
      }
      
    } else {
      
      if (l < n && ((semAcento(matrix[l][this.column]))
        .compareToIgnoreCase(semAcento(matrix[largest][this.column])) > 0)) {
        largest = l;
      }
  
      if (r < n && ((semAcento(matrix[r][this.column]))
        .compareToIgnoreCase(semAcento(matrix[largest][this.column])) > 0)) {
        largest = r;
      }

    }


    if (largest != i) {
      String [] swap = matrix[i];
      matrix[i] = matrix[largest];
      matrix[largest] = swap;

      heapify(matrix, n, largest);
    }
  }

  private String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    return this.pattern.matcher(nfdNormalizedString).replaceAll("");
  }

}
