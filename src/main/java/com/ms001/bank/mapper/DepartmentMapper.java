package com.ms001.bank.mapper;

import com.ms001.bank.dto.request.BranchCreateRequestDTO;
import com.ms001.bank.dto.request.BranchUpdateRequestDTO;
import com.ms001.bank.dto.request.DepartmentCreateRequestDTO;
import com.ms001.bank.dto.request.DepartmentUpdateRequestDTO;
import com.ms001.bank.dto.response.BranchResponseDTO;
import com.ms001.bank.dto.response.DepartmentResponseDTO;
import com.ms001.bank.entity.Branch;
import com.ms001.bank.entity.Department;
import com.ms001.bank.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    Department mapDepartmentCreateRequestDTOToDepartmentEntity(DepartmentCreateRequestDTO departmentCreateRequestDTO);
    Department mapDepartmentUpdateRequestDTOToDepartmentEntity(DepartmentUpdateRequestDTO departmentUpdateRequestDTO);
    @Mappings({
            @Mapping(target = "bankName", source = "department.bank.name"),
            @Mapping(target = "employeeIds", source = "department.employees")

    })
    DepartmentResponseDTO mapDepartmentEntityToDepartmentResponseDTO(Department department);
    default List<Long> mapEmployeeListToLongList(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
    }

}
