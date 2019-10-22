package ru.academits.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.academits.service.ContactService;

@Component
public class DeleteRandomContactScheduler {
    private final ContactService contactService;

    public DeleteRandomContactScheduler(ContactService contactService) {
        this.contactService = contactService;
    }

    @Scheduled(fixedRate = 10000)
    public void deleteRandomContact() {
        int max = contactService.getAllContacts().get(contactService.getAllContacts().size() - 1).getId();
        int rnd = (int) (Math.random() * max);

        contactService.deleteContact(rnd);
        System.out.println("max id number " + max + " delete Contact with id = " + rnd);
    }
}
