package com.example.demo.repository;

import com.example.demo.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {
    //����Ͻ� �䱸���� ����
    Optional<Member> findByName(String name);
}
