package projcet.familystory.repository;

import org.springframework.stereotype.Repository;
import projcet.familystory.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Team team) {
        em.persist(team);
    }

    public List<Team> findAll(){
        List<Team> result = em.createQuery("select t from Team t", Team.class)
                .getResultList();

        return result;
    }
}
