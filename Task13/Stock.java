// Comment -Good work!
/**
 * class Stock represent a stock of FoodItems.
 *
 * @author Barr Caplan
 * @version 2020a 30/12/19
 */
public class Stock
{
    private FoodItem[] _stock;
    private int _noOfItems;
    private final int MAXSTOCK = 100;

    /**
     * creats a new FoodItem array
     * 
     * @param stock name of the array
     * @param noOfItems nunmber of items in the array
     */
    public Stock()
    {
        _stock = new FoodItem[MAXSTOCK];
        _noOfItems = 0;
    }

    /**
     * gets number of items in the stock
     * 
     * @return number of items in stock
     */
    public int getNumOfItems()
    {
        return _noOfItems;
    }

    /**
     * adds an item to the stock
     * 
     * @param newItem item to add to stock
     * 
     * @return true if item added to stock
     */
    public boolean addItem(FoodItem newItem)
    {
        if (_noOfItems == 0) //if array is empty adds the new item at the first slot
        {
            _stock[0] = new FoodItem(newItem);
            _noOfItems ++;
            return true;
        }

        for (int i = 0; i < _noOfItems; i++) // updates quantity if item exists in array
        {
            if (_stock[i].equals(newItem))
            {
                _stock[i].setQuantity(_stock[i].getQuantity() + newItem.getQuantity());
                return true;
            }
        }

        if (_noOfItems == MAXSTOCK) // if item doens't exist in array and stock is full
            return false;

        // adds the new item at the end of array
        FoodItem temp;
        _noOfItems ++; // will be >=2
        _stock[_noOfItems - 1] = new FoodItem(newItem);
        
        for (int i = _noOfItems - 1; i > 0  && _stock[i].getCatalogueNumber() <= _stock[i-1].getCatalogueNumber(); i--)
        {
            
            { //moves the new item toward the beginning of the array until reaching a smaller cat number
                temp = _stock[i-1];
                _stock[i-1] = _stock[i];
                _stock[i] = temp;
            }
        }
        
        return true;
    }

    /**
     * creates a list of items that has lower quantity than a given number
     * 
     * @param amount number of desired quantity
     * 
     * @return String with item names that needs to be ordered
     */
    public String order(int amount)
    {
        String str = "";
        int qty, skip;

        for (int i = 0; i < _noOfItems; i++) //goes over the whole array
        {
            qty = _stock[i].getQuantity();
            skip = 0;

            for (int j = i + 1; j <_noOfItems; j++) 
            {//items with same catNum&name sits next to eachother, sums next equals items and skips them at the main loop
                if (_stock[i].getCatalogueNumber() == _stock[j].getCatalogueNumber())
                {
                    qty += _stock[j].getQuantity();
                    skip ++;
                }
            }

            if (qty < amount)
            {
                if (str.equals(""))
                    str += _stock[i].getName();
                else
                    str += ", " + _stock[i].getName();
            }

            i += skip;
        } 

        return str;
    }

    /**
     * calculates how many items in the stock can be moved to fridge with given temperature
     * 
     * @param temp friedge temperature
     * 
     * @return number of items that can be moved
     */
    public int howMany(int temp)
    {
        int amount = 0;
        for (int i = 0; i < _noOfItems; i++)
        {
            if (temp >= _stock[i].getMinTemperature() && temp <= _stock[i].getMaxTemperature())
                amount += _stock[i].getQuantity();
        }
        return amount;  
    }

    /**
     * removes all items that are expired at a given date
     * 
     * @param d date to determain what items are exprired and needs to be removed
     * 
     */
    public void removeAfterDate(Date d)
    {
        for (int i = 0; i < _noOfItems; i++)
            if (_stock[i].getExpiryDate().before(d))
            {
                removeIndex(i);
                i --;
            }
    }

    /**
     * gets the mose expansive item in stock
     * 
     * @return the most expansive FoodItem in stock
     */
    public FoodItem mostExpensive()
    {
        if (_noOfItems == 0)
            return null;

        FoodItem item = new FoodItem(_stock[0]);
        for (int i = 0; i < _noOfItems - 1; i++)
        {
            if (_stock[i+1].getPrice() > _stock[i].getPrice())
                item = new FoodItem(_stock[i+1]);
        }

        return item;
    }

    /**
     * gets how many units are in stock
     * 
     * @return number of units in stock
     */
    public int howManyPieces()
    {
        int amount = 0;
        for (int i = 0; i < _noOfItems; i++)
            amount += _stock[i].getQuantity();

        return amount;
    }

    /**
     * returns a String that represent all the items in stock
     * 
     * @return String that represents all the FoodItems in stock in FoodItem's toString format, seperated by line breaks
     */
    public String toString()
    {
        String str = "";
        for (int i = 0; i < _noOfItems; i++)
            str += _stock[i] + "\n";

        return str;
    }

    /**
     * update stock by a list of sold items
     * 
     * @param itemsList a list of item names that were sold
     */
    public void updateStock(String[] itemsList)
    {
        boolean notRemoved;

        for (int i = 0; i < itemsList.length; i++) // runs over the "items to delete" array
        {
            notRemoved = true;
            for (int j = 0; j < _noOfItems && notRemoved; j++) // runs over the stock array
            {
                if (itemsList[i].equals(_stock[j].getName()))
                {
                    _stock[j].setQuantity(_stock[j].getQuantity() - 1);
                    if (_stock[j].getQuantity() == 0)
                        removeIndex(j);
                    notRemoved = false;
                }
            }
        }
    }

    /**
     * gets minimal fridge temperature that can store all the items in stock
     * 
     * @return minimal fridge temperature
     */
    public int getTempOfStock()
    {
        int neededTemp = Integer.MAX_VALUE;

        if (_noOfItems == 0)
            return neededTemp;

        int minTemp = _stock[0].getMinTemperature();
        int maxTemp = _stock[0].getMaxTemperature();
        for (int i = 1; i < _noOfItems; i++)
        {
            if (_stock[i].getMinTemperature() > minTemp)
                minTemp = _stock[i].getMinTemperature();
            if (_stock[i].getMaxTemperature() < maxTemp)
                maxTemp = _stock[i].getMaxTemperature();
        }

        if (minTemp <= maxTemp)
            neededTemp = minTemp;

        return neededTemp;
    }

    private void removeIndex(int index) // note: array should have at least one item. if not, _noOfItems will get -1
    {
        FoodItem temp;
        for (int i = index; i < _noOfItems - 1; i++) //moves item in given index to end of array
        {
            temp = _stock[i];
            _stock[i] = _stock[i+1];
            _stock[i+1] = temp;
        }

        //deletes the last item in array and updates array counter
        _stock[_noOfItems-1] = null;
        _noOfItems --;
    }
}
