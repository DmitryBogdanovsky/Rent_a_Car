package my.dao;

import my.model.car.BrandCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandCarRepository extends JpaRepository<BrandCar, Integer> {
}