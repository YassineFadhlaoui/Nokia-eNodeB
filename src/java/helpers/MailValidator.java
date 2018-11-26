/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author yassine
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator {

    private final String Mail;
    private final Pattern pattern;
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Matcher matcher;

    public MailValidator(String Mail) {
        this.Mail = Mail;
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean isValid() {
        matcher = pattern.matcher(Mail);
        return matcher.matches();
    }
}
