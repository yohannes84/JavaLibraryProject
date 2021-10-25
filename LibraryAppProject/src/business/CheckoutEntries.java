package business;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutEntries implements Serializable {

    @Serial
    private static final long serialVersionUID = 7042629999601538443L;
    BookCopy bookCopy;
    LocalDate checkoutDate;
    LocalDate dueDate;

    private  CheckoutEntries( BookCopy bookCopy , LocalDate checkoutDate , LocalDate dueDate  ){

            this.bookCopy = bookCopy;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
    }

    public static CheckoutEntries createEntry(BookCopy bookCopy , LocalDate checkoutDate , LocalDate dueDate ){
        return new CheckoutEntries(bookCopy , checkoutDate , dueDate);
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
