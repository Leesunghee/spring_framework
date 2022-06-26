package com.himalaya.demo.himalaya.entity;

import com.himalaya.demo.himalaya.MemberDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository {

    Optional<List<Member>> findAll();

    Member save(Member member);


    Optional<Member> findByMemberId(String memberId);
}
