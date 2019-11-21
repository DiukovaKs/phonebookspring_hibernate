package ru.academits.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
//import ru.academits.dao.ContactDao;
import ru.academits.dao.ContactDaoImpl;
import ru.academits.model.Contact;
import ru.academits.model.ContactValidation;
import ru.academits.phonebook.PhoneBookController;

import java.util.Iterator;
import java.util.List;

@Service
public class ContactService {
    //private final ContactDao contactDao;
    private final ContactDaoImpl contactDaoImpl;
    private static final Logger log = LoggerFactory.getLogger(PhoneBookController.class);

    /* public ContactService(ContactDao contactDao) {
         this.contactDao = contactDao;
     }*/
    public ContactService(ContactDaoImpl contactDaoImpl) {
        this.contactDaoImpl = contactDaoImpl;
    }

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDaoImpl.findByPhone(phone);
        return !contactList.isEmpty();
    }

   /* private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }*/

    private ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (contact.getFirstName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Имя должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getLastName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Фамилия должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getPhone().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Телефон должно быть заполнено.");
            return contactValidation;
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setError("Номер телефона не должен дублировать другие номера в телефонной книге.");
            return contactValidation;
        }
        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            //contactDao.add(contact);
            contactDaoImpl.create(contact);
        }
        return contactValidation;
    }

    public List<Contact> getAllContacts() {
        //return contactDao.getAllContacts();
        return contactDaoImpl.getAllContacts();
    }

   /* public List<Contact> deleteContact(int contactId){
        contactDao.delete(contactId);
        return contactDao.getAllContacts();
    }*/

    public void deleteContact(Long contactId) {
        Contact contact = null;
        List<Contact> contactList = contactDaoImpl.getAllContacts();
        for (Contact e : contactList) {
            if (e.getId().equals(contactId)) {
                contact = e;
            }
        }
        contactDaoImpl.remove(contact);
        log.info("contact is deleted");
    }

    public List<Contact> filterContacts(String string) {
        List<Contact> contactList = contactDaoImpl.getAllContacts();

        for (Iterator<Contact> it = contactList.iterator(); it.hasNext(); ) {
            Contact e = it.next();
            if (!e.getFirstName().contains(string) && !e.getLastName().contains(string) && !e.getPhone().contains(string)) {
                it.remove();
            }
        }
        return contactList;
    }
}
