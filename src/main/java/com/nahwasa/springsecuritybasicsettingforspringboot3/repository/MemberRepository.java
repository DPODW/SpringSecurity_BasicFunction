package com.nahwasa.springsecuritybasicsettingforspringboot3.repository;

import com.nahwasa.springsecuritybasicsettingforspringboot3.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
@Mapper
public interface MemberRepository {

    Optional<Member> findByUserid(String userId);

    void save(Member member);
}
