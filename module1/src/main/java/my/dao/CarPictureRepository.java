package my.dao;

import my.model.car.CarPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarPictureRepository extends JpaRepository<CarPicture, Integer> {
    @Query(value = "SELECT carPicture_id FROM car_picture WHERE car_id = :carId", nativeQuery = true)
    List<Integer> getAllPictureIdByCarId(@Param("carId") Integer carId);
}