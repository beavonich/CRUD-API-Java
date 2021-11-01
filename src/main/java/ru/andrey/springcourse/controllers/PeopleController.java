package ru.andrey.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.andrey.springcourse.dao.PersonDAO;
import ru.andrey.springcourse.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("people")
public class PeopleController {
    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.allPeople());
        return "people/allPeople";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.onePerson(id));
        return "people/onePerson";
    }

    @GetMapping("new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.add2(person);
        return "redirect:/people";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.onePerson(id));
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult,
                         @ModelAttribute("id") int id){
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
