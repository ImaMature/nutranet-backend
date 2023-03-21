package com.nutranet.Model.Repository;

import com.nutranet.Model.Entity.BoardEntity;
import com.nutranet.Model.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
}
