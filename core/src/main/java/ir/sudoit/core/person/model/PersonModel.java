package ir.sudoit.core.person.model;

import ir.sudoit.core.crud.model.LongIdModel;
import ir.sudoit.core.crud.port.dto.CrudRequest;
import ir.sudoit.core.crud.port.dto.CrudResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode (callSuper = true)
public class PersonModel extends LongIdModel implements CrudRequest, CrudResponse
{

    private String name;

    private String mobile;
}
