package my.service;


import my.dao.CarRepository;
import my.dto.CarDto;
import my.exception.MyException;
import my.model.car.Car;
import my.model.car.CarPicture;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CarDto addCar(CarDto newCar) {
        Car car = modelMapper.map(newCar, Car.class);
        if (car.getId() != null && carRepository.existsById(car.getId())) {
            throw new MyException("Car already exists!:" + car.getId());
        }
        return modelMapper.map(carRepository.save(car),CarDto.class);
    }

    @Override
    @Transactional
    public CarDto updateCar(CarDto updateCar) {

        if (!carRepository.existsById(updateCar.getId()))
            throw new MyException("Car not found!: " + updateCar.getId());

        Car car = modelMapper.map(updateCar, Car.class);
        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    @Override
    @Transactional
    public void deleteCar(Integer id) {
        if (!carRepository.existsById(id))
            throw new MyException("Car not found!: " + id);

        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CarDto findCarById(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new MyException("Car not found!: " + id));

        return modelMapper.map(car, CarDto.class);
    }

    @Override
    @Transactional
    public long countAllCars() {
        return carRepository.count();
    }

    @Override
    @Transactional
    public List<CarDto> findAllCarsPageable(int page, int size) {
        return carRepository.findAll(PageRequest.of(page, size)).stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateCarPictures(Integer id, List<byte[]> pictures) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new MyException("Car not found!: " + id));

        car.setCarPictures(pictures.stream()
                .map(picture -> new CarPicture(null, picture))
                .collect(Collectors.toList()));

        carRepository.save(car);
    }
}
