package com.ruslanburduzhan.spring.rest;

import com.ruslanburduzhan.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("communicationBean")
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/spring_course_rest/api/employees";

    public List<Employee> getAllEmployees() {

         /*ResponseEntity - это обертка http response. Применяется, если нужно что-то вернуть.
            в <> - указываем какого типа будет тело response(т.е. то, что возвращаем)*/

        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Employee>>() {
                });
        /*
        1. по этому адресу
        2. http метод, который используем
        3. тело http request
        4. вспомогательный класс, для передачи generic типа
        */

        List<Employee> allEmployees = responseEntity.getBody();
        return allEmployees;
    }

    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return employee;
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            /*в теле ResponseEntity будет возвращаться Employee в виде
                Json формата, а это String. Поэтому указываем тип String.*/
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, employee, String.class);
            /*
            1. по этому адресу
            2. Что мы добавляем в тело метода Post
            3. Какой тип мы ожидавем
            */

            System.out.println("New employee was adder to DB");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);

            System.out.println("Employee with ID = " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with ID = " + id + " was deleted");
    }
}
