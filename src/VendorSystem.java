import java.util.ArrayList;

public class VendorSystem {

    private ArrayList<Vendor> vendors;

    public VendorSystem(){
        vendors = new ArrayList<>();
    }

    public void addVendor(Vendor vendor){
        vendors.add(vendor);
    }

    public boolean removeVendor(int index){
        if (index >= 0 && index < vendors.size()){
            vendors.remove(index);
            return true;
        }
        return false;
    }

    public ArrayList<String> printVendorsInventory(){
        ArrayList<String> inventoryStrings = new ArrayList<>();
        for (int i=0; i<vendors.size(); i++){
            System.out.println("Vendor_"+i+": "+vendors.get(i).getInventory());
            inventoryStrings.add("Vendor_"+i+": "+vendors.get(i).getInventory());
        }
        return inventoryStrings;
    }

    public String getVendorInventory(int index){
        if (index >= 0 && index < vendors.size()){
            return vendors.get(index).getInventory();
        }
        return null;
    }

    public int numberOfVendors(){
        return vendors.size();
    }

    public Vendor getVendor(int index){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index);
        }
        return null;
    }

    public boolean removeItemFromVendor(int index, String itemName){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).removeItem(itemName);
            return true;
        }
        return false;
    }

    public boolean buyItemFromVendor(int index, String itemName){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).select(itemName);
            return true;
        }
        return false;
    }

    public boolean restockItemFromVendor(int index, String itemName, int quantity, double price){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).restock(itemName, quantity, price);
            return true;
        }
        return false;
    }

    public boolean emptyVendor(int index){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).emptyVendor();
            return true;
        }
        return false;
    }

    public boolean renameItemFromVendor(int index, String itemName, String newItemName){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).changeName(itemName, newItemName);
            return true;
        }
        return false;

    }

    public boolean addMoneyToVendor(int index, double amount){
        if(index >= 0 && index < vendors.size()) {
            vendors.get(index).addMoney(amount);
            return true;
        }
        return false;
    }

    public boolean vendorHasItem(int index, String itemName){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index).hasItem(itemName);
        }
        return false;
    }

    public boolean vendorExists(int index){
        if(index >= 0 && index < vendors.size()) {
            return true;
        }
        return false;
    }

    public int getVendorStock(int index,String itemName){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index).getStock(itemName);
        }
        return 0;
    }

    public double getVendorBalance(int index){
        if(index >= 0 && index < vendors.size()) {
            return vendors.get(index).getBalance();
        }
        return 0;
    }

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


}
