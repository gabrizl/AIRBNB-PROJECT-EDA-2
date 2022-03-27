package algoritms;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class MergeSort implements SortInterface {

  private int indexColumn;
  private Metrics metrics;
  private boolean sortForInt;
  private Pattern pattern;

  public MergeSort(boolean sortForInt) {
    this.indexColumn = 0;
    this.metrics = null;
    this.sortForInt = sortForInt;
    this.pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
  }

  @Override
  public String[][] sort(String[][] matrix, int colomunIndex, String pathToSaveMetrics) {

    this.metrics = new Metrics(pathToSaveMetrics);
    this.metrics.start();
    this.metrics.writeMetrics();

    this.indexColumn = colomunIndex;
    run(matrix, 0, matrix.length - 1);

    this.metrics.start();
    this.metrics.writeMetrics();

    return matrix;
  }

  public void mergeForInt(String matrix[][], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    String L[][] = new String[n1][matrix[0].length];
    String R[][] = new String[n2][matrix[0].length];

    for (int i = 0; i < n1; ++i) {
      L[i] = matrix[l + i];
    }

    for (int j = 0; j < n2; ++j) {
      R[j] = matrix[m + 1 + j];
    }

    int i = 0, j = 0;

    int k = l;
    while (i < n1 && j < n2) {
      if (Integer.parseInt(L[i][this.indexColumn]) <= Integer.parseInt(R[j][this.indexColumn])) {
        matrix[k] = L[i];
        i++;
      } else {
        matrix[k] = R[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      matrix[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      matrix[k] = R[j];
      j++;
      k++;
    }
  }

  public void mergeForString(String matrix[][], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    String L[][] = new String[n1][matrix[0].length];
    String R[][] = new String[n2][matrix[0].length];

    for (int i = 0; i < n1; ++i) {
      L[i] = matrix[l + i];
    }

    for (int j = 0; j < n2; ++j) {
      R[j] = matrix[m + 1 + j];
    }

    int i = 0, j = 0;

    int k = l;
    while (i < n1 && j < n2) {
      if (!((semAcento(L[i][this.indexColumn]).toLowerCase())
          .compareTo(semAcento(R[j][this.indexColumn]).toLowerCase()) > 0)) {
        matrix[k] = L[i];
        i++;
      } else {
        matrix[k] = R[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      matrix[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      matrix[k] = R[j];
      j++;
      k++;
    }
  }

  private String semAcento(String str) {
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    return this.pattern.matcher(nfdNormalizedString).replaceAll("");
  }

  public void run(String matrix[][], int l, int r) {
    if (l < r) {
      int m = l + (r - l) / 2;

      this.metrics.start();
      this.metrics.writeMetrics();

      run(matrix, l, m);
      run(matrix, m + 1, r);

      if (this.sortForInt) {
        mergeForInt(matrix, l, m, r);
      } else {
        mergeForString(matrix, l, m, r);
      }

      this.metrics.start();
      this.metrics.writeMetrics();
    }
  }

}
