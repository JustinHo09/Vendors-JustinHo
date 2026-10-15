import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VendorTest {

    private Vendor vendor;

    @BeforeEach
    public void setup() {
        vendor = new Vendor(6,6, "Its oh so chewy and pink!"
                , "This is super sweet and round!", "Sweets", "Sweets");
    }

    @Test
    void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void addMoney(){
        vendor.addMoney(10.5);
        assertEquals(10.5, vendor.getBalance());
    }

    @Test
    void addMoneyNeg(){
        vendor.addMoney(-10.5);
        assertEquals(0, vendor.getBalance());
    }

    @Test
    void buyItem(){
        vendor.addMoney(10.5);
        vendor.select("Gum");
        assertEquals(5, vendor.getStock("Gum"));
        assertEquals(10, vendor.getBalance());
    }

    @Test
    void buyItemBroke(){
        vendor.addMoney(0.1);
        vendor.select("Candy");
        assertEquals(0.1, vendor.getBalance());
        assertEquals(6, vendor.getStock("Candy"));
    }

    @Test
    void buyItemNonExistent(){
        vendor.addMoney(10.5);
        vendor.select("Tesseract");
        assertEquals(10.5, vendor.getBalance());
    }

    @Test
    void emptyInventory(){
        vendor.emptyVendor();
        assertFalse(vendor.hasItem("Gum"));
        assertFalse(vendor.hasItem("Candy"));
        assertEquals(0, vendor.getStock("Gum"));
        assertEquals(0,vendor.getStock("Candy"));
    }

    @Test
    void buyAfterEmptyInventory(){
        //First verify the inventory is empty before proceeding
        vendor.emptyVendor();
        assertFalse(vendor.hasItem("Gum"));
        assertFalse(vendor.hasItem("Candy"));

        //Now attempt the purchase
        vendor.addMoney(10.5);
        assertEquals(0, vendor.getStock("Gum"));
        assertEquals(0,vendor.getStock("Candy"));
        vendor.select("Gum");
        vendor.select("Candy");
        assertEquals(10.5, vendor.getBalance());
    }

    @Test
    void restock(){
        vendor.restock("Gum",10,0, "Its pink", "Sweets");
        assertEquals(16, vendor.getStock("Gum"));
        assertEquals("Sweets", vendor.getCategory("Gum"));
    }

    @Test
    void restockEmpty(){
        vendor.addMoney(10);
        int stock = vendor.getStock("Gum");
        for (int i =0; i< stock; i++){
            vendor.select("Gum");
        }
        assertEquals(0,vendor.getStock("Gum"));
        vendor.restock("Gum",10,1, "Its pink", "Sweets");
        assertEquals(10,vendor.getStock("Gum"));
        assertEquals("Sweets", vendor.getCategory("Gum"));
    }

    @Test
    void restockIndependent(){
        vendor.restock("Gum",10,1, "Its pink", "Sweets");
        assertEquals(6, vendor.getStock("Candy"));
    }

    @Test
    void restockNeg(){
        vendor.restock("Gum",-10,1, "Its pink", "Sweets");
        assertEquals(6, vendor.getStock("Gum"));
    }

    @Test
    void restockNewItem(){
        vendor.restock("Oats",10,1, "It has Washington's Face", "Healthy");
        assertTrue(vendor.hasItem("Oats"));
        assertEquals(10, vendor.getStock("Oats"));
        assertEquals("It has Washington's Face", vendor.getItemDescription("Oats"));
        assertEquals("Healthy", vendor.getCategory("Oats"));
    }

    @Test
    void buyNewRestocked(){
        vendor.restock("Gummy", 10,1, "Its pink", "Sweets");
        assertTrue(vendor.hasItem("Gummy"));
        vendor.addMoney(10.5);
        assertEquals(10, vendor.getStock("Gummy"));
        vendor.select("Gummy");
        assertEquals(9, vendor.getStock("Gummy"));
        assertEquals(9.5, vendor.getBalance());
    }

    @Test
    void restockNewItemNegPrice(){
        vendor.restock("Gummy",10,-1, "Its sour", "Sweets");
        assertFalse(vendor.hasItem("Gummy"));
    }

    @Test
    void restockNegPrice(){
        vendor.restock("Gum",10,-1, "Its pink", "Sweets");
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6,vendor.getStock("Gum"));
    }

    @Test
    void restockKeepsExistingPrice(){
        vendor.restock("Candy",5,20, "Its chocolate", "Sweets");
        assertTrue(vendor.hasItem("Candy"));
        assertEquals(1.25,vendor.getPrice("Candy"));
    }

    @Test
    void restockNewItemNegQuantity(){
        vendor.restock("Mushroom",-5,2, "Its poison", "Danger");
        assertFalse(vendor.hasItem("Mushroom"));
    }

    @Test
    void changeName(){
        assertTrue(vendor.hasItem("Gum"));
        vendor.changeName("Gum", "Chewing Gum");
        assertFalse(vendor.hasItem("Gum"));
        assertTrue(vendor.hasItem("Chewing Gum"));
        assertEquals(6, vendor.getStock("Chewing Gum"));
        assertEquals(0.5,  vendor.getPrice("Chewing Gum"));
    }

    @Test
    void changeNameNull(){
        assertTrue(vendor.hasItem("Gum"));
        vendor.changeName("Gum", null);
        assertFalse(vendor.hasItem(null));
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6, vendor.getStock("Gum"));
        assertEquals(0.5,  vendor.getPrice("Gum"));
    }

    @Test
    void changeNameBlank(){
        assertTrue(vendor.hasItem("Gum"));
        vendor.changeName("Gum", "");
        assertFalse(vendor.hasItem(""));
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6, vendor.getStock("Gum"));
        assertEquals(0.5,  vendor.getPrice("Gum"));
    }

    @Test
    void changeNameSpace() {
        assertTrue(vendor.hasItem("Gum"));
        vendor.changeName("Gum", " ");
        assertFalse(vendor.hasItem(" "));
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6, vendor.getStock("Gum"));
        assertEquals(0.5, vendor.getPrice("Gum"));
    }

    @Test
    void buyChangedName(){
        vendor.changeName("Gum", "Chewing Gum");
        assertTrue(vendor.hasItem("Chewing Gum"));
        assertEquals(6, vendor.getStock("Chewing Gum"));
        vendor.addMoney(10.5);
        vendor.select("Chewing Gum");
        assertEquals(5,  vendor.getStock("Chewing Gum"));
        assertEquals(10,  vendor.getBalance());
    }

    @Test
    void renameExistingName(){
        vendor.changeName("Gum", "Candy");
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6, vendor.getStock("Candy"));
        assertEquals(1.25,  vendor.getPrice("Candy"));
    }

    @Test
    void renameSameDescription(){
        vendor.changeName("Gum", "Chewing Gum");
        assertEquals("Its oh so chewy and pink!", vendor.getItemDescription("Chewing Gum"));
    }

    @Test
    void renameNonExistentName(){
        vendor.changeName("Chewing Gum", "Hubba Bubba");
        assertFalse(vendor.hasItem("Hubba Bubba"));
    }

    @Test
    void renameSameName(){
        vendor.changeName("Gum", "Gum");
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6, vendor.getStock("Gum"));
        assertEquals(0.5,  vendor.getPrice("Gum"));
    }

    @Test
    void removeItem(){
        vendor.removeItem("Gum");
        assertFalse(vendor.hasItem("Gum"));
        assertEquals(0, vendor.getStock("Gum"));
        assertTrue(vendor.hasItem("Candy"));
    }

    @Test
    void buyRemovedItem(){
        vendor.removeItem("Gum");
        assertFalse(vendor.hasItem("Gum"));
        vendor.addMoney(10.5);
        vendor.select("Gum");
        assertEquals(10.5,  vendor.getBalance());
    }

    @Test
    void removeNonExistItem(){
        vendor.removeItem("Pockey");
        assertFalse(vendor.hasItem("Pockey"));
        assertEquals(0, vendor.getStock("Pockey"));
        assertTrue(vendor.hasItem("Gum"));
        assertTrue(vendor.hasItem("Candy"));
    }

    @Test
    void removeItemNull(){
        vendor.removeItem(null);
        assertFalse(vendor.hasItem(null));
        assertEquals(0, vendor.getStock(null));
        assertTrue(vendor.hasItem("Gum"));
        assertTrue(vendor.hasItem("Candy"));
    }

    @Test
    void removeAndAddItem(){
        vendor.removeItem("Gum");
        assertFalse(vendor.hasItem("Gum"));
        assertEquals(0, vendor.getStock("Gum"));

        vendor.restock("Gum", 10, 0.6, "Its pink", "Sweets");
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(10, vendor.getStock("Gum"));
        assertEquals(0.6,  vendor.getPrice("Gum"));
    }

    @Test
    void getPurchaseCount(){
        assertEquals(0, vendor.getItemPurchaseCount("Gum"));
        vendor.addMoney(10.5);
        vendor.select("Gum");
        vendor.select("Gum");
        assertEquals(2, vendor.getItemPurchaseCount("Gum"));
    }

    @Test
    void getPurchaseCountNull(){
        assertEquals(0, vendor.getItemPurchaseCount(null));
    }

    @Test
    void getItems(){
        assertTrue(vendor.getItems().contains("Gum"));
        assertTrue(vendor.getItems().contains("Candy"));
        assertFalse(vendor.getItems().contains("Gummy"));
    }

    @Test
    void getItemsEmpty(){
        vendor.emptyVendor();
        assertTrue(vendor.getItems().isEmpty());
    }

    @Test
    void getPurchaseCountNotExist(){
        assertEquals(0, vendor.getItemPurchaseCount("Gumball Machine"));
    }

    @Test
    void getPurchaseCountEmpty(){
        assertEquals(0, vendor.getItemPurchaseCount(""));
    }

    @Test
    void getItemsDescription(){
        assertTrue(vendor.getItemDescription("Gum").contains("Its oh so chewy"));
        assertNotNull(vendor.getItemDescription("Gum"));
    }

    @Test
    void getItemsDescriptionNull(){
        assertNull(vendor.getItemDescription(null));
    }

    @Test
    void getItemsDescriptionNotValid(){
        assertNull(vendor.getItemDescription("Gumball Machine"));
    }

    @Test
    void getItemsDescriptionBlank(){
        assertNull(vendor.getItemDescription(""));
    }

    @Test
    void restockSameItemDescription(){
        vendor.restock("Gum", 10, 0.6, "Its pink", "Sweets");
        assertFalse(vendor.getItemDescription("Gum").contains("Its pink"));
    }

    @Test
    void restockSameCategory(){
        vendor.restock("Gum", 10, 0.6, "Chewy", "Candy");
        assertEquals("Sweets", vendor.getCategory("Gum"));
    }

    @Test
    void getItemCategory(){
        assertEquals("Sweets", vendor.getItemCategory("Gum"));
    }

    @Test
    void getItemCategoryNull(){
        assertNull(null, vendor.getItemCategory(null));
    }

    @Test
    void getItemCategoryBlank(){
        assertNull(null, vendor.getItemCategory(""));
    }

    @Test
    void getItemCategoryInvalid(){
        assertNull(null, vendor.getItemCategory("Gumball Machine"));
    }

    @Test
    void getItemsInCategory(){
        assertEquals(2, vendor.getItemsInCategory("Sweets").size());
    }

    @Test
    void getItemsInCategoryNull(){
        assertEquals(0, vendor.getItemsInCategory(null).size());
    }

    @Test
    void getItemsInCategoryBlank(){
        assertEquals(0, vendor.getItemsInCategory("").size());
    }

    @Test
    void getItemsInCategoryInvalid(){
        assertEquals(0, vendor.getItemsInCategory("Baking").size());
    }

    @Test
    void setItemDiscount(){
        assertTrue(vendor.setDiscountItem(0.8, "Candy"));
        assertEquals(1.0, vendor.getPrice("Candy"));
    }

    @Test
    void setItemDiscountNull(){
        assertFalse(vendor.setDiscountItem(0.8, null));
    }

    @Test
    void setItemDiscountBlank(){
        assertFalse(vendor.setDiscountItem(0.8, ""));
    }

    @Test
    void setItemDiscountInvalid(){
        assertFalse(vendor.setDiscountItem(0.8, "Blanket"));
        assertFalse(vendor.setDiscountItem(-0.8, "Candy"));
        assertFalse(vendor.setDiscountItem(8, "Candy"));
    }

    @Test
    void setCategoryDiscount(){
        assertTrue(vendor.setDiscountCategory(0.8, "Sweets"));
        assertEquals(1.0, vendor.getPrice("Candy"));
        assertEquals(0.4, vendor.getPrice("Gum"));
    }

    @Test
    void setCategoryDiscountNull(){
        assertFalse(vendor.setDiscountCategory(0.8, null));
        assertEquals(1.25, vendor.getPrice("Candy"));
        assertEquals(0.5, vendor.getPrice("Gum"));
    }

    @Test
    void setCategoryDiscountBlank(){
        assertFalse(vendor.setDiscountCategory(0.8, ""));
        assertEquals(1.25, vendor.getPrice("Candy"));
        assertEquals(0.5, vendor.getPrice("Gum"));
    }

    @Test
    void setCategoryDiscountInvalid(){
        assertFalse(vendor.setDiscountCategory(0.8, "Tech"));
        assertEquals(1.25, vendor.getPrice("Candy"));
        assertEquals(0.5, vendor.getPrice("Gum"));
        assertTrue(vendor.setDiscountCategory(-0.8, "Sweets"));
        assertEquals(1.25, vendor.getPrice("Candy"));
        assertEquals(0.5, vendor.getPrice("Gum"));
        assertTrue(vendor.setDiscountCategory(8, "Sweets"));
        assertEquals(1.25, vendor.getPrice("Candy"));
        assertEquals(0.5, vendor.getPrice("Gum"));
    }

    @Test
    void setBestSeller(){
        assertFalse(vendor.bestSellerStatus("Gum"));
        assertTrue(vendor.setBestSeller(true, "Gum"));
        assertTrue(vendor.bestSellerStatus("Gum"));
        assertFalse(vendor.bestSellerStatus("Candy"));
    }

    @Test
    void setBestSellerNull(){
        assertFalse(vendor.setBestSeller(true,null));
    }

    @Test
    void setBestSellerBlank(){
        assertFalse(vendor.setBestSeller(true,""));
    }

    @Test
    void setBestSellerInvalid(){
        assertFalse(vendor.setBestSeller(true, "Gumball"));
    }


    @Test
    void getBestSellerStatus(){
        assertFalse(vendor.bestSellerStatus("Gum"));
        vendor.setBestSeller(true, "Gum");
        assertTrue(vendor.bestSellerStatus("Gum"));
        assertFalse(vendor.bestSellerStatus("Candy"));
    }

    @Test
    void getBestSellerStatusNull(){
        assertFalse(vendor.bestSellerStatus(null));
    }

    @Test
    void getBestSellerStatusBlank(){
        assertFalse(vendor.bestSellerStatus(""));
    }

    @Test
    void getBestSellerStatusInvalid(){
        assertFalse(vendor.bestSellerStatus("Gumball"));
    }

    @Test
    void getBestSellersNone(){
        assertEquals(0, vendor.getBestSellers().size());
    }

    @Test
    void getBestSellers(){
        vendor.setBestSeller(true, "Gum");
        vendor.setBestSeller(true, "Candy");
        assertEquals(2, vendor.getBestSellers().size());
        assertTrue(vendor.getBestSellers().contains("Gum"));
        assertTrue(vendor.getBestSellers().contains("Candy"));
    }
}