package com.my.foodsafe.repositories;

import com.my.foodsafe.pojo.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryRepository extends IBaseRepository<History, String> {
}
