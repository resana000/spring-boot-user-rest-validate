package com.example.demo.service;

import com.example.demo.dto.UserCriteriaDto;
import com.example.demo.entity.User;
import com.example.demo.exception.DBFindException;
import com.example.demo.exception.UserDtoValidationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DBFindException(String.format("user=%d not found", id)));
    }

    @Transactional
    public User create(User user, String xSource) {
        validate(user, xSource);
        return userRepository.save(user);
    }

    public User search(UserCriteriaDto dto) {
        return userRepository.findUserByNameAndPhoneAndEmail(
                dto.getName(), dto.getLastName(), dto.getSurname(), dto.getPhoneNumber(), dto.getEmail()
        );
    }

    private void validate(User user, String xSource) {
        Validator validator = Validation
                .buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(user, UserValidator.X_SOURCE_MAP.get(xSource));
        if (!constraintViolations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            constraintViolations.forEach(a -> errors.put(a.getPropertyPath().toString(), a.getMessageTemplate()));
            throw new UserDtoValidationException(errors);
        }
    }
}
