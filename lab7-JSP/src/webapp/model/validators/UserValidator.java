package webapp.model.validators;

import webapp.model.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class UserValidator {
    public static String validate(User entity) {
        StringBuilder errors = new StringBuilder();
        if (entity.getUsername().length()<3) errors.append("Username too short!\n");
        if (entity.getPassword().length()<5) errors.append("Password too short!\n");
        if (!entity.getPassword().matches(".*\\d.*")) errors.append("Password should contain at least a digit!\n");
        if (entity.getUsername().equals(entity.getPassword())) errors.append("Username and Password shouldn't be equal!\n");
        try {
            InternetAddress currentMail = new InternetAddress(entity.getMail());
            currentMail.validate();
        } catch (AddressException e) {
            errors.append("The mail address entered is not valid!");
        }
        return errors.toString();
    }
}
