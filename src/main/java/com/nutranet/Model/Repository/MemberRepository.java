package com.nutranet.Model.Repository;

import com.nutranet.Model.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    public boolean existsByMidAndMpw(String mid, String mpw);

    MemberEntity findByMid(String mid);
}
