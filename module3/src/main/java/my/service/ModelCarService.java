package my.service;


import my.dto.ModelCarDto;

import java.util.List;

public interface ModelCarService {


    ModelCarDto addModelCar(ModelCarDto newModelCarDto);


    ModelCarDto updateModelCar(ModelCarDto updateModelCarDto);


    void deleteModelCar(Integer id);



    ModelCarDto findModelCarById(Integer id);

    List<ModelCarDto> findAllModelCarsPages(int page, int size);

    int countAllModelsByBrand(int brandCarId);

    List<ModelCarDto> findAllModelsPagesByBrandCar(int brandCarId, int page, int size);

}
