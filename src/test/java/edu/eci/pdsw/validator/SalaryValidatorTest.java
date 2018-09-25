package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.*;

import org.junit.Test;

import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTestEmployee() {
		qt().forAll(EmployeeGen.employees())
		.check( employee -> {
			Optional<ErrorType> err = validator.validate(employee);
			if(employee.getPersonId()<1000 || employee.getPersonId()>100000) {
				return err.equals(Optional.of(ErrorType.INVALID_ID));
			}
			if(employee.getSalary()<100 || employee.getSalary()>50000) {
				return err.equals(Optional.of(ErrorType.INVALID_SALARY));
			}
			if(employee.getSocialSecurityType()==SocialSecurityType.EPS) {
				if(employee.getSalary()>=1500) {
					return err.equals(Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION));
				}
			}
			if(employee.getSocialSecurityType()==SocialSecurityType.SISBEN){
				if(employee.getSalary()>=10000) {
					return err.equals(Optional.of(ErrorType.INVALID_EPS_AFFILIATION));
				}
			}
			if(err.equals(Optional.empty())) {
				return true;
			}
			return true;
		});
	}
}
