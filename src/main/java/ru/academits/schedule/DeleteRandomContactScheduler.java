package ru.academits.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academits.service.ContactService;

import javax.transaction.Transactional;

@Component
public class DeleteRandomContactScheduler {
    private final ContactService contactService;
    private static final Logger log = LoggerFactory.getLogger(DeleteRandomContactScheduler.class);

    public DeleteRandomContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(fixedRate = 7000)
    @Transactional
    public void deleteRandomContact() {
        Long max = contactService.getAllContacts().get(contactService.getAllContacts().size() - 1).getId();

        Long rnd = new Long((int) Math.round((Math.random() * max)));

        log.info("delete contact with Id " + rnd + " by Scheduler");

        contactService.deleteContact(rnd);
        System.out.println("max id number " + max + " delete Contact with id = " + rnd);
    }
}
