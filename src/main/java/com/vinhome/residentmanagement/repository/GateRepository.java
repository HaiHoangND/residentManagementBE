package com.vinhome.residentmanagement.repository;

import com.vinhome.residentmanagement.entity.Gate;
import com.vinhome.residentmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {
    @Query("select g from Gate g " +
            "WHERE g.deletedAt IS NULL " +
            "AND (:keyword is null or concat(g.name, g.address) LIKE %:keyword% )")
    List<Gate> findAllGates(String keyword);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.deletedAt IS NULL AND u.gate.id = :gateId")
    boolean workingPeopleExist(Long gateId);
}
