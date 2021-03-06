package pl.hotowy.workers.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hotowy.workers.ex.WrongEmployeeFieldException;
import pl.hotowy.workers.model.Employee;
import pl.hotowy.workers.service.MySqlOperator;
import java.time.LocalDate;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private MySqlOperator operator;



    @RequestMapping("/")
    public String index(Model model){
        List<Employee> employees = operator.selectAllEmployees();
        model.addAttribute("employees",employees);
        return "index";
    }

    @GetMapping("/info/{id}")
    public String info(Model model,
                       @PathVariable String id){
        model.addAttribute("emp",operator.selectEmployeebyId(id));
    return "info";
    }

    @GetMapping("/edycja/{id}")
    public String edit(Model model, @PathVariable String id){
        model.addAttribute("emp",operator.selectEmployeebyId(id));
        return "edit";
    }
    @PostMapping("/edycja/{id}")
    public String edit( @PathVariable String id,
                        @RequestParam(value = "firstName") String firstName,
                        @RequestParam(value = "lastName") String lastName,
                        @RequestParam(value = "pesel") String pesel,
                        @RequestParam(value = "salary") double salary,
                        @RequestParam(value = "employeedFrom") String employeedFrom){

        try {
            operator.UpdateEmployee(Long.parseLong(id), firstName, lastName, pesel, salary, employeedFrom);
        } catch (WrongEmployeeFieldException e) {
            return "refirect:/error";
        }
        return "redirect:/";

    }

    @GetMapping("/dodaj")
    public String dodaj(){

        return "dodaj";
    }

    @PostMapping("/dodaj")
    public String dodany( @RequestParam(value = "firstName") String firstName,
                          @RequestParam(value = "lastName") String lastName,
                          @RequestParam(value = "pesel") String pesel,
                          @RequestParam(value = "salary") double salary,
                          @RequestParam(value = "employeedFrom") String employeedFrom){

        try {
            operator.save(new Employee(firstName, lastName, pesel, salary, LocalDate.parse(employeedFrom)));
        } catch (WrongEmployeeFieldException e) {
            return "redirect:/error";
        }

        return "redirect:/";
    }

    @GetMapping("/error")
    public static String error(){

        return "error";
    }
}
