package projcet.familystory.form;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Data
public class LoginForm {

    @NotEmpty(message = "아이디는 필수입니다.")
    private String loginId;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

}
