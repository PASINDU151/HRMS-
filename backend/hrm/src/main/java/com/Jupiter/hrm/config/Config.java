package com.Jupiter.hrm.config;
import com.Jupiter.hrm.repository.EmployeeExtensionRepo;
import com.Jupiter.hrm.repository.EmployeeRepo;
import com.Jupiter.hrm.repository.LeaveTypeRepository;
import com.Jupiter.hrm.repository.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserRepo userRepo(){return new UserRepo();}

    @Bean
    public EmployeeRepo employeeRepo(){return new EmployeeRepo();}

    @Bean
    public EmployeeExtensionRepo employeeExtensionRepo(){return new EmployeeExtensionRepo();}

    @Bean
    public LeaveTypeRepository leaveTypeRepository(){ return new LeaveTypeRepository();}

}
