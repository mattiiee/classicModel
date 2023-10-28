package sit.int202.classicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Employee;

import java.util.List;
import java.util.Scanner;

public class TestQueryParameter {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("EMPLOYEE.FIND_BY_NAME");
        Scanner sc = new Scanner(System.in);
        char choice = 'x';
        do {
            System.out.println("Search Employee by name:");
            String name = sc.next();
            choice = name.charAt(0);
            if(choice == '.'){
                break;
            }
            query.setParameter("first_name", name + "%");
            query.setParameter("last_name", name + "%");
            List<Employee> employeeList = query.getResultList();
            if(employeeList.isEmpty()){
                System.out.println("Employee name start with " + name + " does not exist!!!");
            }else{
                displayEmployees(employeeList);
            }
        } while (true);
    }
    private static void displayEmployees(List<Employee> employeeList) {
        for(Employee employee : employeeList) {
            System.out.printf("%4d %-10s %-12s %-15s\n",
                    employee.getEmpID(), employee.getFirstName(),
                    employee.getLastName(), employee.getJobTitle());
        }
    }
}
