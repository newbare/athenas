package as.athen.springmvc.repo;

import java.util.List;

import as.athen.springmvc.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
    
    public int addMember (Member member) ;

	public int deleteMember(Long index);

	public int updateMember(Long index, Member member);
}
