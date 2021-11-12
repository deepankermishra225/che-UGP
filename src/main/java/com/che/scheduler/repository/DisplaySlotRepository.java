package com.che.scheduler.repository;

import com.che.scheduler.models.DisplaySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisplaySlotRepository extends JpaRepository<DisplaySlot, Integer> {

    List<DisplaySlot> findBySemester(String semester);

    List<DisplaySlot> findBySlotType(String slotType) ;

    List<DisplaySlot> findBySemesterAndSlotType(String semester , String slotType) ;


}
