package com.ruslanburduzhan.spring.rest;

import com.ruslanburduzhan.spring.rest.configuration.MyConfig;
import com.ruslanburduzhan.spring.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communicationBean", Communication.class);

//        List<Employee> employeeList = communication.getAllEmployees();
//        for (Employee q : employeeList) {
//            System.out.println(q);
//        }
//        **********************************************************
//        System.out.println(communication.getEmployee(1));
//        **********************************************************
//        Employee employee = new Employee("test_client","test_client","test_client",3);
//        employee.setId(15);
//        communication.saveEmployee(employee);
//        **********************************************************
//        communication.deleteEmployee(17);

        context.close();

    }
}
