package BackEnd.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private String street;


}
