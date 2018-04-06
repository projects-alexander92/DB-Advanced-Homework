package app.enitties.validation;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class EntitiesValidatorImpl implements EntitiesValidator
{
    private Validator validator;

    public EntitiesValidatorImpl()
    {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T t)
    {
        return t != null && validator.validate(t).size() == 0;
    }
}
