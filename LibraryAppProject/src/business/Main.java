package business;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
		System.out.println(allWhoseZipContains3());
		System.out.println(allHavingOverdueBook());
		System.out.println(allHavingMultipleAuthors());

	}
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		
		List<String> IDS  =
				 mems.stream()
				.filter(X -> X.getAddress().getZip().contains(""+3))
				.map(LibraryMember::getMemberId)
				.sorted()
				.collect(Collectors.toList());
			   
				
		return IDS;
		
	}
	//Returns a list of all ids of  LibraryMembers that have an overdue book
	public static List<String> allHavingOverdueBook() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
//		List<String> IDS  =
//				 mems.stream()
//				.map(X -> X.getCheckoutRecord().checkoutEntriesList)
//				//.filter(x->x.stream().filter(x->x.getDueDate().compareTo(LocalDate.now)))
//				.filter(S-> S.get(0).)
//				.map(LibraryMember::getMemberId)
//				.sorted()
//				.collect(Collectors.toList());
//			   
//				
//		return IDS;
		return null;
		
	}
	
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		
		List<String> IDS  =
				 bs.stream()
				.filter(X -> X.getAuthors().size()>=2)
				.map(Book::getIsbn)
				.sorted()
				.collect(Collectors.toList());
			   
				
		return IDS;
		//return null;
		
		}

}
