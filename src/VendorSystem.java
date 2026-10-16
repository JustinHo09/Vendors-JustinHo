import java.util.ArrayList;

/**
 * This represents a Vendor System which can manage 5 vendors.
 */
public class VendorSystem {

    private ArrayList<Vendor> vendors;
    private final int cap = 5;

    public VendorSystem(){
        vendors = new ArrayList<>();
    }

    /**
     * This adds a vendor to the ArrayList of vendors managed.
     * @param vendor The vendor to add to the system
     * @return False if addition was unsuccessful and True if successful
     */
    public boolean addVendor(Vendor vendor){
        if(vendor != null && vendors.size() < cap) {
            vendors.add(vendor);
            return true;
        }
        return false;
    }

    /**
     * Removes a vendor from the managed vendors by index
     * @param index Index of the vendor to remove
     * @return True if the removal was successful False is not
     */
    public boolean removeVendor(int index){
        if (index >= 0 && index < vendors.size()){
            vendors.remove(index);
            return true;
        }
        return false;
    }

    /**
     * This returns and prints the inventory of each vendor
     * @return An ArrayList of Strings with the inventory of each vendor in the system
     */
    public ArrayList<String> printVendorsInventory(){
        ArrayList<String> inventoryStrings = new ArrayList<>();
        for (int i=0; i<vendors.size(); i++){
            System.out.println("Vendor_"+i+": "+vendors.get(i).getInventory());
            inventoryStrings.add("Vendor_"+i+": "+vendors.get(i).getInventory());
        }
        return inventoryStrings;
    }

    /**
     * Retrieves the inventory of the vendor at the given index
     * @param index Index of the vendor to check the inventory of
     * @return String of the inventory or null if not a valid index
     */
    public String getVendorInventory(int index){
        if (index >= 0 && index < vendors.size()){
            return vendors.get(index).getInventory();
        }
        return null;
    }

    /**
     * Retrieves the number of vendors in the system
     * @return The number of vendors in the system
     */
    public int numberOfVendors(){
        return vendors.size();
    }

