package com.mindata.blockmanager.manager;

import com.mindata.blockmanager.model.Member;
import com.mindata.blockmanager.repository.MemberRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuweifeng wrote on 2018/3/7.
 */
@Component
public class MemberManager {
    @Resource
    private MemberRepository memberRepository;

    public int checkIdAndIp(String name, String id, String ip) {
        List<Member> members = memberRepository.findByName(name);
        //客户不存在
        if (members.size() == 0) {
            return -1;
        } else if (!members.get(0).getMemberId().equals(id)) {
            //id错误
            return -2;
        } else if (!members.stream().map(Member::getIp).collect(Collectors.toSet()).contains(ip)) {
            //ip错误
            return -3;
        }
        return 0;
    }
}
