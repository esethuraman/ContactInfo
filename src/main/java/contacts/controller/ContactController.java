package contacts.controller;


import contacts.ContactRepository;
import contacts.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ContactController {

    private ContactRepository contactRepository;

    @Autowired
    ContactController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getContacts(Model model){
        List<Contact> contacts = contactRepository.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitContact(Contact contact){
        contactRepository.save(contact);
        return "redirect:/";
    }
}
