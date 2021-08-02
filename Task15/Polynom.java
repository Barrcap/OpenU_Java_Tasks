// Comment -Good work!
/**
 * Class Polynom represents a polynom.
 *
 * @author Barr Caplan
 * @version 08/02/2020
 */
public class Polynom
{
    PolyNode _head;

    
    /**
     * Polynom Constructor creates an epmty polynom
     * Time complexity O(1), Storage complexity O(1)
     * 
     */
    public Polynom()
    {
        _head = null;
    }

    /**
     * Polynom Constructor creates a polynom with given polynode as a head
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param p head of the new polymon
     */
    public Polynom(PolyNode p)
    {
        _head = p;
    }

    /**
     * Method getHead retruns the head
     * Time complexity O(1), Storage complexity O(1)
     *
     * @return The head
     */
    public PolyNode getHead()
    {
        return _head;
    }

    /**
     * Method setHead sets the head
     * Time complexity O(1), Storage complexity O(1)
     *
     * @param p PolyNode to set as head
     */
    public void setHead (PolyNode p)
    {
        _head = p;
    }

    /**
     * Method addNode adds a PolyNode to the Polynom
     * Time complexity O(n), Storage complexity O(1)
     *
     * @param p A PolyNode
     * @return The polynom
     */
    public Polynom addNode(PolyNode p)
    {
        if (p == null)
            return this;

        if (p.getCoefficient() == 0)
            return this;

        PolyNode curr = _head;

        if (curr == null)
        {
            _head = p;
            return this;
        }

        PolyNode prev = null;

        while (curr.getPower() > p.getPower())
        {
            if (curr.getNext() == null)
            {
                curr.setNext(p);
                return this;
            }
            prev = curr;
            curr = curr.getNext();
        }

        if (curr.getPower() == p.getPower())
        {
            curr.setCoefficient(curr.getCoefficient()+p.getCoefficient());
            if (curr.getCoefficient() == 0) 
            {
                if (prev == null)
                    _head = curr.getNext();
                else
                    prev.setNext(curr.getNext());
            }
        }
        else
        {
            p.setNext(curr);
            if (prev == null)
                _head = p;
            else
                prev.setNext(p);
        }

        return this;
    }

    /**
     * Method multByScalar multiples the polynom by a scalar
     * Time complexity O(n), Storage complexity O(1)
     *
     * @param num A scalar
     * @return The polymon
     */
    public Polynom multByScalar(int num)
    {
        if (num == 0)
            _head = null;
        PolyNode curr = _head;

        while (curr != null)
        {
            curr.setCoefficient(curr.getCoefficient()*num);
            curr = curr.getNext();
        }

        return this;
    }

    /**
     * Method addPol adds together two polynoms
     * Time complexity O(m*n), Storage complexity O(1)
     *
     * @param other A polynom to add
     * @return The polynom after adding
     */
    public Polynom addPol(Polynom other)
    {
        if (other == null)
            return this;

        PolyNode curr = other._head;
        PolyNode temp;
        while (curr != null)
        {
            temp = new PolyNode(curr);
            temp.setNext(null);
            this.addNode(temp);
            curr = curr.getNext();
        }

        return this;
    }

    /**
     * Method multPol multiple polymon by another given polynom
     *
     * @param other A polynom to mulyple with
     * @return The polynom after multipication
     */
    public Polynom multPol(Polynom other)
    {
        if (other == null)
            return this;

        Polynom multPol = new Polynom();
        PolyNode multNod = new PolyNode(0,9);

        PolyNode currThis = _head;
        while (currThis != null)
        {
            PolyNode currOther = other._head;
            while (currOther != null)
            {
                multNod.setCoefficient(currOther.getCoefficient() * currThis.getCoefficient());
                multNod.setPower(currOther.getPower() + currThis.getPower());
                if (multNod.getCoefficient() != 0)
                    multPol.addNode(new PolyNode(multNod));

                currOther = currOther.getNext();
            }

            currThis = currThis.getNext();
        }

        if (other._head != null)
            _head = multPol.getHead();
        return this;
    }

    /**
     * Method differential calculate the diffrential of a polynom
     * Time complexity O(n), Storage complexity O(1)
     *
     * @return The polynom
     */
    public Polynom differential()
    {
        PolyNode curr = _head;
        PolyNode prev = null;

        while (curr != null)
        {
            curr.setCoefficient(curr.getCoefficient() * curr.getPower());
            curr.setPower(curr.getPower() - 1);

            if (curr.getCoefficient() == 0)
            {
                if (prev == null)
                    _head = curr.getNext();
                else
                    prev.setNext(curr.getNext());
            }
            prev = curr;
            curr = curr.getNext();
        }

        return this;
    }

    /**
     * Method toString create a string that represents the polynom
     * Time complexity O(n), Storage complexity O(1)
     *
     * @return The polynom
     */
    public String toString()
    {
        String str = "";
        PolyNode curr = _head;

        if (curr != null)
        {
            while (curr != null && curr.getCoefficient() == 0)
                curr = curr.getNext();
            str += curr;
            curr = curr.getNext();
        }

        while (curr != null)
        {
            if (curr.getCoefficient() > 0)
                str += "+";
            str += curr;
            curr = curr.getNext();
        }

        return str;
    }
}
