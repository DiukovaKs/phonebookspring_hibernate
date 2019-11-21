package ru.academits.dao;

import ru.academits.model.Contact;

import java.util.List;

public interface InterfaceDao extends GenericDao<Contact,Long> {
    List<Contact> getAllContacts();

    List<Contact> findByPhone(String phone);
}
