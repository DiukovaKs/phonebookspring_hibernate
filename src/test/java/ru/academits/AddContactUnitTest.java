package ru.academits;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.academits.model.Contact;
import ru.academits.phonebook.PhoneBookController;
import ru.academits.service.ContactService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneBookController.class)
public class AddContactUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactService service;

   /* @Test
    public void givenPhone_whenGetContactPhone_thenReturnJsonArray()
            throws Exception {

        Contact testedContactPhoneNumber = new Contact();
        testedContactPhoneNumber.setId(2);
        testedContactPhoneNumber.setFirstName("Ivan");
        testedContactPhoneNumber.setLastName("Ivanov");
        testedContactPhoneNumber.setPhone("123456");

        List<Contact> allContacts = Arrays.asList(testedContactPhoneNumber);

        given(service.getAllContacts()).willReturn(allContacts);

        mvc.perform(get("/phoneBook/rpc/api/v1/getAllContacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phone", Matchers.is("123456")));
    }*/
}

