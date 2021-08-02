// Comment - Good work!
/**
 * 
 * @author Barr Caplan
 * @version version 2020a 31/01/2020
 */
public class Ex14
{
    /**
     * Method subStrC counts substrings that start, end, and contain with a given character
     *
     * @param s Given string
     * @param c Given character
     * @return Number of substrings
     */
    public static int subStrC(String s, char c)
    {// Time complexity O(n), Storage complexity O(1)
        int charCount = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                charCount ++;

        if (charCount < 3)
            return 0;
        return charCount - 2;
    }

    /**
     * Method subStrMaxC counts substrings that start, ends, and contain up to k numbers of given character
     *
     * @param s Given string
     * @param c Given character
     * @param k Un-negative number
     * @return Number of substrings
     */
    public static int subStrMaxC(String s, char c, int k)
    {// Time complexity O(n), Storage complexity O(1)
        int charCount = 0;
        int strCount = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                charCount ++;

        if (charCount < 2)
            return 0;

        for (int i = charCount - 1, j = 0; i >=0 && j <= k; i--, j++)
            strCount += i;

        return strCount;
    }

    /**
     * Method zeroDistance updates each cell to contain the distance from closest 0
     *
     * @param a An array that contains 1's and 0's
     */
    public static void zeroDistance(int[] a)
    {// Time complexity O(n), Storage complexity O(1)
        int counter = Integer.MAX_VALUE;

        for (int i = 0; i < a.length; i++)
        { //scanning array from left to right, count steps from 0 and updates array
            if (a[i] == 0)
                counter = 0;
            a[i] = counter;
            if (counter != Integer.MAX_VALUE) //will start counting only after reseting the counter
                counter ++;
        }

        for (int i = a.length-1; i >= 0; i--)
        { //scanning array from right to left, count steps from 0 and updates array if smaller then previous
            if (a[i] == 0)
                counter = 0;
            if (counter < a[i])
                a[i] = counter;
            counter ++;
        }
    }

    /**
     * Method isTrans determine if t was transformed from s
     *
     * @param s A string
     * @param t A String
     * @return True if y was transformed from s
     */
    public static boolean isTrans(String s, String t)
    {
        if (s.length() == 0 && t.length() == 0) //stop condition
            return true;

        if (s.length() > 0 && t.length() > 0 && s.charAt(0) == t.charAt(0)) 
        {//checks if both strings has at least one character, and if first chararcter is equals
            return isTrans(s.substring(1), t.substring(1)) || //deletes first character from both strings
            ((t.length() > 1 && t.charAt(0) == t.charAt(1)) && isTrans(s, t.substring(1))); //remove duplicate chars
        }
        return false;
    }

    /**
     * Method countPaths counts how many legal paths exist from first cell (0,0) to last one (last line, last column)
     *
     * @param mat Two dimentional array that contain numbers between 1 and 99
     * @return Number of legal paths
     */
    // Comment - your method doesn't work ok for the folloiwng cases -2 points
    //11	10	
    //22	22	
    //expected=1 : student=2
    //************Failed!!!
    //1	0	
    //22	22	

    //************FAILED caught exception in countPaths method.

    //java.lang.StackOverflowError
    public static int countPaths (int [][] mat)
    {
        return countPaths(mat, 0, 0); //caling actual method with starting cordinates
    }

    private static int countPaths(int[][]mat, int x, int y)
    {
        if (x >= mat.length || y >= mat[0].length) //checks if (x,y) got out of array boundry
            return 0;

        if (x == mat.length - 1 && y == mat[0].length - 1) //check if (x,y) are the the desired final cell
            return 1;

        int dig = mat[x][y]%10;
        int tens = mat[x][y]/10;

        return countPaths(mat, x+dig, y+tens) + countPaths(mat, x+tens, y+dig); //calling method for two possible steps
    }
}