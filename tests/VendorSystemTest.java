import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VendorSystemTest {

    private VendorSystem vs;
    @BeforeEach
    void setUp(){
        vs = new VendorSystem();
        Vendor v = new Vendor(5,5, "Its chewy", "Its sweet",
        "Sweets", "Sweets");
        v.addMoney(20);
        vs.addVendor(v);
        vs.addVendor(new Vendor(5,5, "Its chewy", "Its sweet",
                "Sweets", "Sweets"));
        vs.addVendor(new Vendor(5,5, "Its chewy", "Its sweet",
                "Sweets", "Sweets"));
        vs.addVendor(new Vendor(5,5, "Its chewy", "Its sweet",
                "Sweets", "Sweets"));
        Vendor v2 = new Vendor(5,5, "Its chewy", "Its sweet",
                "Sweets", "Sweets");
        v2.addMoney(20);
        v2.restock("Coke", 10, 3, "Its fizzy", "Soda");
        v2.restock("Gummy", 5, 0.5, "Its sweet and chewy", "Sweets");
        v2.addMoney(20);
        vs.addVendor(v2);
    }

    @Test
    void removeItemFromVendorInSystem(){
        assertTrue(vs.vendorHasItem(0, "Gum"));
        assertTrue(vs.removeItemFromVendor(0, "Gum"));
        assertFalse(vs.vendorHasItem(0, "Gum"));
    }

    @Test
    void removeItemFromVendorInSystemOverlap(){
        assertTrue(vs.vendorHasItem(0, "Gum"));
        assertTrue(vs.removeItemFromVendor(0, "Gum"));
        assertFalse(vs.vendorHasItem(0, "Gum"));
        assertTrue(vs.vendorHasItem(1, "Gum"));
    }

    @Test
    void removeItemFromInvalidVendorInSystem(){
        assertFalse(vs.removeItemFromVendor(110, "Gum"));
        assertFalse(vs.removeItemFromVendor(-110, "Gum"));
    }


    @Test
    void buyItemToVendorInSystem(){
        assertTrue(vs.buyItemFromVendor(0, "Gum"));
        assertEquals(4, vs.getVendorStock(0, "Gum"));
    }

    @Test
    void buyItemToVendorInSystemOverlap(){
        assertTrue(vs.buyItemFromVendor(0, "Gum"));
        assertEquals(4, vs.getVendorStock(0, "Gum"));
        assertEquals(5, vs.getVendorStock(2,"Gum"));
    }

    @Test
    void buyItemToVendorInSystemNotValid(){
        assertFalse(vs.vendorExists(100));
        assertFalse(vs.buyItemFromVendor(100, "Gum"));
        assertFalse(vs.vendorExists(100));

        assertFalse(vs.vendorExists(-100));
        assertFalse(vs.buyItemFromVendor(-100, "Gum"));
        assertFalse(vs.vendorExists(-100));
    }

    @Test
    void emptyInventoryOfVendorInSystem(){
        assertTrue(vs.emptyVendor(4));
        assertFalse(vs.getVendor(4).hasItem("Gum"));
        assertFalse(vs.getVendor(4).hasItem("Candy"));
    }

    @Test
    void emptyInventoryOfInvalidVendorInSystem(){
        assertFalse(vs.emptyVendor(40));
        assertFalse(vs.vendorExists(40));

        assertFalse(vs.emptyVendor(-40));
        assertFalse(vs.vendorExists(-40));
    }

    @Test
    void addOverCap(){
        assertEquals(5, vs.numberOfVendors());
        assertFalse(vs.addVendor(new Vendor(1,1, "Its chewy",
                "Its sweet", "Sweets", "Sweets")));
        assertEquals(5, vs.numberOfVendors());
    }

    @Test
    void emptyInventoryOfVendorInSystemOverlap(){
        assertTrue(vs.emptyVendor(4));
        assertFalse(vs.getVendor(4).hasItem("Gum"));
        assertFalse(vs.getVendor(4).hasItem("Candy"));

        assertTrue(vs.getVendor(3).hasItem("Gum"));
        assertTrue(vs.getVendor(3).hasItem("Candy"));
    }

    @Test
    void restockItemToVendorInSystem(){
        assertTrue(vs.restockItemFromVendor(4,"Gum", 10, 3,
                "Its pink", "Sweets"));
        assertEquals(15, vs.getVendorStock(4,"Gum"));
        assertEquals("Its chewy", vs.getItemDescription(4, "Gum"));
        assertEquals("Sweets", vs.getItemCategory(4, "Gum"));

    }

    @Test
    void restockItemToVendorInSystemOverlap(){
        assertTrue(vs.restockItemFromVendor(4,"Gum", 10, 3,
                "Its mint", "Sweets"));
        assertEquals(15, vs.getVendorStock(4,"Gum"));
        assertEquals("Its chewy", vs.getItemDescription(4, "Gum"));
        assertEquals("Sweets", vs.getItemCategory(4, "Gum"));
        assertEquals(5, vs.getVendorStock(0,"Gum"));
        assertEquals("Its chewy", vs.getItemDescription(0, "Gum"));
        assertEquals("Sweets", vs.getItemCategory(0, "Gum"));
    }

    @Test
    void restockNewItemToVendorInSystem(){
        assertTrue(vs.restockItemFromVendor(1,"Pepsi", 10, 3,
                "Its cola", "Soda"));
        assertEquals(10, vs.getVendorStock(1,"Pepsi"));
        assertEquals("Its cola", vs.getItemDescription(1, "Pepsi"));
        assertEquals("Soda", vs.getItemCategory(1, "Pepsi"));
        assertTrue(vs.vendorHasItem(1,"Pepsi"));
    }

    @Test
    void restockNewItemToVendorInSystemOverlap(){
        assertTrue(vs.restockItemFromVendor(1,"Pepsi", 10, 3,
                "Its cola", "Soda"));
        assertEquals(10, vs.getVendorStock(1,"Pepsi"));
        assertTrue(vs.vendorHasItem(1,"Pepsi"));
        assertEquals("Its cola", vs.getItemDescription(1, "Pepsi"));
        assertEquals("Soda", vs.getItemCategory(1, "Pepsi"));
        assertFalse(vs.vendorHasItem(0,"Pepsi"));
        assertNull(vs.getItemDescription(0, "Pepsi"));
    }


    @Test
    void restockInvalidVendorInSystem(){
        assertFalse(vs.restockItemFromVendor(100,"Gummy", 10, 3,
                "Its a bear", "Sweets"));
        assertFalse(vs.vendorExists(100));

        assertFalse(vs.restockItemFromVendor(-100,"Gummy", 10, 3,
                "Its a bear", "Sweets"));
        assertFalse(vs.vendorExists(-100));
    }


    @Test
    void addVendor(){
        vs.removeVendor(0);
        vs.removeVendor(0);
        assertTrue(vs.addVendor(new Vendor(5,5, "Its chewy"
                , "Its sweet", "Sweets", "Sweets")));
        assertEquals(4, vs.numberOfVendors());
        assertTrue(vs.vendorExists(3));
    }

    @Test
    void removeVendor(){
        assertTrue(vs.removeVendor(0));
        assertEquals(4, vs.numberOfVendors());
    }

    @Test
    void removeVendorNotExist(){
        assertFalse(vs.vendorExists(100));
        assertFalse(vs.removeVendor(100));
        assertFalse(vs.vendorExists(100));

        assertFalse(vs.vendorExists(-100));
        assertFalse(vs.removeVendor(-100));
        assertFalse(vs.vendorExists(-100));
    }

    @Test
    void getVendor(){
        assertNotNull(vs.getVendor(0));
        assertNotNull(vs.getVendor(2));
        assertNull(vs.getVendor(-10));
        assertNull(vs.getVendor(5));
        assertNull(vs.getVendor(100));
    }

    @Test
    void removeVendorNoVendors(){
        int size = vs.numberOfVendors();
        for( int i = 0; i < size; i++ ){
            vs.removeVendor(0);
        }
        assertFalse(vs.removeVendor(0));
        assertEquals(0, vs.numberOfVendors());
    }

    @Test
    void vendorSystemSize(){
        assertEquals(5, vs.numberOfVendors());
    }

    @Test
    void vendorSystemPrintList(){
        ArrayList<String> output = vs.printVendorsInventory();
        assertEquals(5, output.size());
        for( int i = 0; i < output.size(); i++ ){
            assertTrue(output.get(i).contains("Vendor_"+ i + ": "));
            assertTrue(output.get(i).contains(vs.getVendorInventory(i)));
        }
    }

    @Test
    void vendorSystemPrintListEmpty(){
        int size = vs.numberOfVendors();
        for( int i = 0; i < size; i++ ){
            vs.removeVendor(0);
        }
        assertEquals(0, vs.printVendorsInventory().size());
    }

    @Test
    void vendorSystemItemNameChange(){
        assertTrue(vs.renameItemFromVendor(1,"Gum","Gumball"));
        assertTrue(vs.vendorHasItem(1,"Gumball"));
        assertEquals(5, vs.getVendorStock(1,"Gumball"));
    }


    @Test
    void vendorSystemItemNameChangeOverlap(){
        assertTrue(vs.renameItemFromVendor(1,"Gum","Gumball"));
        assertTrue(vs.vendorHasItem(1,"Gumball"));
        assertEquals(5, vs.getVendorStock(1,"Gumball"));
        assertFalse(vs.vendorHasItem(0,"Gumball"));
    }


    @Test
    void vendorSystemItemNameVendorNotValid(){
        assertFalse(vs.renameItemFromVendor(100,"Gum","Gumball"));
        assertFalse(vs.vendorExists(100));

        assertFalse(vs.renameItemFromVendor(-10,"Gum","Gumball"));
        assertFalse(vs.vendorExists(-10));
    }

    @Test
    void addMoneyToVendor(){
        assertTrue(vs.addMoneyToVendor(0,20));
        assertEquals(40,vs.getVendorBalance(0));
    }

    @Test
    void addMoneyToVendorOverlap(){
        assertTrue(vs.addMoneyToVendor(0,20));
        assertEquals(40,vs.getVendorBalance(0));
        assertEquals(0, vs.getVendorBalance(3));
    }

    @Test
    void addMoneyToInvalidVendor(){
        assertFalse(vs.vendorExists(100));
        assertFalse(vs.addMoneyToVendor(100, 20.8));
        assertFalse(vs.vendorExists(100));

        assertFalse(vs.vendorExists(-10));
        assertFalse(vs.addMoneyToVendor(-10, 20.8));
        assertFalse(vs.vendorExists(-10));
    }

    @Test
    void getVendorInventory(){
        assertTrue(vs.getVendorInventory(0).contains("Gum: 5"));
    }

    @Test
    void getVendorInventoryInvalid(){
        assertNull(vs.getVendorInventory(100));
        assertNull(vs.getVendorInventory(-10));
    }

    @Test
    void getVendorStock(){
        assertEquals(5, vs.getVendorStock(0, "Candy"));
    }

    @Test
    void getVendorStockInvalid(){
        assertEquals(0, vs.getVendorStock(100, "Candy"));
        assertEquals(0, vs.getVendorStock(-10, "Candy"));
    }

    @Test
    void getVendorBalance(){
        assertEquals(20.0, vs.getVendorBalance(0));
    }

    @Test
    void getVendorBalanceInvalid(){
        assertEquals(0.0, vs.getVendorBalance(100));
        assertEquals(0.0, vs.getVendorBalance(-10));
    }

    @Test
    void vendorHasItem(){
        assertTrue(vs.vendorHasItem(1,"Gum"));
        assertFalse(vs.vendorHasItem(1,"Gumball Machine"));
    }

    @Test
    void vendorHasItemInvalidIndex(){
        assertFalse(vs.vendorHasItem(-10,"Gum"));
        assertFalse(vs.vendorHasItem(110,"Gumball Machine"));
    }

    @Test
    void itemPurchaseCount(){
        vs.getVendor(0).addMoney(20);
        vs.buyItemFromVendor(0, "Gum");
        vs.buyItemFromVendor(0, "Gum");
        vs.buyItemFromVendor(0, "Gum");

        vs.getVendor(3).addMoney(20);
        vs.buyItemFromVendor(3, "Gum");
        vs.buyItemFromVendor(3, "Candy");

        vs.getVendor(4).addMoney(20);
        vs.buyItemFromVendor(4, "Candy");
        assertEquals(4, vs.getItemPurchaseCount("Gum"));
        assertEquals(2, vs.getItemPurchaseCount("Candy"));
        assertEquals(0, vs.getItemPurchaseCount("Coke"));
        assertEquals(0, vs.getItemPurchaseCount("Gummy"));
    }

    @Test
    void itemPurchaseCountNull(){
        assertEquals(0, vs.getItemPurchaseCount(null));
    }

    @Test
    void itemPurchaseCountBlank(){
        assertEquals(0, vs.getItemPurchaseCount(""));
    }

    @Test
    void itemPurchaseCountNotValidItem(){
        assertEquals(0, vs.getItemPurchaseCount("Gumball Machine"));
    }

    @Test
    void purchaseInsights(){
        vs.getVendor(0).addMoney(20);
        vs.getVendor(3).addMoney(20);
        vs.getVendor(1).addMoney(20);
        vs.getVendor(2).addMoney(20);

        vs.buyItemFromVendor(0, "Gum");
        vs.buyItemFromVendor(3, "Candy");
        vs.buyItemFromVendor(3, "Gum");
        vs.buyItemFromVendor(1, "Candy");
        vs.buyItemFromVendor(2, "Gum");
        vs.buyItemFromVendor(2, "Candy");
        vs.buyItemFromVendor(3, "Gum");
        vs.buyItemFromVendor(1, "Candy");
        vs.buyItemFromVendor(4, "Gum");
        vs.buyItemFromVendor(4, "Gum");
        vs.buyItemFromVendor(4, "Coke");

        String output = vs.getPurchaseInsights();
        assertTrue(output.contains("Most Popular Item is: Gum"));
        assertTrue(output.contains("Gum: 6"));
        assertTrue(output.contains("Gummy: 0"));
        assertTrue(output.contains("Coke: 1"));
        assertTrue(output.contains("Candy: 4"));

    }

    @Test
    void purchaseInsightsNull(){
        for(int i=0; i<vs.numberOfVendors(); i++){
            vs.getVendor(i).emptyVendor();
        }
        assertNull(vs.getPurchaseInsights());
    }

    @Test
    void getItemDescription(){
        assertNotNull(vs.getItemDescription(0, "Gum"));
        assertEquals("Its chewy", vs.getItemDescription(0, "Gum"));
    }

    @Test
    void getItemDescriptionVendorNotExist(){
        assertNull(vs.getItemDescription(100, "Gum"));
        assertNull(vs.getItemDescription(-100, "Gum"));
    }

    @Test
    void setItemDiscount(){
        assertTrue(vs.setItemDiscount(0,0.8, "Candy"));
        assertEquals(1.0, vs.getItemCost(0,"Candy"));
    }

    @Test
    void setItemDiscountInvalid(){
        assertFalse(vs.setItemDiscount(-10,0.8, "Candy"));
        assertFalse(vs.setItemDiscount(10,0.8, "Candy"));
    }

    @Test
    void setItemDiscountOverlap(){
        vs.setItemDiscount(0,0.8, "Candy");
        assertNotEquals(vs.getItemCost(0, "Candy"),
                vs.getItemCost(1, "Candy"));
    }

    @Test
    void setCategoryDiscount(){
        assertTrue(vs.setCategoryDiscount(0,0.8, "Sweets"));
        assertEquals(1.0, vs.getItemCost(0,"Candy"));
        assertEquals(0.4, vs.getItemCost(0,"Gum"));
    }

    @Test
    void setCategoryDiscountInvalid(){
        assertFalse(vs.setCategoryDiscount(-10,0.8, "Sweets"));
        assertFalse(vs.setCategoryDiscount(10,0.8, "Sweets"));
    }

    @Test
    void setCategoryDiscountOverlap(){
        vs.setCategoryDiscount(0,0.8, "Sweets");
        assertNotEquals(vs.getItemCost(0, "Gum"),
                vs.getItemCost(1, "Gum"));
        assertNotEquals(vs.getItemCost(0, "Candy"),
                vs.getItemCost(1, "Candy"));
    }

    @Test
    void getItemCost(){
        assertEquals(0.5, vs.getItemCost(0, "Gum"));
    }

    @Test
    void getItemCostInvalid(){
        assertEquals(0.0, vs.getItemCost(-10, "Gum"));
        assertEquals(0.0, vs.getItemCost(10, "Gum"));
    }

    @Test
    void getCategory(){
        assertEquals("Sweets", vs.getItemCategory(0, "Gum"));
    }

    @Test
    void getCategoryInvalid(){
        assertNull(vs.getItemCategory(-10, "Gum"));
        assertNull(vs.getItemCategory(10, "Gum"));
    }
}