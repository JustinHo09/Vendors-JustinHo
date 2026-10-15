class Item {
    double price;
    int stock;

    /**
     * Class for an item, representing an item for a vendor
     * @param price The cost of the item.
     * @param numPieces The number of pieces/stock of the item
     */
    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
    }

    /**
     * Adds that input amount to the item's stock.
     * @param amount The amount to increase the item's stock by.
     */
    void restock(int amount) {
        this.stock = this.stock + amount;
    }

    /**
     * Simulates a purchase by reducing this item's stock by the input amount.
     * @param amount The amount to reduce the item's stock by.
     */
    void purchase(int amount) {
        this.stock = this.stock - amount;
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
}