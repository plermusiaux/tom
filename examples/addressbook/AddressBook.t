import aterm.*;
import aterm.pure.*;
import adt.address.data.*;
import adt.address.data.types.*;

import java.util.Iterator;
import java.util.HashSet;

public class AddressBook {
  private Factory factory;

  private HashSet book;
  
  %include { data.tom }
 
  public AddressBook(Factory factory) {
    this.factory = factory;
  }

  public Factory getDataFactory() {
    return factory;
  }

  public final static void main(String[] args) {
    AddressBook test = new AddressBook(new Factory(new PureFactory()));
    test.run();
  }
  
  public void run() {
    book = new HashSet();
    generatePerson(book);
    
    Iterator it = book.iterator();
    while(it.hasNext()) {
      Person p = (Person) it.next();
      happyBirthday(p, `date(2003,10,19));
    }
  }

  public void happyBirthday(Person p, Date today) {
    %match(Person p, Date today) {
      person(firstname,lastname,date(year,month1,day1)),
        date(_,month2,day2) -> {
          if(month1==month2 && day1==day2) {
            System.out.println("Happy Birthday " + firstname );
          }  			  
      }
    }
    
 }
  
  public void generatePerson(HashSet set) {
	set.add(`person("John","Smith",date(1965,9,18)));
	set.add(`person("Marie","Muller",date(1986,10,19)));
	set.add(`person("Paul","Muller",date(2000,9,20)));
	}

}

