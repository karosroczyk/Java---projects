import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @org.junit.jupiter.api.Test
    void asArray() {
        double[] inputRow0 = new double[]{1,2,0};
        double[] inputRow1 = new double[]{3,4};

        double[] expectedRow0 = new double[]{1,2};
        double[] expectedRow1 = new double[]{3,4};

        Matrix m = new Matrix(new double[][]{inputRow0,inputRow1});
        double[][] result_matrix = m.asArray();

        assertArrayEquals(result_matrix[0], expectedRow0);
        assertArrayEquals(result_matrix[1], expectedRow1, "Print message if wrong");
    }

    @org.junit.jupiter.api.Test
    void asArrayWithZeros() {
        double[] inputRow0 = new double[]{1,2,0};
        double[] inputRow1 = new double[]{3,4};

        double[] expectedRow0 = new double[]{1,2,0};
        double[] expectedRow1 = new double[]{3,4,0};

        Matrix m = new Matrix(new double[][]{inputRow0,inputRow1});
        double[][] result_matrix = m.asArrayWithZeros();

        assertArrayEquals(result_matrix[0], expectedRow0);
        assertArrayEquals(result_matrix[1], expectedRow1, "Print message if wrong");
    }

    @org.junit.jupiter.api.Test
    void frobenius() {
        Matrix expected = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix diff = expected.sub(new Matrix(new double[][]{{1,2},{3,4}}));
        double err = diff.frobenius();
        assertEquals(err,0);
    }

    @org.junit.jupiter.api.Test
    void get() {
        Matrix expected = new Matrix(new double[][]{{1,2},{3,4}});

        assertEquals(expected.get(0,0), 1);
        assertThrows(RuntimeException.class, () -> {
            expected.get(expected.rows, 0);
        });

        assertThrows(RuntimeException.class, () -> {
            expected.get(0,expected.cols+1);
        });
    }

    @org.junit.jupiter.api.Test
    void set() {
        Matrix expected = new Matrix(new double[][]{{1,2},{3,4}});
        expected.set(0,0, 8);

        assertEquals(expected.get(0,0), 8);
        assertThrows(RuntimeException.class, () -> {
            expected.set(expected.rows, 0, 8);
        });

        assertThrows(RuntimeException.class, () -> {
            expected.set(0,expected.cols+1, 8);
        });
    }

    @org.junit.jupiter.api.Test
    void reshape() {
        double[] inputRow0 = new double[]{1,2};
        double[] inputRow1 = new double[]{3,4};

        double[] expectedRow0 = new double[]{1,2,3,4};

        Matrix m = new Matrix(new double[][]{inputRow0,inputRow1});

        assertArrayEquals(m.shape(), new int[]{2,2});

        m.reshape(1,4);
        double[][] returnMatrix = m.asArray();

        assertArrayEquals(returnMatrix[0], expectedRow0);
        assertArrayEquals(m.shape(), new int[]{1,4});
    }

    @org.junit.jupiter.api.Test
    void add() {
        double[] inputRow0 = new double[]{1,2};
        double[] inputRow1 = new double[]{3,4};

        Matrix m1 = new Matrix(new double[][]{inputRow0,inputRow1});
        Matrix m2 = new Matrix(new double[][]{inputRow0,inputRow1});

        Matrix returnMatrix = m1.add(m2);

        assertEquals( returnMatrix.get(0,0),m1.get(0,0) + m2.get(0,0) );
    }

    @org.junit.jupiter.api.Test
    void sub() {
        double[] inputRow0 = new double[]{1,2};
        double[] inputRow1 = new double[]{3,4};

        Matrix m1 = new Matrix(new double[][]{inputRow0,inputRow1});
        Matrix m2 = new Matrix(new double[][]{inputRow0,inputRow1});

        Matrix returnMatrix = m1.sub(m2);

        assertEquals( returnMatrix.get(0,0),m1.get(0,0) - m2.get(0,0) );
        assertEquals(returnMatrix.frobenius(), 0);
    }

    @org.junit.jupiter.api.Test
    void mul() {
        double[] inputRow0 = new double[]{1,2};
        double[] inputRow1 = new double[]{3,4};

        Matrix m1 = new Matrix(new double[][]{inputRow0,inputRow1});
        Matrix m2 = new Matrix(new double[][]{inputRow0,inputRow1});

        Matrix returnMatrix = m1.mul(m2);

        assertEquals( returnMatrix.get(0,0),m1.get(0,0) * m2.get(0,0) );
    }

    @org.junit.jupiter.api.Test
    void div() {
        double[] inputRow0 = new double[]{1, 2};
        double[] inputRow1 = new double[]{3, 4};

        Matrix m1 = new Matrix(new double[][]{inputRow0, inputRow1});
        Matrix m2 = new Matrix(new double[][]{inputRow0, inputRow1});

        Matrix returnMatrix = m1.div(m2);

        assertEquals(returnMatrix.get(0, 0), m1.get(0, 0) / m2.get(0, 0));
        assertEquals(returnMatrix.frobenius(), Math.sqrt(returnMatrix.rows * returnMatrix.cols));
        assertThrows(RuntimeException.class, () -> {
            m1.div(new Matrix(new double[][]{{}}));
        });
    }

    @org.junit.jupiter.api.Test
    void dot() {
        double[] inputRow0 = new double[]{1,2};
        double[] inputRow1 = new double[]{3,4};

        double[] expectedRow0 = new double[]{7,10};
        double[] expectedRow1 = new double[]{15,22};

        Matrix m1 = new Matrix(new double[][]{inputRow0,inputRow1});
        Matrix m2 = new Matrix(new double[][]{inputRow0,inputRow1});

        Matrix returnMatrix = m1.dot(m2);
        double[][] returnArray = returnMatrix.asArray();
        assertArrayEquals( returnArray[0], expectedRow0 );
        assertArrayEquals( returnArray[1], expectedRow1 );
    }

    @org.junit.jupiter.api.Test
    void dotIrrregularShape() {
        double[] inputRow0 = new double[]{1,2,3};
        double[] inputRow1 = new double[]{3,4};

        double[] inputRow2 = new double[]{1,2};
        double[] inputRow3 = new double[]{3,4};
        double[] inputRow4 = new double[]{1};

        double[] expectedRow0 = new double[]{10,10};
        double[] expectedRow1 = new double[]{15, 22};

        Matrix m1 = new Matrix(new double[][]{inputRow0,inputRow1});
        Matrix m2 = new Matrix(new double[][]{inputRow2,inputRow3,inputRow4});

        Matrix returnMatrix = m1.dot(m2);
        double[][] returnArray = returnMatrix.asArray();
        assertArrayEquals( returnArray[0], expectedRow0 );
        assertArrayEquals( returnArray[1], expectedRow1 );
    }

    @org.junit.jupiter.api.Test
    void eye() {
        int n = 4;
        Matrix m = Matrix.eye(n);
        assertEquals(m.frobenius(), Math.sqrt(n));
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        double[] inputRow0 = new double[]{1,2,3};
        double[] inputRow1 = new double[]{3,4,0};
        Matrix m1 = new Matrix(new double[][]{inputRow0,inputRow1});

        String m1String = m1.toString();

        assertEquals(m1String.indexOf("]"), 14);
        assertEquals(m1String.lastIndexOf("]"), 25);
    }
}