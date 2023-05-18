package com.example.demo.validators;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    public static HashMap<String, Class> X_SOURCE_MAP = new HashMap<>(Map.of(
            "mail", CaseByMail.class,
            "mobile", CaseByMobile.class,
            "bank", CaseByBank.class,
            "gosuslugi", CaseByGosuslugi.class));

    public interface CaseByMail {
    }

    public interface CaseByMobile {
    }

    public interface CaseByBank {
    }

    public interface CaseByGosuslugi {
    }
}
