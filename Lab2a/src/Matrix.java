import java.util.*;
import static java.lang.System.out;

public class Matrix{
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows*cols];
    }

    Matrix(double[][] d){
        this.cols = getLogestCols(d);
        this.rows = d.length;
        this.data = new double[rows*cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(j == d[i].length){
                    for(int k=0; k<cols-j; k++)
                        this.data[(cols*i)+j+k] = 0;
                    break;
                }
                this.data[(cols*i)+j] = d[i][j];
            }
        }
    }

    double[][] asArray(){
        double[][] return_array = new double[rows][];
        int counter = 0;

        for(int i=0; i<rows; i++){
            for(int j=1; j<=cols; j++) {
                if(data[cols*(i+1)-j] == 0)
                    counter++;
                else
                    break;
            }

            return_array[i] = new double[cols-counter];
            counter = 0;

            for(int j=cols; j>0; j--) {
                if(data[cols*(i+1)-j] != 0)
                    return_array[i][cols-j] = data[cols*(i+1)-j];
            }
        }

        return return_array;
    }

    double[][] asArrayWithZeros(){
        double[][] return_array = new double[rows][cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++)
                return_array[i][j] = data[cols*i+j];
        }

        return return_array;
    }

    Matrix dot(Matrix m){
        if(this.cols != m.rows)
            throw new RuntimeException(String.format("%d cols differs from %d rows", this.cols, m.rows));

        Matrix new_matrix = new Matrix(this.rows, m.cols);
        var A = asArrayWithZeros();
        var B = m.asArrayWithZeros();

        for(int i=0; i<this.rows ; i++){
            for(int k=0; k<m.cols; k++){
                for(int j=0; j<this.cols; j++)
                    new_matrix.data[i*new_matrix.cols+k] += A[i][j]*B[j][k];
            }
        }

        return new_matrix;
    }

    double frobenius(){
        int sum=0;

        for(int i=0; i<this.data.length ; i++) {
            sum += Math.pow(this.data[i],2);
        }

        return Math.sqrt(sum);
    }

    double get(int r,int c){
        if(r >= rows)
            throw new RuntimeException(String.format("%d rows exeeded %d", r, rows));
        if(c >= cols)
            throw new RuntimeException(String.format("%d cols exeeded %d", c, cols));

        return data[r*cols+c];
    }

    void set(int r,int c, double value){
        if(r >= rows)
            throw new RuntimeException(String.format("%d rows exeeded %d", r, rows));
        if(c >= cols)
            throw new RuntimeException(String.format("%d cols exeeded %d", c, cols));

        data[r*cols+c] = value;
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        rows = newRows;
        cols = newCols;
        asArray();
    }

    int[] shape(){
        return new int[]{rows, cols};
    }

    Matrix add(Matrix m){
        if(this.rows != m.rows)
            throw new RuntimeException(String.format("%d differs from %d", this.rows, m.rows));
        if(this.cols != m.cols)
            throw new RuntimeException(String.format("%d differs from %d", this.cols, m.cols));

        Matrix return_matrix = new Matrix(this.rows, this.cols);

        for(int i=0; i<this.data.length; i++)
            return_matrix.data[i] = this.data[i] + m.data[i];

        return return_matrix;
    }

    Matrix sub(Matrix m){
        if(this.rows != m.rows)
            throw new RuntimeException(String.format("%d rows differs from %d", this.rows, m.rows));
        if(this.cols != m.cols)
            throw new RuntimeException(String.format("%d cols differs from %d", this.cols, m.cols));

        Matrix return_matrix = new Matrix(this.rows, this.cols);

        for(int i=0; i<this.data.length; i++)
            return_matrix.data[i] = this.data[i] - m.data[i];

        return return_matrix;
    }

    Matrix mul(Matrix m){
        if(this.rows != m.rows)
            throw new RuntimeException(String.format("%d differs from %d", this.rows, m.rows));
        if(this.cols != m.cols)
            throw new RuntimeException(String.format("%d differs from %d", this.cols, m.cols));

        Matrix return_matrix = new Matrix(this.rows, this.cols);

        for(int i=0; i<this.data.length; i++)
            return_matrix.data[i] = this.data[i] * m.data[i];

        return return_matrix;
    }

    Matrix div(Matrix m){
        if(this.rows != m.rows)
            throw new RuntimeException(String.format("%d differs from %d", this.rows, m.rows));
        if(this.cols != m.cols)
            throw new RuntimeException(String.format("%d differs from %d", this.cols, m.cols));

        Matrix return_matrix = new Matrix(this.rows, this.cols);

        for(int i=0; i<this.data.length; i++)
            return_matrix.data[i] = this.data[i] / m.data[i];

        return return_matrix;
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();

        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++)
                m.set(i, j, r.nextDouble());
        }

        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if (i == j)
                    m.set(i, j, 1);
                else
                    m.set(i, j, 0);
            }
        }

        return m;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(var vec : asArray())
            sb.append(Arrays.toString(vec)+"\n");

        return sb.toString();
    }

    private int getLogestCols(double[][] d){
        int longestCols = 0;
        for(int i=0; i<d.length; i++){
            if(d[i].length > longestCols)
                longestCols = d[i].length;
        }
        return longestCols;
    }

    public static void main(String[] args){
        Matrix m = new Matrix(new double[][]{{1,2,3},{5,6},{7,8},{9}});
        System.out.println(m);

       /*System.out.println(m.get(0,0));
       m.set(0,0, 8.0);
       System.out.println(m);

        m.reshape(2,8);
        System.out.println(m);

        m.reshape(4,4);
        System.out.println(m);

        int[] dimensions = m.shape();
        System.out.println(Arrays.toString(dimensions));

        Matrix return_matrix_add = m.add(new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}}));
        System.out.println(return_matrix_add);
        Matrix return_matrix_sub = m.sub(new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}}));
        Matrix return_matrix_mul = m.mul(new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}}));
        Matrix return_matrix_div = m.div(new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}}));

        Matrix return_matrix_dot = m.dot(new Matrix(new double[][] {{1},{5,6},{7,8}}));
        System.out.println(return_matrix_dot);

        var m_test = m.sub(new Matrix(new double[][]{{1,2,3},{5,6},{7,8},{9}}));
        System.out.println(m_test.frobenius());

        Matrix r = Matrix.random(2,3);
        System.out.println(r);*/

        Matrix e = Matrix.eye(3);
        System.out.println(e);
    }
}