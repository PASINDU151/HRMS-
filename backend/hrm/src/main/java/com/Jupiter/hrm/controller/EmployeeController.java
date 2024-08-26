package com.Jupiter.hrm.controller;
import com.Jupiter.hrm.entity.Employee;
import com.Jupiter.hrm.dto.EmployeeDto;
import com.Jupiter.hrm.entity.EmployeeExtension;
import com.Jupiter.hrm.service.EmployeeExtensionService;
import com.Jupiter.hrm.service.EmployeeService;
import com.Jupiter.hrm.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeExtensionService employeeExtensionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeExtensionService employeeExtensionService) {
        this.employeeService = employeeService;
        this.employeeExtensionService = employeeExtensionService;
    }


    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public HttpStatus createEmployee(@RequestBody EmployeeDto employeeDto) {
        int employee_id = employeeDto.getEmployee_id();
        employeeService.createEmployee(modelMapper.map(employeeDto, Employee.class));
        employeeExtensionService.createEmployeeExtension(employeeDto.getInt_attributes(), employeeDto.getStr_attributes(), employeeDto.getDouble_attributes());
        if(employeeDto.getInt_attributes() != null){
            for (Map.Entry<String, Integer> entry : employeeDto.getInt_attributes().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }

        }
        if(employeeDto.getStr_attributes() != null){
            for (Map.Entry<String, String> entry : employeeDto.getStr_attributes().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }

        }
        if(employeeDto.getDouble_attributes() != null){
            for (Map.Entry<String, Double> entry : employeeDto.getDouble_attributes().entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }

        }
        return HttpStatus.CREATED;
    }


    @PostMapping("/add-attribute")
    public HttpStatus addAttribute(@RequestBody Map<String, String> attributes) {
        employeeExtensionService.addAttributes(attributes.get("attribute"), attributes.get("constraint"));
        return HttpStatus.OK;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employee = modelMapper.map(employeeService.getEmployeeById(id), EmployeeDto.class);
        return new ResponseEntity<>(employee, HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public HttpStatus updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto updatedEmployee) {
        if(employeeService.updateEmployee(id, modelMapper.map(updatedEmployee, Employee.class))){
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody EmployeeDto employeeDto) {
        return null;
    }

}

