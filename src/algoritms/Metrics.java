package algoritms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Metrics {
  private double allocatedMem;
  private double totalFreeMem;
  private FileWriter arq;
  private PrintWriter gravarArq;
  private String pathToSave;

  public Metrics(String pathToSave) {
    this.allocatedMem = 0;
    this.totalFreeMem = 0;
    this.pathToSave = pathToSave;

    try {
      this.arq = new FileWriter(pathToSave);
      this.gravarArq = new PrintWriter(arq);
      this.gravarArq.println("allocated_memory_MB,total_memory_MB,milliseconds");
      this.gravarArq.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    Runtime runtime = Runtime.getRuntime();
    
    this.allocatedMem = runtime.totalMemory() / (1024*1024);
    this.totalFreeMem = (runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory())) / (1024*1024);
  }

  public void writeMetrics() {
    try {
      BufferedWriter arq = new BufferedWriter(new FileWriter(this.pathToSave, true));
      
      arq.append(getStringMetrics());
      arq.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getStringMetrics() {
    String auxAllocatedMem = Double.toString(this.allocatedMem).replace(",", ".");
    String auxTotalFreeMem = Double.toString(this.totalFreeMem).replace(",", ".");

    String metrics = String.format("%s,%s,%d\n", auxAllocatedMem,
                                                 auxTotalFreeMem,
                                                 System.currentTimeMillis());

    return metrics;
  }
}
