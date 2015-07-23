package as.athen.springmvc.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import as.athen.springmvc.domain.Member;
import as.athen.springmvc.repo.MemberDao;

@Controller
@RequestMapping("/rest/members")
public class MemberRestController
{
    @Autowired
    private MemberDao memberDao;

/* */   @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<Member> listAllMembers()
    {
        return memberDao.findAllOrderedByName();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody Member lookupMemberById(@PathVariable("id") Long id)
    {
        return memberDao.findById(id);
    }
    
    
    
    @RequestMapping(value="/all", method=RequestMethod.GET, produces="application/json")
	public  @ResponseBody List<Member> getAnnuaire() {
		 return memberDao.findAllOrderedByName();
	}
	
    /*@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	public  @ResponseBody  Member getMemberByID (@PathVariable("id") Long id){
    	 return memberDao.findById(id);
	}*/
   
    
	
	
    @RequestMapping(value="/id", method=RequestMethod.PUT,produces="application/json",  consumes="application/json")
	public @ResponseBody int addMember (@RequestBody Member member) {
		return memberDao.addMember(member);
	}

	/*@Path("/id/{id}")
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})*/
    @RequestMapping(value="/id/{id}", method=RequestMethod.DELETE, produces="application/json")
	public @ResponseBody int deleteMember (@PathVariable("id") Long index) {
		return memberDao.deleteMember(index);
	}

	/*@Path("/id/{id}")
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})*/
    @RequestMapping(value="/id/{id}", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody int updateMember (@PathVariable("id") Long index,@RequestBody Member member) {
		return memberDao.updateMember(index, member);
	}    
    
}
