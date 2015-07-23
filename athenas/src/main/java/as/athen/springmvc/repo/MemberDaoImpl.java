package as.athen.springmvc.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import as.athen.springmvc.domain.Member;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao
{
    @PersistenceContext
    private EntityManager em;

    public Member findById(Long id)
    {
        return em.find(Member.class, id);
    }

    public Member findByEmail(String email)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(member).where(builder.equal(member.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Member> findAllOrderedByName()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        /*
         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
         */

        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    public void register(Member member)
    {
        em.persist(member);
    }
   @Transactional(propagation= Propagation.REQUIRED)
    public int addMember (Member member) {
		try {
			register(member);
		} catch (Exception e) {
			return 0;
		}		
		return 1;
	}

   @Transactional(propagation= Propagation.REQUIRED)
	public int deleteMember(Long index) {
		try {
			Member member = this.findById(index);
			em.remove(member);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

   @Transactional(propagation= Propagation.REQUIRED)
	public int updateMember(Long index, Member member) {
		try {
			Member memberDb = this.findById(member.getId());
			memberDb = member;
			em.merge(memberDb);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
