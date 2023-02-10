package my.dao;


import my.model.car.ModelCar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelCarRepository extends JpaRepository<ModelCar, Integer> {

    int countByBrandCar_Id(int id);

    @Query("SELECT m FROM ModelCar m WHERE m.brandCar.id = ?1")
    Page<ModelCar> findAllByBrand(int id, Pageable pageable);









}