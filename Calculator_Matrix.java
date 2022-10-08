package PBL8;

import java.util.concurrent.Semaphore;

public class Calculator_Matrix extends Thread{
    int id;
    Semaphore end;
    double[][] matrix_a;
    double[][] matrix_b;
    static private final int num_col = Config.getNum_col();
    static private final int num_row = Config.getNum_row();

    public double[][] getMatrix_a() {
        return matrix_a;
    }

    public void setMatrix_a(double[][] matrix_a) {
        this.matrix_a = matrix_a;
    }

    public double[][] getMatrix_b() {
        return matrix_b;
    }

    public void setMatrix_b(double[][] matrix_b) {
        this.matrix_b = matrix_b;
    }

    public Calculator_Matrix(int id) {
        this.id = id;
        init_concurrence();

    }

    private void init_concurrence(){
        this.end = Config.mutexes[id];
        this.matrix_a = Config.getMatrix_a();
        this.matrix_b = Config.getMatrix_b();
    }
    public double calc_element(int i, int j){
        double acc = 0;
        double[] row = matrix_a[i];
        double[] column = new double[matrix_b.length];
        for (int k = 0; k < matrix_b.length; k++) {
            column[k]=matrix_b[k][j];
        }
        for (int k = 0; k < matrix_b.length; k++) {
            acc+=row[k] * column[k];
        }
        return acc;
    }

    public void run() {
        end.release();
    }
}
