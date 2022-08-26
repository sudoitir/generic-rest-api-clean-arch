package ir.sudoit.core.crud.port.out;

import ir.sudoit.core.crud.port.dto.CrudResponse;
import ir.sudoit.core.crud.utility.PropertiesConfig;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MetaModel implements CrudResponse
{

    private Integer statusCode;
    private String code;
    private String message;

    public static MetaModel getInstance(PropertiesConfig propertiesConfig)
    {
        return new MetaModel(200,
                propertiesConfig.getResult("success-code"),
                propertiesConfig.getResult("success-text"));
    }
}
