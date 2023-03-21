package com.nutranet.Model.Repository;

import com.nutranet.Model.Entity.BoardEntity;
import com.nutranet.Model.Entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {
}
