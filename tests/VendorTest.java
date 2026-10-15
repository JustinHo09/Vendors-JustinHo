import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendorTest {

    private Vendor vendor;

    @BeforeEach
    public void setup() {
        vendor = new Vendor(6,6);
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
        vendor.restock("Gum",10,0);
        assertEquals(16, vendor.getStock("Gum"));
    }

    @Test
    void restockEmpty(){
        vendor.addMoney(10);
        int stock = vendor.getStock("Gum");
        for (int i =0; i< stock; i++){
            vendor.select("Gum");
        }
        assertEquals(0,vendor.getStock("Gum"));
        vendor.restock("Gum",10,1);
        assertEquals(10,vendor.getStock("Gum"));
    }

    @Test
    void restockIndependent(){
        vendor.restock("Gum",10,1);
        assertEquals(6, vendor.getStock("Candy"));
    }

    @Test
    void restockNeg(){
        vendor.restock("Gum",-10,1);
        assertEquals(6, vendor.getStock("Gum"));
    }

    @Test
    void restockNewItem(){
        vendor.restock("Oats",10,1);
        assertTrue(vendor.hasItem("Oats"));
        assertEquals(10, vendor.getStock("Oats"));
    }

    @Test
    void buyNewRestocked(){
        vendor.restock("Gummy", 10,1);
        assertTrue(vendor.hasItem("Gummy"));
        vendor.addMoney(10.5);
        assertEquals(10, vendor.getStock("Gummy"));
        vendor.select("Gummy");
        assertEquals(9, vendor.getStock("Gummy"));
        assertEquals(9.5, vendor.getBalance());
    }

    @Test
    void restockNewItemNegPrice(){
        vendor.restock("Gummy",10,-1);
        assertFalse(vendor.hasItem("Gummy"));
    }

    @Test
    void restockNegPrice(){
        vendor.restock("Gum",10,-1);
        assertTrue(vendor.hasItem("Gum"));
        assertEquals(6,vendor.getStock("Gum"));
    }

    @Test
    void restockKeepsExistingPrice(){
        vendor.restock("Candy",5,20);
        assertTrue(vendor.hasItem("Candy"));
        assertEquals(1.25,vendor.getPrice("Candy"));
    }

    @Test
    void restockNewItemNegQuantity(){
        vendor.restock("Mushroom",-5,2);
        assertFalse(vendor.hasItem("Mushroom"));
    }
}