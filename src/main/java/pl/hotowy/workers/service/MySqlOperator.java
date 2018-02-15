package pl.hotowy.workers.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hotowy.workers.ex.WrongEmployeeFieldException;
import pl.hotowy.workers.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MySqlOperator {

    @Autowired
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;




    public void save(Employee employee) throws WrongEmployeeFieldException {
        verifyEmployeeFields(employee);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Employee> selectAllEmployees(){
        List<Employee> result = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select e from Employee e");
        result.addAll(query.getResultList());
        return result;
    }

    public Employee selectEmployeebyId(String id){
        Employee result;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query= entityManager.createQuery("select e from Employee e WHERE id ="+id);
        result = (Employee) query.getSingleResult();
        return result;
    }

    public void UpdateEmployee(long id, String firstName, String lastname, String pesel, double salary, String employeedFrom) throws WrongEmployeeFieldException {
        verifyEmployeeFields(new Employee(firstName,lastname,pesel,salary,LocalDate.parse(employeedFrom)));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Employee employee1 = entityManager.find(Employee.class,id);

        employee1.setFirstName(firstName);
        employee1.setLastName(lastname);
        employee1.setPesel(pesel);
        employee1.setSalary(salary);
        employee1.setEmployedFrom(LocalDate.parse(employeedFrom));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void verifyEmployeeFields(Employee employee) throws WrongEmployeeFieldException {
        if    ( employee.getFirstName() == null || employee.getFirstName().equals("") ||
                employee.getLastName() == null || employee.getLastName().equals("") ||
                !employee.getPesel().matches("[0-9]+") || employee.getPesel().length()!=11){
            throw new WrongEmployeeFieldException();

        }
    }



}
