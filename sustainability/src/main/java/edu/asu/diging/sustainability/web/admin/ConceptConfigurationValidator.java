package edu.asu.diging.sustainability.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import edu.asu.diging.sustainability.core.model.impl.Roles;

/**
 * @author Namratha 
 * Validator for the backing form in edit concept configuration page.
 */
public class ConceptConfigurationValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        return arg0 == Roles.class;
    }

    @Override
    public void validate(Object arg0, Errors arg1) {}
}
