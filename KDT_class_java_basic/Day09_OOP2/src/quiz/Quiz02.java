package quiz;

import classes.Contact;

import java.util.Scanner;

public class Quiz02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Contact contact = new Contact(sc.nextLine(), sc.nextLine());
        System.out.println(contact.getName() + " : " + contact.getPhNo());
    }
}
