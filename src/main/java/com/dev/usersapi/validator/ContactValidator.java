package com.dev.usersapi.validator;

import com.dev.usersapi.dto.ContactDto;
import com.dev.usersapi.entity.Contact;
import com.dev.usersapi.enums.ContactType;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContactValidator implements Validator {

    private final String EMAIL_PATTERN = "^[\\w\\.-]+@\\w+\\.\\w{2,6}$";
    private final String PHONE_PATTERN = "(\\(\\d{2}\\))?\\s?\\d{4,5}-?\\d{4}";
    @Override
    public boolean supports(Class<?> clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return ;
        }
        ContactDto contact = (ContactDto) target;

        if (ContactType.EMAIL == contact.getType()) {
            if (!Pattern.matches(EMAIL_PATTERN, contact.getContact())) {
                errors.rejectValue("contact", "contact.email.invalid", "Invalid e-mail");
            }
        } else {
            if (!Pattern.matches(PHONE_PATTERN, contact.getContact())) {
                errors.rejectValue("contact", "contact.phone.invalid", "Invalid phone number");
            }
        }
    }

}
