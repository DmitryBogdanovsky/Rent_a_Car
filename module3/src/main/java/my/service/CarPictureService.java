package my.service;

import java.util.List;

public interface CarPictureService {

    byte[] getPicture(Integer pictureId);

    List<Integer> getPictureIdByCarId(Integer carId);

}
