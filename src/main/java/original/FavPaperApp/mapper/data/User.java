package original.FavPaperApp.mapper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String roles;

}