    /**
     * Retrieves the vendor at the index
     * @param index Index of the vendor to retrieve
     * @return The vendor at the specified index, null if index is out of bounds
     */
    public Vendor getVendor(int index){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index);
        }
        return null;
    }

    /**
     * Removes a specified item from a specified vendor
     * @param index The index of the vednor to remove from
     * @param itemName The name of the item to remove
     * @return True if the removal was in a valid index, False if not
     */
    public boolean removeItemFromVendor(int index, String itemName){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).removeItem(itemName);
            return true;
        }
        return false;
    }

    /**
     * Buys an item from a vendor
     * @param index The index signifying the vendor to purchase from
     * @param itemName The name of the item to buy from them
     * @return True if a purchase was successful, or it was unsuccessful but on a valid Vendor,
     * False if it is not on a valid vendor
     */
    public boolean buyItemFromVendor(int index, String itemName){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).select(itemName);
            return true;
        }
        return false;
    }

    /**
     * Restocks an item using the Vendor restock method
     * @param index The index of the vendor to restock
     * @param itemName The name of the item to restock
     * @param quantity The amount to restock the item by
     * @param price The price of the item to be restocked
     * @param description The description of the item to be restocked
     * @param category The category of the item to be restocked
     * @return False if done on an invalid index for vendors, True otherwise even if it does not restock.
     */
    public boolean restockItemFromVendor(int index, String itemName, int quantity, double price,
                                         String description, String category){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).restock(itemName, quantity, price, description, category);
            return true;
        }
        return false;
    }

    /**
     * Empties the specified vendor
     * @param index The index of the vendor to empty the inventory of
     * @return True if successful or a valid index, False if an invalid index
     */
    public boolean emptyVendor(int index){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).emptyVendor();
            return true;
        }
        return false;
    }

    /**
     * Renames a specified item from a specified vendor
     * @param index Index of the vendor to rename an item from
     * @param itemName Name of the item to change
     * @param newItemName New name of the item to change
     * @return True if index was valid or change was successful, False if not a valid index
     */
    public boolean renameItemFromVendor(int index, String itemName, String newItemName){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).changeName(itemName, newItemName);
            return true;
        }
        return false;

    }

    /**
     * Sets discount of an item
     * @param vendorIndex Index of the vendor to check
     * @param discount The discount
     * @param itemName The name of the item to discount
     * @return True if successful false if not valid or unsuccessful
     */
    public boolean setItemDiscount(int vendorIndex, double discount, String itemName){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).setDiscountItem(discount, itemName);
        }
        return false;
    }

    /**
     * Discounts a category from a vendor
     * @param vendorIndex Index of hte vendor to disount
     * @param discount The discount
     * @param category The category to discount
     * @return True if discount successful, False if not and invalid index
     */
    public boolean setCategoryDiscount(int vendorIndex, double discount,String category){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).setDiscountCategory(discount, category);
        }
        return false;
    }

    /**
     * Gets the cost of an item form a vendor
     * @param vendorIndex index of Vendor to check
     * @param itemName Name ofhte item to get cost
     * @return 0.0 if not valid index, price of the item
     */
    public double getItemCost(int vendorIndex, String itemName){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).getPrice(itemName);
        }
        return 0.0;
    }

    /**
     * Gets category of the item
     * @param vendorIndex Index of the vendor to check
     * @param itemName Name of the item to select
     * @return category of the item null if index not valid
     */
    public String getItemCategory(int vendorIndex, String itemName){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).getItemCategory(itemName);
        }
        return null;
    }

    /**
     * Adds money to a vendor
     * @param index Index of vendor to add
     * @param amount Amount on money to give it
     * @return True f valid index, False if not
     */
    public boolean addMoneyToVendor(int index, double amount){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).addMoney(amount);
            return true;
        }
        return false;
    }

    /**
     * Checks if a vendor has an item
     * @param index Index of vendor to check
     * @param itemName Name of hte item to look for
     * @return True if it has the item, false if not
     */
    public boolean vendorHasItem(int index, String itemName){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index).hasItem(itemName);
        }
        return false;
    }

    /**
     * Checks if a vendor exists
     * @param index Index of vendor to check
     * @return True if valid index false if not
     */
    public boolean vendorExists(int index){
        if(index >= 0 && index < vendors.size()) {
            return true;
        }
        return false;
    }

    /**
     * Gets the stock of an item from a vendor
     * @param index Index of the vendor
     * @param itemName Name of he item to check
     * @return Stock of the item, 0 also if invalid index
     */
    public int getVendorStock(int index,String itemName){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index).getStock(itemName);
        }
        return 0;
    }

    /**
     * Gets tthe balance of a vendor
     * @param index Index of the vendor to check
     * @return Balance of the vendor, 0 is also is not valid index
     */
    public double getVendorBalance(int index){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index).getBalance();
        }
        return 0;
    }

    /**
     * Gets items desciption form a vnedor
     * @param vendorIndex Index of vendor to check
     * @param itemName Name of hte item to get description
     * @return Null is not valid index, String of the item's description
     */
    public String getItemDescription(int vendorIndex, String itemName){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).getItemDescription(itemName);
        }
        return null;
    }

    /**
     * Gets count of all purchases of an item across all vendors
     * @param itemName Name of item to check
     * @return 0 if invalid name or no purchase, number of purchases of that item across vendors
     */
    public int getItemPurchaseCount(String itemName){
        if(itemName == null || itemName.isBlank()){
            return 0;
        }
        int sum=0;
        for(int i=0; i<vendors.size(); i++){
            if(vendors.get(i).hasItem(itemName)){
                sum+=vendors.get(i).getItemPurchaseCount(itemName);
            }
        }
        return sum;
    }

    /**
     * Gets the insight string of the items from the vendors from purchases
     * @return String with the item purchase counts and the most popular
     */
    public String getPurchaseInsights(){
        ArrayList<String> allItems = new ArrayList<>();
        for(int i=0; i<vendors.size(); i++){
            int size = vendors.get(i).getItems().size();
            for(int j=0; j<size; j++){
                if(!allItems.contains(vendors.get(i).getItems().get(j))){
                    allItems.add(vendors.get(i).getItems().get(j));
                }
            }
        }
        if(!allItems.isEmpty()) {
            String purchaseInsights = "";
            int max = -1;
            int maxIndex = 0;
            for (int i = 0; i < allItems.size(); i++) {
                purchaseInsights += allItems.get(i) + ": " + getItemPurchaseCount(allItems.get(i)) + "\n";
                if (getItemPurchaseCount(allItems.get(i)) >= max) {
                    max = getItemPurchaseCount(allItems.get(i));
                    maxIndex = i;
                }
            }
            return purchaseInsights + "Most Popular Item is: " + allItems.get(maxIndex) + "\n";
        }
        return null;
    }

    /**
     * Sets bestselling status of a item from a vendor
     * @param vendorIndex Index of vendor to check
     * @param status Status to chage it to
     * @param itemName Name of item to check
     * @return True if valid index, false if not
     */
    public boolean setBestSelling(int  vendorIndex, boolean status, String itemName){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).setBestSeller(status, itemName);
        }
        return false;
    }

    /**
     * Gets bestselling status of an item from a vendor
     * @param vendorIndex Index of vendor to check
     * @param itemName Name of item to get status for
     * @return Best selling status of a vendor
     */
    public boolean getBestSelling(int vendorIndex, String itemName){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).bestSellerStatus(itemName);
        }
        return false;
    }

    /**
     * Retrieves the array of all bestSeller item names
     * @param vendorIndex index of vendor to check
     * @return ArrayList of bestselling item names
     */
    public ArrayList<String> getBestSellers(int vendorIndex){
        if(vendorIndex >= 0 && vendorIndex < vendors.size()) {
            return vendors.get(vendorIndex).getBestSellers();
        }
        return null;
    }

}
