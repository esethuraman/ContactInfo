package contacts.controller;


import contacts.persistence.ContactRepository;
import contacts.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class ContactController {

    private ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getContacts(Map<String, Object> map){
        System.out.println("----------- reached get method in controller ------- ");
        List<Contact> contacts = contactRepository.getAllContacts();
        System.out.println("Contacts : " + contacts.size());
//        model.addAttribute("contacts", contacts);
        map.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submitContact(Contact contact){
        contactRepository.save(contact);
        return "redirect:/";
    }
}
