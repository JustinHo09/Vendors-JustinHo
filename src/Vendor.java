import java.util.HashMap;


/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vendor {
    private static HashMap<String, Item> Stock = new HashMap<String,Item>();
    private double balance;

    Vendor(int numCandy, int numGum) {
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Gum", new Item(.5, numGum));
        this.balance = 0;
    }

    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }

    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        if(amt >0) {
            this.balance = this.balance + amt;
        }
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
            }
            else
                System.out.println("Gimme more money");
        }
        else System.out.println("Sorry, don't know that item");
    }

    /**
     * Retrieves the stock of the input item.
     * @param item The name of the item to check the stock for.
     * @return 0 the number of that item in stock, 0 if out of stock, and
     * if item is not found/carried by the vendor.
     */
    int getStock (String item){
        if(Stock.containsKey(item)) {
            return Stock.get(item).getStock();
        }
        return 0;
    }

    /**
     * Clears the stock leaving it empty.
     */
    void emptyVendor(){
        Stock.clear();
    }

    /**
     * This will check to see if the item is even carried by the vendor.
     * @param item The name of the item to check if carried.
     * @return True if the item is carried by the vendor False if not.
     */
    boolean hasItem(String item){
        return Stock.containsKey(item);
    }

    /**
     * Restocks the specified item by the specified amount, if the item
     * does not exist then it will create it as a new item in the stock.
     * Items will not be restocked if the quantity is not greater than 0 or if the
     * price is less than 0.
     * @param item The item to restock.
     * @param quantity The amount to restock the item by.
     */
    void restock(String item, int quantity, double price){
        if(Stock.containsKey(item) && quantity > 0 && price >= 0) {
            Stock.get(item).restock(quantity);
        } else if( !Stock.containsKey(item) && quantity > 0 && price > 0) {
            Stock.put(item, new Item(price, quantity));
        }
    }

    /**
     * Returns the price of the inputted item, if it exists.
     * @param item The item to get the price for.
     * @return double indicating the price of the item, 0.0 if the item does not exist.
     */
    double getPrice(String item){
        if(Stock.containsKey(item)) {
            return Stock.get(item).getPrice();
        }else{
            return 0.0;
        }
    }
}

class Examples {
}

