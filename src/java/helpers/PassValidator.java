/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author yassine
 */
public class PassValidator {

    public static List ValidatePass(String pass) {
        List<String> errors = new ArrayList<>();
        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (pass.length() < 8) {
            errors.add("Password lenght must have at least 8 character !!");

        }
        if (!specailCharPatten.matcher(pass).find()) {
            errors.add("Password must have at least one specail character !!");

        }
        if (!UpperCasePatten.matcher(pass).find()) {
            errors.add("Password must have at least one uppercase character !!");

        }
        if (!lowerCasePatten.matcher(pass).find()) {
            errors.add("Password must have atleast one lowercase character !!");
        }
        if (!digitCasePatten.matcher(pass).find()) {
            errors.add("Password must have at least one digit character !!");
        }
        return errors;
    }
}
