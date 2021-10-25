package business;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable  {

    @Serial
    private static final long serialVersionUID = 1610595103854896023L;
   public List<CheckoutEntries> checkoutEntriesList;
    CheckoutRecord () {
        checkoutEntriesList = new ArrayList<CheckoutEntries>();
    }

    public void addEntry(CheckoutEntries checkoutEntries){
        checkoutEntriesList.add(checkoutEntries);
    }
}
