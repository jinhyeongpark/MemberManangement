package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.SpringDataJpaMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MemberService {

    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;

    @Autowired
    public MemberService(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.springDataJpaMemberRepository = springDataJpaMemberRepository;
    }
    public Long join(Member member) {
        validateDuplicateMember(member);
        springDataJpaMemberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        springDataJpaMemberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    public List<Member> findMembers() {
        return springDataJpaMemberRepository.findAll();
    }
    public Optional<Member> fineOne(Long memberId) {
        return springDataJpaMemberRepository.findById(memberId);
    }
}
