package algoritms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

public class ReadCSV2 {

  private String filePath;
  private String sep;
  private String pathToSave;
  private String columnToOrder;
  private String pathToSaveMetrics;

  public ReadCSV2(String filePath, String pathToSave,
      String pathToSaveMetrics, String columnToOrder, String sep) {
    this.filePath = filePath;
    this.pathToSave = pathToSave;
    this.columnToOrder = columnToOrder;
    this.pathToSaveMetrics = pathToSaveMetrics;
    this.sep = sep.length() == 0 ? "," : sep;
  }

  public void readCsv(SortInterface2 algoritm) throws ParseException {

    String arquivoCSV = this.filePath;
    BufferedReader br = null;
    String linha = "";

    int lineSize = lineSize() - 1;
    int columnSize = columnsSize() - 1;
    ArrayList<String[]> matrix = new ArrayList<String[]>();
    int i = 0;

    try {

      br = new BufferedReader(new FileReader(arquivoCSV));
      String[] header = br.readLine().split(this.sep);
      while ((linha = br.readLine()) != null) {

        String[] auxLine = linha.split(this.sep);
        matrix.add(auxLine);
      }

      int col = indexOfColumn(header);

      ArrayList<String[]> newMatrix = algoritm.sort(matrix, col, this.pathToSaveMetrics);
      ArrayList<String> csv = new ArrayList<>();

      for (int k = 0; k < newMatrix.size(); k++) {
        csv.add(String.join(",", newMatrix.get(k)).replace(",", ";"));
      }

      saveInCsv(csv, header);

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

  public void readCsvAndTransform(TransformInterface2 algoritm) throws ParseException {

    String arquivoCSV = this.filePath;
    BufferedReader br = null;
    String linha = "";

    int lineSize = lineSize() - 1;
    int columnSize = columnsSize() - 1;
    ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
    int i = 0;

    try {

      br = new BufferedReader(new FileReader(arquivoCSV));
      String[] header = br.readLine().split(this.sep);
      while ((linha = br.readLine()) != null) {
        String[] auxLine = linha.split(this.sep);
        
        ArrayList<String> auxLineList = new ArrayList<String>();
      
        for(int j = 0; j < auxLine.length; j++) {
          auxLineList.add(auxLine[j]);
        }

        matrix.add(auxLineList);
      }

      int col = indexOfColumn(header);

      // ArrayList<ArrayList<String>> newMatrix = algoritm.transform(matrix, col, this.pathToSaveMetrics);

      // saveInCsv(newMatrix, header);

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

  private void saveInCsv(ArrayList<String> matrix, String[] header) {
    try {

      FileWriter arq = new FileWriter(this.pathToSave);
      PrintWriter gravarArq = new PrintWriter(arq);
      String head = String.join(";", header);

      gravarArq.println(head);
      for (int i = 0; i < matrix.size(); i++) {
        gravarArq.println(matrix.get(i));
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
