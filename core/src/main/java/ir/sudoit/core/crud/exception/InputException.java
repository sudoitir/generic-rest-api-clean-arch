package ir.sudoit.core.crud.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class InputException extends RuntimeException
{

    private final String code;

    private final String message;

}

