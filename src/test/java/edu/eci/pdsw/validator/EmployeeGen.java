package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;
import  edu.eci.pdsw.model.*;
import  edu.eci.pdsw.validator.*;

public class EmployeeGen {

	public static Gen<Employee> employees() {
	    return ids().zip(salaries(), socialsecurity(),
	        (id, salary, socialtype) -> new Employee(id, salary, socialtype));
	}
	
	private static Gen<Integer> ids() {
	    return integers().allPositive();
	}
	
	private static Gen<Integer> salaries() {
	    return integers().allPositive();
	}
	
	private static Gen<SocialSecurityType> socialsecurity() {
	    return Generate.enumValues(SocialSecurityType.class);
	}
	
}