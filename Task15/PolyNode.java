// Comment - Good work!
/**
 * Class PolyNode represents a polynom's element.
 *
 * @author Barr Caplan
 * @version 08/02/2020
 */
public class PolyNode
{
    int _power;
    double _coefficient;
    PolyNode _next;

    /**
     * PolyNode Constructor creates a PolyNode
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param power Integer taht represents the power
     * @param Double that represent the coefficient
     */
    public PolyNode(int power, double coefficient)
    {
        if (power < 0)
        {
            power = 0;
            coefficient = 0;
        }

        _power = power;
        _coefficient = coefficient;
        _next = null;
    }

    /**
     * PolyNode Constructor creates a PolyNode
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param power Integer taht represents the power
     * @param Double that represent the coefficient
     * @param next The next PolyNode
     */
    public PolyNode(int power, double coefficient, PolyNode next)
    {
        this(power, coefficient);
        _next = next;
    }

    /**
     * PolyNode Copy Constructor
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param next A parameter
     */
    public PolyNode(PolyNode next)
    {
        _power = next._power;
        _coefficient = next._coefficient;
        _next = next._next;
    }

    /**
     * Method getPower gets the power of the PolyNode
     * Time complexity O(1), Storage complexity O(1)
     *
     * @return The power
     */
    public int getPower()
    {
        return _power;
    }

    /**
     * Method getCoefficient gets the coefficient of the PolyNode
     * Time complexity O(1), Storage complexity O(1)
     *
     * @return The coefficient
     */
    public double getCoefficient()
    {
        return _coefficient;
    }

    /**
     * Method getNext gets the next PolyNode in the chain
     * Time complexity O(1), Storage complexity O(1)
     *
     * @return The next PolyNode
     */
    public PolyNode getNext()
    {
        return _next;
    }

    /**
     * Method setPower sets the power
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param power Power to set
     */
    public void setPower(int power)
    {
        if (power >= 0)
            _power = power;
    }

    /**
     * Method setCoefficient sets the coefficient
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param coefficient Coefficient to set
     */
    public void setCoefficient(double coefficient)
    {
        _coefficient = coefficient;
    }

    /**
     * Method setNext sets the next PolyNode in the chain
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param next PolyNode to set as next
     */
    public void setNext(PolyNode next)
    {
        _next = next;
    }

    /**
     * Method toString creates a string that represents the PolyNode
     * Time complexity O(1), Storage complexity O(1)
     *
     * @return String
     */
    public String toString()
    {
        String str = "";

        if (_coefficient == 0)
            return "";

        if (_power == 0)
            return Double.toString(_coefficient);

        if (_coefficient < 0) //adds "-" if coefficient is negative
            str += "-";

        if (Math.abs(_coefficient) != 1) //if coefficient's abs isn't 1, it will be added to  string
            str += Double.toString(Math.abs(_coefficient));

        str += "x";

        if (_power > 1) // adding power if it is not 0 or 1
            str += "^" + _power;

        return str;
    }
}
