// Comment -Good work!
/**
 * class Matrix represent a two dimentional array of int values between 0 and 255.
 *
 * @author Barr Caplan
 * @version 2020a 30/12/19
 */
public class Matrix
{
    private int[][] _matrix;
    private final int MAXVAL = 255;
    
    /**
     * constracts a Matrix from a two dimentional array with the same values
     * 
     * @param array array to copy
     */
    public Matrix(int[][] array)
    {
        _matrix = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[0].length; j++)
                _matrix[i][j] = array[i][j];
    }
    
    /**
     * constracts a size1 by size2 Matrix of zeros
     * 
     * @param size1 number of rows
     * @param size2 number of columns
     */
    public Matrix(int size1, int size2)
    {
        _matrix = new int[size1][size2];
        for (int i = 0; i < size1; i++)
            for (int j = 0; j < size2; j++)
                _matrix[i][j] = 0;
    }

    /**
     * returns a string that represents the Matrix
     * 
     * @return a string with all the the values, elements in the same row are seperated by tab, rows seperated by line break
     */
    public String toString()
    {
        String str = "";
        for (int i = 0; i < _matrix.length; i++)
        {
            for (int j = 0; j < _matrix[0].length; j++)
            {
                if (j > 0)
                    str += "\t";
                str += _matrix[i][j];
            }
            str += "\n";
        }

        return str;
    }

    /**
     * creates a negative picture of the Matrix
     * 
     * @return negative picture Matrix
     */
    public Matrix makeNegative()
    {
        Matrix mat = new Matrix(_matrix);
        for (int i = 0; i < _matrix.length; i++)
            for (int j = 0; j < _matrix[0].length; j++)
                mat._matrix[i][j] = MAXVAL - _matrix[i][j];

        return mat;
    }

    /**
     * creates a smoother picture
     * 
     * @return a smoother picuture
     */
    public Matrix imageFilterAverage()
    {
        Matrix mat = new Matrix(_matrix);
        int[] neighbors = new int[8];
        int counter; // number of neighbors
        int nSum; // sum of all the neighbors
        boolean above, below, left, right; 

        for (int i = 0; i < _matrix.length; i++)
        {
            for (int j = 0; j < _matrix[0].length; j++)
            {
                for (int k = 0; k < neighbors.length; k++) //reset neighbors array
                    neighbors[k] = 0;
                counter = 0;
                nSum = 0;
                above = i > 0; //checks if neighbors above exist (i-1 exist)
                below = i < _matrix.length - 1; //checks if neighbors below exist (i+1 exist)
                left = j > 0; //checks if neighbors to left exist (j-1 exist)
                right = j < _matrix[0].length - 1; // checks if neighbors to right exist (j+1 exist)

                if (above) //i-1 exists
                {
                    neighbors[counter] = _matrix[i-1][j]; //adds neighbor above
                    counter ++;

                    if (left) //j-1 exist as well, adds top left neighbor
                    {
                        neighbors[counter] = _matrix[i-1][j-1];
                        counter ++;
                    }

                    if (right) //j+1 exist as well, adds top right neighbor
                    {
                        neighbors[counter] = _matrix[i-1][j+1];
                        counter ++;
                    }
                } // end of i-1 row check

                if (left) //j-1 exists, adds left neighbor
                {
                    neighbors[counter] = _matrix[i][j-1];
                    counter ++;
                }

                if (right) //j+1 exists, adds right neighbor
                {
                    neighbors[counter] = _matrix[i][j+1];
                    counter ++;
                }

                if (below) //i+1 exists
                {
                    neighbors[counter] = _matrix[i+1][j]; // adds neighbor below
                    counter ++;

                    if (left) //j-1 exists as well, adds bottom left neighbor
                    {
                        neighbors[counter] = _matrix[i+1][j-1];
                        counter ++;
                    }

                    if (right) //j+1 exists as well, adds bottom right neighbor
                    {
                        neighbors[counter] = _matrix[i+1][j+1];
                        counter ++;
                    }
                }

                for (int k = 0; k < neighbors.length; k++)
                    nSum += neighbors[k];

                mat._matrix[i][j] = (_matrix[i][j] + nSum) / (1 + counter);
            } // end of row run, going to next row if exists
        }

        return mat;
    } // end of imageFilterAvarage method

    /**
     * rotates the Matrix clockwise
     * 
     * @return a new roatated Matrix
     */
    public Matrix rotateClockwise()
    {
        Matrix mat = new Matrix(_matrix[0].length, _matrix.length);

        for (int i = 0; i < mat._matrix.length; i++)
            for (int j = 0; j < mat._matrix[0].length; j++)
                mat._matrix[i][j] = _matrix[mat._matrix[0].length-1-j][i];

        return mat;
    }

    /**
     * rotates the Matrix counter clockwise
     * 
     * @return a new roatated Matrix
     */
    public Matrix rotateCounterClockwise()
    {
        Matrix mat = new Matrix(_matrix);

        for (int i = 0; i < 3; i++)
            mat = mat.rotateClockwise();

        return mat;
    }
}
