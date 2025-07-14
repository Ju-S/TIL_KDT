package dao;

import dto.ContactDTO;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private List<ContactDTO> contactList = new ArrayList<>();

    public void addContact(ContactDTO contact){
        contactList.add(contact);
    }

    public List<ContactDTO> getContactList() {
        return contactList;
    }
}
