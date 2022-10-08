package PBL8;

import java.util.concurrent.Semaphore;

public class Config {
    static private int num_process = Runtime.getRuntime().availableProcessors();
    static public Semaphore[] mutexes = new Semaphore[num_process];
    static public int num_row;
    static public int num_col;
    static public float operations_per_thread;

    public static float getOperations_per_thread() {
        return operations_per_thread;
    }

    public static void setOperations_per_thread() {
        operations_per_thread = (num_col*num_row)/(float) num_process;
    }

    public static int getNum_row() {
        return num_row;
    }

    public static void setNum_row() {
        num_row = matrix_a.length;
    }

    public static int getNum_col() {
        return num_col;
    }

    public static void setNum_col() {
        num_col =  matrix_b[0].length;
    }

    static private double[][] matrix_a;
    static private double[][] matrix_b;

    static public Calculator_Matrix[] calculators = new Calculator_Matrix[num_process];


    public static void setMutexes() {
        for (int i = 0; i < num_process; i++) {
            mutexes[i]=new Semaphore(0);
            calculators[i] = new Calculator_Matrix(i);
        }


    }

    public static void startThreads(){
        for (Calculator_Matrix calc:
             calculators) {
            calc.start();
        }
    }
    public static void initMutexes() throws InterruptedException {
        for (Semaphore mutex:
                mutexes) {
            mutex.acquire();
        }
    }

    public static Semaphore[] getMutexes() {
        return mutexes;
    }

    public static Calculator_Matrix[] getCalculators() {
        return calculators;
    }

    public static void initConfig(double[][] matrix_a, double[][] matrix_b) throws InterruptedException {
        setNum_process();
        setMatrix_a(matrix_a);
        setMatrix_b(matrix_b);
        setNum_row();
        setNum_col();
        setOperations_per_thread();
        setMutexes();
        startThreads();
        initMutexes();

    }


    public static void setNum_process() {
        Config.num_process = Runtime.getRuntime().availableProcessors();
    }


    public static void setMatrix_a(double[][] matrix_a) {
        Config.matrix_a = matrix_a;
    }

    public static void setMatrix_b(double[][] matrix_b) {
        Config.matrix_b = matrix_b;
    }

    public static double[][] getMatrix_a() {
        return matrix_a;
    }

    public static double[][] getMatrix_b() {
        return matrix_b;
    }

    public static int getNum_process() {
        return num_process;
    }
}
