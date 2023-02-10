package my.service;


import my.dto.BrandCarDto;

import org.springframework.transaction.annotation.Transactional;


import java.util.List;


public interface BrandCarService {

    @Transactional
    BrandCarDto addBrand(BrandCarDto brandCarDto);

    @Transactional
    BrandCarDto updateBrandCar(BrandCarDto updatedBrandCar);

    @Transactional
    void deleteBrandCar(Integer id);

    @Transactional
    BrandCarDto findBrandCarById(Integer id);

    long countAllBrandsCar();
    @Transactional
    List<BrandCarDto> findAllBrandsCarPages(int page, int size);

}
