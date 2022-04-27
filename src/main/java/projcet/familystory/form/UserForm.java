package projcet.familystory.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserForm {

    @NotEmpty(message = "아이디는 필수 입니다")
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String passWord;

    @NotEmpty(message = "이름은 필수 입니다")
    private String name;

    @NotEmpty(message = "닉네임은 필수 입니다")
    private String nickName;

    @NotEmpty(message = "E-mail은 필수 입니다")
    private String email;

    @NotEmpty(message = "휴대폰 번호는 필수 입니다")
    private String phoneNumber;

    @NotEmpty(message = "생년월일은 필수 입니다")
    private String birthDay;


}
