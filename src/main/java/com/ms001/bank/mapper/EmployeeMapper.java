package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.DepartmentCreateRequestDTO;
import com.ms001.bank.dto.request.DepartmentUpdateRequestDTO;
import com.ms001.bank.dto.request.EmployeeCreateRequestDTO;
import com.ms001.bank.dto.request.EmployeeUpdateRequestDTO;
import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.dto.response.EmployeeResponseDTO;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    Employee mapEmployeeCreateRequestDTOToEmployeeEntity(EmployeeCreateRequestDTO employeeCreateRequestDTO);
    Employee mapEmployeeUpdateRequestDTOToEmployeeEntity(EmployeeUpdateRequestDTO employeeUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "departmentId", source = "employee.department.id")
    })
    EmployeeResponseDTO mapEmployeeEntityToEmployeeResponseDTO(Employee employee);
}
