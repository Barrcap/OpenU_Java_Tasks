
/**
 * This class represents a FoodItem object.
 *
 * @author Barr Caplan
 * @version 2020a 14/12/19
 */
public class FoodItem
{
    private String _name;
    private long _catalogueNumber;
    private int _quantity;
    private Date _productionDate;
    private Date _expiryDate;
    private int _minTemperature;
    private int _maxTemperature;
    private int _price;

    final String DEFAULTNAME = "item";
    final long CATMIN = 1000;
    final long CATMAX = 9999;
    final long DEFAULTCAT = 9999;
    final int DEFAULTQTY = 0;
    final int DEFAULTPRICE = 1;

    /**
     * creates a new FoodItem object
     * 
     * @param name name of food item
     * @param catalogueNumber catalogue number of food item
     * @param quantity quantity of food item
     * @param productionDate production date
     * @param expiryDate expiry date
     * @param minTemperature minimum storage temperature
     * @param maxTemperature maximum storage temperature
     * @param price unit price
     */
    public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate,
    int minTemperature, int maxTemperature, int price)
    {
        if (name.length() > 0)
            _name = new String(name);
        else
            _name = DEFAULTNAME;

        if (catalogueNumber >= CATMIN && catalogueNumber <= CATMAX)
            _catalogueNumber = catalogueNumber;
        else
            _catalogueNumber = DEFAULTCAT;

        if (quantity < 0)
            _quantity = DEFAULTQTY;
        else
            _quantity = quantity;

        _productionDate = new Date(productionDate);

        if (expiryDate.before(productionDate))
            _expiryDate = new Date(productionDate.tomorrow());
        else
            _expiryDate = new Date(expiryDate);

        if (minTemperature < maxTemperature)
        {
            _minTemperature = minTemperature;
            _maxTemperature = maxTemperature;
        }
        else
        {
            _minTemperature = maxTemperature;
            _maxTemperature = minTemperature;
        }

        if (price > 0)
            _price = price;
        else
            _price = DEFAULTPRICE;
    }

    /**
     * copy constructor
     * 
     * @param other the food item to be copied
     */
    public FoodItem(FoodItem other)
    {
        _name = other.getName();
        _catalogueNumber = other.getCatalogueNumber();
        _quantity = other.getQuantity();
        _productionDate = new Date(other.getProductionDate());
        _expiryDate = new Date(other.getExpiryDate());
        _minTemperature = other.getMinTemperature();
        _maxTemperature = other.getMaxTemperature();
        _price = other.getPrice();
    }

    /**
     * check if 2 food items are the same (excluding the quantity values)
     * 
     * @param other the food item to compare this food item to
     * 
     * @return true if the food items are the same
     */
    public boolean equals(FoodItem other)
    {
        if (
        _name.equals(other.getName()) &&
        other.getCatalogueNumber() == _catalogueNumber &&
        _productionDate.equals(other.getProductionDate()) &&
        _expiryDate.equals(other.getExpiryDate()) &&
        other.getMinTemperature() == _minTemperature &&
        other.getMaxTemperature() == _maxTemperature &&
        other.getPrice() == _price)
            return true;
        else
            return false;
    }

    /**
     * check if this food item is fresh on the date d
     * 
     * @param d date to check
     * 
     * @return true if this food item is fresh on the date d
     */
    public boolean isFresh(Date d)
    {
        if (!d.before(_productionDate) && !d.after(_expiryDate))
            return true;
        else
            return false;
    }

    /**
     * returns a String that represents this food item
     * 
     * @return String that represents this food item in the following format:
    FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3
     */
    public String toString()
    {
        return "FoodItem: "+_name+"\tCatalogueNumber: "+_catalogueNumber+"\tProductionDate: "+_productionDate.toString()+
        "\tExpiryDate: "+_expiryDate.toString()+"\tQuantity: "+_quantity;
    }

    /**
     * check if this food item is older than other food item
     * 
     * @param other food item to compare this food item to
     * 
     * @return true if this food item is older than other date
     */
    public boolean olderFoodItem(FoodItem other)
    {
        if (_productionDate.before(other.getProductionDate()))
            return true;
        else
            return false;
    }

    /**
     * returns the number of units of products that can be purchased for a given amount
     * 
     * @param amount amount to purchase
     * 
     * @return the number of units can be purchased
     */
    public int howManyItems(int amount)
    {
        amount = amount / _price;
        if (_quantity < amount)
            amount = _quantity;
        return amount;
    }

    /**
     * check if this food item is cheaper than other food item
     * 
     * @param other food item to compare this food item to
     * 
     * @return true if this food item is cheaper than other date
     */
    public boolean isCheaper(FoodItem other)
    {
        if (_price < other.getPrice())
            return true;
        else
            return false;
    }

    /**
     * gets the name
     * 
     * @return the name
     */
    public String getName()
    {
        return new String(_name);
    }

    /**
     * gets the catalogue number
     * 
     * @return the catalogue number
     */
    public long getCatalogueNumber()
    {
        return _catalogueNumber;
    }

    /**
     * gets the quantity
     * 
     * @return the quantity
     */
    public int getQuantity()
    {
        return _quantity;
    }

    /**
     * gets the production date
     * 
     * @return the production date
     */
    public Date getProductionDate()
    {
        return new Date(_productionDate);
    }

    /**
     * gets the expiry date
     * 
     * @return the expiry date
     */
    public Date getExpiryDate()
    {
        return new Date(_expiryDate);
    }

    /**
     * gets the minimum storage temperature
     * 
     * @return the minimum storage temperature
     */
    public int getMinTemperature()
    {
        return _minTemperature;
    }

    /**
     * gets the maximum storage temperature
     * 
     * @return gets the maximum storage temperature
     */
    public int getMaxTemperature()
    {
        return _maxTemperature;
    }

    /**
     * gets the unit price
     * 
     * @return the unit price
     */
    public int getPrice()
    {
        return _price;
    }

    /**
     * set the expiry date (only if not before production date )
     * 
     * @param expiryDate expiry date value to be set
     */
    public void setExpiryDate(Date expiryDate)
    {
        if (expiryDate.after(_productionDate))
            _expiryDate = new Date(expiryDate);
    }

    /**
     * set the production date (only if not after expiry date )
     * 
     * @param productionDate - production date value to be set
     */
    public void setProductionDate(Date productionDate)
    {
        if (productionDate.before(_expiryDate))
            _productionDate = new Date(productionDate);
    }

    /**
     * set the price (only if positive)
     * 
     * @param price price value to be set
     */
    public void setPrice(int price)
    {
        if (price > 0)
            _price = price;
    }

    /**
     * set the quantity (only if not negative)
     * 
     * @param quantity the quantity value to be set
     */
    public void setQuantity(int quantity)
    {
        if (quantity >= 0)
            _quantity = quantity;
    }
}
// Comment -   -2 points
 // 3. Testing accessors and mutators:

	 // ERROR in setProductionDate() - if productionDate is 22 / 9 / 2019 and is set to 22 / 9 / 2020, the same as the expiry date , then the result should be 22/09/2020 but student's result is 22/09/2019

	 // ERROR in setExpiryDate() - if expiryDate is 22 / 9 / 2020 and is set to 22 / 9 / 2019, the same as the production date , then the result should be 22/09/2019 but student's result is 22/09/2020