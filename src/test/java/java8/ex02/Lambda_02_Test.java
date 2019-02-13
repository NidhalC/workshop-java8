package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

	// tag::PersonToAccountMapper[]
	interface PersonToAccountMapper {
		Account map(Person p);
	}
	// end::PersonToAccountMapper[]

	// tag::map[]
	private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
		// TODO implémenter la méthode
		List<Account> personFiltre = new ArrayList<Account>();
		for (Person p:personList){

			Account conversion =mapper.map(p);
			personFiltre.add(conversion);			
		}


		return personFiltre;
	}
	// end::map[]



	// tag::test_map_person_to_account[]
	@Test
	public void test_map_person_to_account() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		PersonToAccountMapper mapper= p -> {
			Account a = new Account();
			a.setOwner(p);
			a.setBalance(100);
			return a;
		};

		// TODO transformer la liste de personnes en liste de comptes
		// TODO tous les objets comptes ont un solde à 100 par défaut
		List<Account> result = map(personList,  mapper);


		assertThat(result, hasSize(personList.size()));
		assertThat(result, everyItem(hasProperty("balance", is(100))));
		assertThat(result, everyItem(hasProperty("owner", notNullValue())));
	}
	// end::test_map_person_to_account[]

	// tag::test_map_person_to_firstname[]

	interface AccountToFirstName{
		String map(Account listAccount);
	}
	
	private List<String> map(List<Account> personList, AccountToFirstName mapper) {
		// TODO implémenter la méthode
		List<String> personFiltre = new ArrayList<String>();
		for (Account p:personList){

			String conversion =mapper.map(p);
			personFiltre.add(conversion);			
		}


		return personFiltre;
	}
	@Test
	public void test_map_person_to_firstname() throws Exception {

		List<Person> personList = Data.buildPersonList(100);
		PersonToAccountMapper mapper= p -> {
			Account a = new Account();
			return a;
		};
		List<Account> listAccount =map(personList, mapper);
		

		AccountToFirstName nameMapper = a ->{
			
	
			
			return a.getOwner().getFirstname();
			
			
		};

		// TODO transformer la liste de personnes en liste de prénoms
		List<String> result = map(listAccount, nameMapper);

		assertThat(result, hasSize(personList.size()));
		assertThat(result, everyItem(instanceOf(String.class)));
		assertThat(result, everyItem(startsWith("first")));
	}
	// end::test_map_person_to_firstname[]
}
