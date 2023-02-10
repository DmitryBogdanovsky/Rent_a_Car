package my.service;

import my.dao.BrandCarRepository;
import my.dto.BrandCarDto;
import my.exception.MyException;
import my.model.car.BrandCar;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandCarServiceImpl implements BrandCarService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandCarRepository brandCarRepository;


    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public BrandCarDto addBrand(BrandCarDto newBrandCar) {

        BrandCar brandCar = modelMapper.map(newBrandCar, BrandCar.class);
        if (brandCar.getId() != null && brandCarRepository.existsById(brandCar.getId())) {
            throw new MyException("Brand car already exists!: " + brandCar.getId());
        }
        return modelMapper.map(brandCarRepository.save(brandCar), BrandCarDto.class);

    }

    @Override
    @Transactional
    public BrandCarDto updateBrandCar(BrandCarDto updateBrandCar) {
        BrandCar brandCar = brandCarRepository.findById(updateBrandCar.getId())
                .orElseThrow(() -> new MyException("Brand car not found!: " + updateBrandCar.getId()));
        brandCar.setBrandName(updateBrandCar.getBrandName());
        return modelMapper.map(brandCarRepository.save(brandCar), BrandCarDto.class);
    }

    @Override
    @Transactional

    public void deleteBrandCar(Integer id) {
        brandCarRepository.deleteById(id);
    }

    @Override
    @Transactional

    public BrandCarDto findBrandCarById(Integer id) {
        BrandCar brandCar = brandCarRepository.findById(id)
                .orElseThrow(() -> new MyException("Brand car not found!: " + id));
        return modelMapper.map(brandCar, BrandCarDto.class);
    }

    @Override
    @Transactional
    public long countAllBrandsCar() {
        return brandCarRepository.count();
    }

    @Override
    @Transactional
    public List<BrandCarDto> findAllBrandsCarPages(int page, int size) {
        return brandCarRepository.findAll(PageRequest.of(page, size)).stream()
                .map(brandCar -> modelMapper.map(brandCar, BrandCarDto.class))
                .collect(Collectors.toList());
    }

}
