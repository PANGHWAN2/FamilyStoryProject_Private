package projcet.familystory.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import projcet.familystory.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long uid) {
        log.info("INFO");
        System.out.println("uid = " + uid);
        List<User> all = findAll();
        for (User u : all) {
            if (u.getUId().equals(uid)) {
                System.out.println("u.getUId() = " + u.getUId());
                return u; //optional 객체는 껍데기 통인데 null이 들어갈수 있어
            }
        }
        return null;
    }


    public List<User> findAll(){
        List<User> result = em.createQuery("select u from User u", User.class)
                .getResultList();

        return result;
    }

    public List<User> findUserId(String userId) {
        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<User> findUserPw(String passWord) {
        return em.createQuery("select u from User u where u.passWord = :passWord", User.class)
                .setParameter("passWord", passWord)
                .getResultList();
    }

    public Optional<User> findUserByUserId(String loginId){
        return findAll().stream()
                .filter(u -> u.getUserId().equals(loginId))
                .findFirst();
    }


    public Optional<User> findByLoginId(String loginId){
        return findAll().stream()
                .filter(u -> u.getUserId().equals(loginId))
                .findFirst();
    }

    //패스워드 수정
    public void editPassword(Long uid, String newPassword){

        String s = "update User u set u.passWord = :newPassword where u.uId = :uid";
        em.createQuery(s).setParameter("newPassword",newPassword).setParameter("uid", uid).executeUpdate();

    }

}


