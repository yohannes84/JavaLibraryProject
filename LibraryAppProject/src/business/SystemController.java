package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	public boolean addLibraryMember(String memberId, String fname, String lname, String tel,String street, String city, String state, String zip) {
		DataAccess da = new DataAccessFacade();
		Address address = new Address(street,city,state,zip);
		da.saveNewMember(new LibraryMember(memberId , fname , lname , tel , address ));

		return true;
	}
	
	public boolean addNewBook(String ISBN, String title, int maxCheckOutLength, List<Author> author) {
		DataAccess da = new DataAccessFacade();
		//Address address = new Address(street,city,state,zip);
		da.saveBook(new Book(ISBN , ISBN , maxCheckOutLength , author));

		return true;
	}

	public boolean checkoutBook(String memberId , String isbn) {
		DataAccess da = new DataAccessFacade();
		LibraryMember member = da.searchMember(memberId);
		Book book = da.searchBook(isbn);
		if(book == null || !book.isAvailable()){
			return false;
		}
		if(member == null ){
			return false;
		}
		BookCopy bookCopy = book.getNextAvailableCopy();
		int checkoutLength = book.getMaxCheckoutLength();
		member.checkout(bookCopy , LocalDate.now() , LocalDate.now().plusDays(checkoutLength));
		da.saveMember(member);
		da.saveBook(book);
		da.readMemberMap();
		da.readBooksMap();


		return true;
	}

	public Object[][] getMemberData(){
		DataAccess da = new DataAccessFacade();
		Object[][] lists = new Object[da.readMemberMap().size()][8];
	int index = 0;
		for(LibraryMember librarymember : da.readMemberMap().values()){
			System.out.println(librarymember);
			Object[] eachRow = new Object[8];

			System.out.println(librarymember);

			eachRow[0] = librarymember.getMemberId();
			eachRow[1] = librarymember.getFirstName();
			eachRow[2] = librarymember.getLastName();
			eachRow[3] = librarymember.getTelephone();
			eachRow[4] = librarymember.getAddress().getState();
			eachRow[5] = librarymember.getAddress().getCity();
			eachRow[6] =librarymember.getAddress().getStreet();
			eachRow[7] = librarymember.getAddress().getZip();


			lists[index] = eachRow;
			index++;

		}



		return lists;
	}
	
	public List<CheckoutEntries> getMemberCheckoutEntry(String memberId) {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		LibraryMember cr = new LibraryMember();
		
		if(da.readMemberMap().containsKey(memberId)) {
			cr = da.readMemberMap().get(memberId);
		}
		return cr.getCheckoutRecord().checkoutEntriesList;

	
	}

	public List<List<String>> getListOfUpdatedRecords(String memberId){

		List<List<String>> lists = new ArrayList<>();
		DataAccess da = new DataAccessFacade();
		LibraryMember member = da.searchMember(memberId);
		List<CheckoutEntries> listOfCheckoutEntries = member.getCheckoutRecord().checkoutEntriesList;
		for(CheckoutEntries eachCheckoutEntries : listOfCheckoutEntries ){
			List<String> listOfEachValues = new ArrayList<>();
			listOfEachValues.add(eachCheckoutEntries.getBookCopy().getBook().getIsbn());
			listOfEachValues.add(eachCheckoutEntries.getDueDate().toString());
			listOfEachValues.add(eachCheckoutEntries.getCheckoutDate().toString());
			lists.add(listOfEachValues);
		}
		return lists;


	}

	public boolean addCopyOfExistingBook(String isbn) {
		DataAccess da = new DataAccessFacade();

		 Book book = da.searchBook(isbn);
		 System.out.println("I got the book"+ book);
		 if(book == null) return false;
		 book.addCopy();
		 da.saveBook(book);
		return true;
	}

	public void saveUpdatedMembers(Object[][] dataEntries){
		DataAccess da = new DataAccessFacade();

		for(int x=0;x<dataEntries.length;x++){

			String memeberId = (String) dataEntries[x][0];
			String first_name = (String) dataEntries[x][1];
			String last_name = (String) dataEntries[x][2];
			String phoneNumber = (String) dataEntries[x][3];
			String state = (String) dataEntries[x][4];
			String city = (String) dataEntries[x][5];
			String street = (String) dataEntries[x][6];
			String zip = (String) dataEntries[x][7];
			Address address = new Address(state,city,zip,street);
			LibraryMember libraryMember =
					new LibraryMember(memeberId,first_name,last_name,phoneNumber,address);
			da.saveMember(libraryMember);
		}
	}


}
