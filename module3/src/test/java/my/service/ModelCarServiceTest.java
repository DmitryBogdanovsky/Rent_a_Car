package my.service;

import my.dao.ModelCarRepository;
import my.dto.BrandCarDto;
import my.dto.ModelCarDto;
import my.model.car.ModelCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.Validator;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ModelCarServiceTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    ModelCarRepository modelDao;
    @InjectMocks
    ModelCarServiceImpl targetObject;

    public ModelCarServiceTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void addNewModelShouldCallDao() {
        // Given
        ModelCarDto carModelDto = new ModelCarDto(1, "X1", new BrandCarDto(1, "BMW"));

        // When
        when(modelDao.save(any())).thenReturn(new ModelCar());
        targetObject.addModelCar(carModelDto);

        // Then
        ArgumentCaptor<ModelCar> argument = ArgumentCaptor.forClass(ModelCar.class);
        verify(modelDao).save(argument.capture());
        assertEquals(argument.getValue().getModelName(), carModelDto.getModelName());
        assertEquals(argument.getValue().getId(), carModelDto.getId());
        assertEquals(argument.getValue().getBrandCar().getId(), carModelDto.getBrandCar().getId());
        assertEquals(argument.getValue().getBrandCar().getBrandName(), carModelDto.getBrandCar().getBrandName());
    }

}
