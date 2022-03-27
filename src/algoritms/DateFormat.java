package algoritms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat implements TransformInterface {

    @Override
    public String[][] transform(String[][] matrix, int indexCol, String savePath) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][indexCol] != null && !matrix[i][indexCol].equals("")) {
                Date data = formatador.parse(matrix[i][indexCol]);
                matrix[i][indexCol] = formatador2.format(data);

            }
        }
        return matrix;

    }
}
