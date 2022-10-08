package PBL8;


import java.util.Arrays;

public class Executable {

    private static int[] pos_by_num(int num){
        int[] matrix_pos = new int[2];
        matrix_pos[0] = num/5;
        matrix_pos[1] = num%4;
        System.out.println(Config.getNum_col());
        return matrix_pos;
    }
    public static void main(String[] args) throws InterruptedException {

        double[][] matrix_a = {{4,2},{8,4},{6,8},{2,5},{7,-4}};
        double[][] matrix_b = {{1,2,3,-2},{-2,0,4,1}};


        Config.initConfig(matrix_a,matrix_b);
        System.out.println(Config.getNum_row());
        System.out.println(Config.getNum_col());
        System.out.println(Config.getOperations_per_thread());

//        System.out.println("mutexes: " +Arrays.toString(Config.getMutexes()));
//        System.out.println("---------");
//        System.out.println("Calcs: " + Arrays.toString(Config.getCalculators()));
//
//        for (double[] doubles : matrix_a) {
//            for (int j = 0; j < matrix_a[0].length; j++) {
//                System.out.print(doubles[j] + " ");
//            }
//            System.out.println();
//        }
//        for (double[] doubles : matrix_b) {
//            for (int j = 0; j < matrix_b[0].length; j++) {
//                System.out.print(doubles[j] + " ");
//            }
//            System.out.println();
//        }
//

        double[] results = new double[matrix_a.length * matrix_b[0].length];

        int index = 0;
        int process = (int) Config.getOperations_per_thread();
        Calculator_Matrix calc = Config.calculators[0];
        for (int i = 0; i < matrix_a.length; i++) {
            for (int j = 0; j < matrix_b[0].length; j++) {
                if(index%process==0){
                    System.out.println("process: " +index);
                }
                results[index] = calc.calc_element(i,j);
                index++;
            }

        }

        System.out.println(Arrays.toString(results));

        System.out.println(Arrays.toString(pos_by_num(20)));

        System.out.println(calc.calc_element(pos_by_num(20)[0],pos_by_num(20)[1]));


        }

}
