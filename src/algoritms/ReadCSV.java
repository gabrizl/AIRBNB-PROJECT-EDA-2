package algoritms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

public class ReadCSV {

  private String filePath;
  private String sep;
  private String pathToSave;
  private String columnToOrder;
  private String pathToSaveMetrics;

  public ReadCSV(String filePath, String pathToSave,
      String pathToSaveMetrics, String columnToOrder, String sep) {
    this.filePath = filePath;
    this.pathToSave = pathToSave;
    this.columnToOrder = columnToOrder;
    this.pathToSaveMetrics = pathToSaveMetrics;
    this.sep = sep.length() == 0 ? "," : sep;
  }

  public void readCsv(SortInterface algoritm) throws ParseException {

    String arquivoCSV = this.filePath;
    BufferedReader br = null;
    String linha = "";

    int lineSize = lineSize() - 1;
    int columnSize = columnsSize() - 1;
    String[][] matrix = new String[lineSize][columnSize];
    int i = 0;

    try {

      br = new BufferedReader(new FileReader(arquivoCSV));
      String[] header = br.readLine().split(this.sep);
      while ((linha = br.readLine()) != null) {

        matrix[i++] = linha.split(this.sep);

      }

      int col = indexOfColumn(header);

      // matrix = DateFormat.mudarData(matrix);

      String[][] newMatrix = algoritm.sort(matrix, col, this.pathToSaveMetrics);

      saveInCsv(newMatrix, header);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void readCsvAndTransform(TransformInterface algoritm) throws ParseException {

    String arquivoCSV = this.filePath;
    BufferedReader br = null;
    String linha = "";

    int lineSize = lineSize() - 1;
    int columnSize = columnsSize() - 1;
    String[][] matrix = new String[lineSize][columnSize];
    int i = 0;

    try {

      br = new BufferedReader(new FileReader(arquivoCSV));
      String[] header = br.readLine().split(this.sep);
      while ((linha = br.readLine()) != null) {

        matrix[i++] = linha.split(this.sep);

      }

      int col = indexOfColumn(header);

      String[][] newMatrix = algoritm.transform(matrix, col, this.pathToSaveMetrics);

      saveInCsv(newMatrix, header);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void saveInCsv(String[][] matrix, String[] header) {
    try {

      FileWriter arq = new FileWriter(this.pathToSave);
      PrintWriter gravarArq = new PrintWriter(arq);
      String head = String.join(";", header);

      gravarArq.println(head);
      for (int i = 0; i < matrix.length; i++) {
        String line = String.join(";", matrix[i]);
        gravarArq.println(line);
      }

      gravarArq.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private int indexOfColumn(String[] header) {
    for (int i = 0; i < header.length; i++) {
      if (header[i].equalsIgnoreCase(this.columnToOrder)) {
        return i;
      }
    }
    return -1;
  }

  private int lineSize() {

    String arquivoCSV = this.filePath;
    BufferedReader br = null;
    int lines = 0;

    try {

      br = new BufferedReader(new FileReader(arquivoCSV));
      while (br.readLine() != null) {
        lines++;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return lines;
  }

  private int columnsSize() {
    String arquivoCSV = filePath;
    BufferedReader br = null;

    int columns = 0;

    try {

      br = new BufferedReader(new FileReader(arquivoCSV));
      String[] lineColumn = br.readLine().split(this.sep);

      for (int i = 0; i < lineColumn.length; i++) {
        columns++;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return columns;
  }

}
