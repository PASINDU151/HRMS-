package com.Jupiter.hrm.config;
import com.Jupiter.hrm.entity.LeaveApplication;
import com.Jupiter.hrm.repository.*;
import com.Jupiter.hrm.security.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Bean
    public EmployeeLeaveBalanceRepository employeeLeaveBalanceRepository(){return new EmployeeLeaveBalanceRepository();}

    @Bean
    public LeaveApplicationRepo leaveApplicationRepo(){return new LeaveApplicationRepo();}

    @Bean
    public PasswordHasher passwordHasher(){return new PasswordHasher();}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){return new BCryptPasswordEncoder();}
}
