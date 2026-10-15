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

}