package projcet.familystory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projcet.familystory.domain.User;

import java.util.List;
import java.util.Optional;

import projcet.familystory.repository.UserRepository;
import projcet.familystory.form.FindPwForm;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Async;

@Service
@EnableAsync
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    @Transactional(readOnly = false)
    public Optional<User> join(User user) {

        List<User> findUsers = userRepository.findUserId(user.getUserId());
        if (findUsers.isEmpty()){
            userRepository.save(user);
            return Optional.of(user);
            //throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        return null;
    }

    public User findByUid(Long Uid){
        return userRepository.findOne(Uid);
    }



    //회원 전체 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }


    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId).filter(u -> u.getPassWord().equals(password))
                .orElse(null);

    }
    //아이디찾기
    public Optional<User> findId(User user) {
        List<User> findUsers = userRepository.findAll();
        for(User u : findUsers){
            //name, email 이 일치해야한다.
            if(user.getName().equals(u.getName())&&user.getEmail().equals(u.getEmail())) {
                return Optional.of(u);
            }
        }
        return null;
    }
    //비밀번호 찾기
    public Optional<User> findPw(User user) {
        List<User> findUsers = userRepository.findAll();
        for(User u : findUsers){
            //name, email, userid가 모두 일치해야한다.
            if(user.getName().equals(u.getName())&&user.getEmail().equals(u.getEmail())&&user.getUserId().equals(u.getUserId())) {
                return Optional.of(u);
            }
        }
        return null;
    }





    //메일보내기
    @Autowired // JavaMailSender 사용 위해 Autowired 필요
    private JavaMailSender javaMailSender;//build.gradle - implementation 'org.springframework.boot:spring-boot-starter-mail'
    private static final String FROM_ADDRESS = "multicampusgroup6@gmail.com";//송신 이메일

    @Async                  //없으면 메일이 보내지기전까지 다음 작업 수행하지 않음 (페이지 전환 지연 방지를 위함)
    public void mailSend(FindPwForm findPwForm){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(findPwForm.getAddress());                     //수신자
        message.setFrom(FROM_ADDRESS);                              //FROM_ADDRESS가 발신자.
        message.setSubject(findPwForm.getTitle());                  //메일 제목
        message.setText(findPwForm.getMessage());                   //메일 내용

        javaMailSender.send(message);
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    //패스워드 수정
    public void editPassword(Long uId, String str) {
        userRepository.editPassword(uId, str);
    }

}
