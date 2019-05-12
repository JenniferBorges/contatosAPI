package com.dev.usersapi.validator;

import com.dev.usersapi.dto.ContactDto;
import com.dev.usersapi.enums.ContactType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;

public class ContactValidatorTest {
    
    private ContactValidator validator = new ContactValidator();
    
    @Test
    public void testEmailValid() {
        ContactDto dto = new ContactDto();
        dto.setContact("jennifer@hotmail.com");
        dto.setType(ContactType.EMAIL);

        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "contact");
        validator.validate(dto, errors);
        
        Assert.assertTrue("Contato deveria ser valido", errors.getFieldErrors("contact").isEmpty());
    }

    @Test
    public void testEmailInvalid() {
        ContactDto dto = new ContactDto();
        dto.setContact("jennifer");
        dto.setType(ContactType.EMAIL);

        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "contact");
        validator.validate(dto, errors);
        
        Assert.assertFalse("Contato deveria ser invalido", errors.getFieldErrors("contact").isEmpty());
    }

    @Test
    public void testPhoneInvalid() {
        ContactDto dto = new ContactDto();
        dto.setContact("jennifer");
        dto.setType(ContactType.PHONE);

        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "contact");
        validator.validate(dto, errors);
        
        Assert.assertFalse("Contato deveria ser invalido", errors.getFieldErrors("contact").isEmpty());
    }
    
    @Test
    public void testPhoneValid() {
        ContactDto dto = new ContactDto();
        dto.setContact("999887766");
        dto.setType(ContactType.PHONE);

        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "contact");
        validator.validate(dto, errors);
        
        Assert.assertTrue("Contato deveria ser valido", errors.getFieldErrors("contact").isEmpty());
    }
  
    @Test
    public void testPhoneValid_WithDDD() {
        ContactDto dto = new ContactDto();
        dto.setContact("(35)999887766");
        dto.setType(ContactType.PHONE);

        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "contact");
        validator.validate(dto, errors);
        
        Assert.assertTrue("Contato deveria ser valido", errors.getFieldErrors("contact").isEmpty());
    }
   
    @Test
    public void testPhoneInalid_IllegalChar() {
        ContactDto dto = new ContactDto();
        dto.setContact("9998dasd87766");
        dto.setType(ContactType.PHONE);

        final BeanPropertyBindingResult errors = new BeanPropertyBindingResult(dto, "contact");
        validator.validate(dto, errors);
        
        Assert.assertFalse("Contato deveria ser invalido", errors.getFieldErrors("contact").isEmpty());
    }

}
