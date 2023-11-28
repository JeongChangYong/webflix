package webflix.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webflix.mapper.MemberMapper;

@Service
public class MembersDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memDels []) {
		memberMapper.membersDelete(memDels);
		
	}
}
