package ru.rksp.zhilkinalera_processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rksp.zhilkinalera_processor.entity.ParkingEntity;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {
}