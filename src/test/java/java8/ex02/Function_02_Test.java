package java8.ex02;

import java8.data.Account;
import java8.data.Person;
import org.junit.Test;

import java.util.function.BiFunction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - java.util.function.BiFunction
 */
public class Function_02_Test {

    //  tag::buildAccount[]
    // TODO Compléter la fonction buildAccount
    // TODO la fonction possède 2 paramètres en entrée : une personne et un solde
    BiFunction<Person, Integer, Account> buildAccount = (p,solde)->{
    	Account a = new Account();
    	p.setFirstname("John");
    	p.setLastname("France");
    	p.setPassword("pass");
    	p.setAge(80);
    	a.setBalance(solde);
    	a.setOwner(p);
    	
    	return a;
    };
    //  end::buildAccount[]

    @Test
    public void test_build_account() throws Exception {

        // TODO invoquer la fonction buildAccount pour que le test soit passant
    	Person john = new Person();
        Account account = buildAccount.apply(john, 500);

        assertThat(account, hasProperty("balance", is(500)));
        assertThat(account.getOwner(), hasProperty("firstname", is("John")));
        assertThat(account.getOwner(), hasProperty("lastname", is("France")));
        assertThat(account.getOwner(), hasProperty("age", is(80)));
        assertThat(account.getOwner(), hasProperty("password", is("pass")));
    }


}
