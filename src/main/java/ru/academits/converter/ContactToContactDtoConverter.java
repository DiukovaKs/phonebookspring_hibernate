package ru.academits.converter;

import org.springframework.stereotype.Service;
import ru.academits.dto.ContactDto;
import ru.academits.model.Contact;

@Service
public class ContactToContactDtoConverter extends AbstractConverter<Contact, ContactDto> {

    @Override
    public ContactDto convert(Contact source){
        ContactDto contact = new ContactDto();

        contact.setId(source.getId());
        contact.setLastName(source.getLastName());
        contact.setFirstName(source.getFirstName());
        contact.setPhone(source.getPhone());

        return contact;
    }
}
