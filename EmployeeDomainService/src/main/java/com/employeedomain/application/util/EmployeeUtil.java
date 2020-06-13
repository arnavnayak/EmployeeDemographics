package com.employeedomain.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.employeedomain.application.dto.EmployeeDto;
import com.employeedomain.application.entity.EmployeePersonalDetails;
import com.employeedomain.application.model.EmployeeRequestModel;
import com.employeedomain.application.model.EmployeeRequestUpdateModel;
import com.employeedomain.application.model.EmployeeResponseModel;
import com.employeedomain.application.model.EmployeesAll;

public class EmployeeUtil {
	
	private static SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
	private static ModelMapper mapper=new ModelMapper();

	
	public static Date parseDateOfJoinning(String dateOfJoining) throws ParseException {
		return formatter.parse(dateOfJoining);
	}
	
	public static String formatDateOfJoining(Date dateOfJoining ) {
		return formatter.format(dateOfJoining);
	}
	public static Object modelMapping(Object obj) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		/* Need to add support for casting based on object to be parsed*/
		if(obj instanceof EmployeeRequestModel) {
			return mapper.map(obj, EmployeeDto.class);
		}else if(obj instanceof EmployeeDto) {
			return mapper.map(obj, EmployeePersonalDetails.class);
		}else if(obj instanceof EmployeePersonalDetails) {
			return mapper.map(obj, EmployeeDto.class);

		}else if(obj instanceof EmployeeRequestUpdateModel) {
			return mapper.map(obj, EmployeeDto.class);

		}
		
		return null;
	}
	
	public static EmployeeResponseModel modelMappingResponse(EmployeeDto empDto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(empDto, EmployeeResponseModel.class);
	}
	
	public static List<EmployeePersonalDetails> iterableToListConverter(Iterable<EmployeePersonalDetails> iterable){
		List<EmployeePersonalDetails> empEntities=(List<EmployeePersonalDetails>) StreamSupport.stream(iterable.spliterator(), false)
				.collect(Collectors.toList());
		
		return empEntities;
		
		
	}
	public static EmployeesAll setEmpDto(List<EmployeeDto> returnEmpDetails) {
		EmployeesAll allEmp=new EmployeesAll();
		allEmp.setEmployeeDto(returnEmpDetails);
		return allEmp;
	}

}
