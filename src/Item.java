class Item {
    double price;
    int stock;
    int purchaseCount;
    String description;
    String category;
    boolean bestseller;

    /**
     * Class for an item, representing an item for a vendor
     * @param price The cost of the item.
     * @param numPieces The number of pieces/stock of the item
     */
    Item(double price, int numPieces, String description, String category) {
        this.price = price;
        this.stock = numPieces;
        this.purchaseCount = 0;
        this.description = description;
        this.category = category;
        this.bestseller = false;
    }

    /**
     * Retrieves an item's description.
     * @return String which is the item's description
     */
    String getDescription(){
        return description;
    }

    /**
     * Sets discount the price of the item by the input amount
     * @param percentageOff
     */
    void setDiscount(double percentageOff){
        if(percentageOff > 0 && percentageOff < 1) {
            this.price = this.price * percentageOff;
        }
    }

    /**
     * This method sets a item as a bestseller or not
     * @param bestseller Status of it being a bell seller or not.
     */
    void setBestseller(boolean bestseller){
        this.bestseller = bestseller;
    }

    /**
     * Retrieves the bestseller status of the iem
     * @return True if it is a bestseller False if not
     */
    boolean  getBestseller(){
        return this.bestseller;
    }

    /**
     * This retrieves the category of the item which may be one or a String with many categories
     * @return String representing the category or categories of the item.
     */
    String getCategory(){
        return category;
    }

    /**
     * Adds that input amount to the item's stock.
     * @param amount The amount to increase the item's stock by.
     */
    void restock(int amount) {
        if(amount > 0) {
            this.stock = this.stock + amount;
        }
    }

    /**
     * Simulates a purchase by reducing this item's stock by the input amount.
     * @param amount The amount to reduce the item's stock by.
     */
    void purchase(int amount) {
        this.stock = this.stock - amount;
        purchaseCount++;
    }

    /**
     * Retrieves the stock of the item.
     * @return Integer representing the item's stock.
     */
    int getStock(){
        return this.stock;
    }

    /**
     * Returns the price of the item
     * @return Double representing the item's price
     */
    double getPrice(){
        return this.price;
    }

    /**
     * Retrieves the count for how many times this item has been purchased.
     * @return An integer representing how many times this item has been purchased.
     */
    int getPurchaseCount(){
        return this.purchaseCount;
    }
}