package my.service;


import my.dao.CarPictureRepository;
import my.exception.MyException;
import my.model.car.CarPicture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CarPictureServiceImpl implements CarPictureService {
    @Autowired
    CarPictureRepository carPictureRepository;

    @Override
    @Transactional
    public byte[] getPicture(Integer pictureId) {
        CarPicture carPicture = carPictureRepository.findById(pictureId)
                .orElseThrow(() -> new MyException("Image not found!: " + pictureId));
        return carPicture.getPicture();
    }
    @Override
    @Transactional
    public List<Integer> getPictureIdByCarId(Integer carId) {
        return carPictureRepository.getAllPictureIdByCarId(carId);
    }

}
