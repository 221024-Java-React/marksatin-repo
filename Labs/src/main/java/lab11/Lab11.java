package lab11;

import java.util.ArrayList;
import java.util.Iterator;

public class Lab11 {
	
	private String[] firstNameList;

	public String[] nameList(Person[] personList) {
		firstNameList = new String[personList.length];
		
		for (int i = 0; i < personList.length; i++) {
			firstNameList[i] = personList[i].getFirst();
		}
		return firstNameList;
	}
	
	public String iteratorFun(ArrayList<Person> personList) {
		Iterator<Person> it = personList.iterator();
		int oldest = 0;
		Person oldestP = null;
		
		while (it.hasNext()) {
			Person p = it.next();
			if (p.getAge() > oldest) {
				oldest = p.getAge();
				oldestP = p;
			}
		}
		return oldestP.getFirst() + " " + oldestP.getLast();
	}
	
	public static void main(String[] args) {
		Person p1 = new Person("Mark", "Satin", 32);
		Person p2 = new Person("Ben", "Price", 24);
		Person p3 = new Person("AJ", "Stanislaus", 23);
		Person p4 = new Person("Cosmo", "Price", 2);
		Person[] pArr = new Person[] {p1, p2, p3, p4};
		
		ArrayList<Person> pArrList = new ArrayList<Person>();
		pArrList.add(p1);
		pArrList.add(p2);
		pArrList.add(p3);
		pArrList.add(p4);
		Lab11 l11 = new Lab11();
		
		String[] fNames = l11.nameList(pArr);
		for (String name : fNames) {
			System.out.println(name);
		}
		
		String oldestP = l11.iteratorFun(pArrList);
		System.out.println(oldestP);
	}
}
