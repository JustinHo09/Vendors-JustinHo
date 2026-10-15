import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}