package algoritms;

import java.text.ParseException;

public class AboveMedia implements TransformInterface {

    @Override
    public String[][] transform(String[][] matrix, int indexCol, String savePath) throws ParseException {
        Double media = getMedia(matrix, indexCol);
        int line = getQtdAboveMedia(matrix, indexCol, media);
        int column = matrix[0].length;
        String[][] matrixAux = new String[line][column];
        int j = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (Double.parseDouble(matrix[i][indexCol]) > media) {
                matrixAux[j++] = matrix[i];

            }
        }

        return matrixAux;
    }

    private int getQtdAboveMedia(String[][] matrix, int indexCol, double media) {

        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (Double.parseDouble(matrix[i][indexCol]) > media) {
                count = count + 1;
            }
        }

        return count;
    }

    private Double getMedia(String[][] matrix, int indexCol) {
        Double sum = 0.0;
        int i = 0;
        for (; i < matrix.length; i++) {
            sum = sum + Double.parseDouble(matrix[i][indexCol]);

        }
        Double media = sum / i;
        return media;
    }
}
