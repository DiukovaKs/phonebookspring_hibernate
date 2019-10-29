package ru.academits.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academits.phonebook.PhoneBookController;
import ru.academits.service.ContactService;

@Component
public class DeleteRandomContactScheduler {
    private final ContactService contactService;
    private static final Logger log = LoggerFactory.getLogger(DeleteRandomContactScheduler.class);

    public DeleteRandomContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(fixedRate = 10000)
    public void deleteRandomContact() {
        int max = contactService.getAllContacts().get(contactService.getAllContacts().size() - 1).getId();
        int rnd = (int) (Math.random() * max);

        contactService.deleteContact(rnd);
        System.out.println("max id number " + max + " delete Contact with id = " + rnd);
        log.info("delete contact with Id " + rnd + " by Scheduler");
    }
}
