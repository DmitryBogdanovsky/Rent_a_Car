package my.service;


import my.dao.ModelCarRepository;
import my.dto.ModelCarDto;

import my.exception.MyException;
import my.model.car.BrandCar;
import my.model.car.ModelCar;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class ModelCarServiceImpl implements ModelCarService {


    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ModelCarRepository modelCarRepository;


    @Override
    @Transactional
    public ModelCarDto addModelCar(ModelCarDto newModelCar) {

        ModelCar modelCar = modelMapper.map(newModelCar, ModelCar.class);
        if (modelCar.getId() != null && modelCarRepository.existsById(modelCar.getId())) {
            throw new MyException("Model already exists! Id: " + modelCar.getId());
        }
        return modelMapper.map(modelCarRepository.save(modelCar), ModelCarDto.class);
    }


    @Override
    @Transactional
    public ModelCarDto updateModelCar(ModelCarDto updateModelCarDto) {

        ModelCar modelCar = modelCarRepository.findById(updateModelCarDto.getId())
                .orElseThrow(() -> new MyException("Model car not found!: " + updateModelCarDto.getId()));

        modelCar.setModelName(updateModelCarDto.getModelName());
        modelCar.setBrandCar(modelMapper.map(updateModelCarDto.getBrandCar(), BrandCar.class));

        return modelMapper.map(modelCarRepository.save(modelCar), ModelCarDto.class);
    }

    @Override
    @Transactional
    public void deleteModelCar(Integer id) {

        if (!modelCarRepository.existsById(id))
            throw new MyException("Model car not found!: " + id);

        modelCarRepository.deleteById(id);

    }

    @Override
    @Transactional
    public ModelCarDto findModelCarById(Integer id) {

         ModelCar modelCar = modelCarRepository.findById(id)
                .orElseThrow(() -> new MyException("Model car not found!: " + id));

        return modelMapper.map(modelCar, ModelCarDto.class);
    }


    @Override
    @Transactional
    public List<ModelCarDto> findAllModelCarsPages(int page, int size) {
        return modelCarRepository.findAll(PageRequest.of(page, size)).stream()
                .map(modelCar -> modelMapper.map(modelCar, ModelCarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public int countAllModelsByBrand(int brandCarId) {
        return modelCarRepository.countByBrandCar_Id(brandCarId);
    }

    @Override
    @Transactional
    public List<ModelCarDto> findAllModelsPagesByBrandCar(int brandCarId, int page, int size) {
        return modelCarRepository.findAllByBrand(brandCarId, PageRequest.of(page, size)).stream()
                .map(modelCar -> modelMapper.map(modelCar, ModelCarDto.class))
                .collect(Collectors.toList());
    }
}
