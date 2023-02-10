package my.service;

import my.dao.BrandCarRepository;
import my.dto.BrandCarDto;
import my.exception.MyException;
import my.model.car.BrandCar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BrandCarServiceTest {

    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    BrandCarRepository brandCarRepository;
    @InjectMocks
    BrandCarServiceImpl targetObject;

    public BrandCarServiceTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void findAllBrandsPageableShouldReturnList() {
        // Given
        List<BrandCarDto> expectedList = List.of(
                new BrandCarDto(1, "VOLVO"),
                new BrandCarDto(2, "Mercedes-Benz")
        );

        when(brandCarRepository.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<>(List.of(
                new BrandCar(1, "VOLVO", new ArrayList<>()),
                new BrandCar(2, "Mercedes-Benz", new ArrayList<>()))
        ));

        // When
        List<BrandCarDto> resultList = targetObject.findAllBrandsCarPages(0, 20);

        // Then
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0).getId(), expectedList.get(0).getId());
        assertEquals(resultList.get(0).getBrandName(), expectedList.get(0).getBrandName());

        assertEquals(resultList.get(1).getId(), expectedList.get(1).getId());
        assertEquals(resultList.get(1).getBrandName(), expectedList.get(1).getBrandName());
    }

    @Test
    public void addNewBrandDao() {
        // Given
        BrandCarDto brandCarDto = new BrandCarDto(1, "VOLVO");

        // When
        when(brandCarRepository.save(any())).thenReturn(new BrandCar());
        targetObject.addBrand(brandCarDto);

        // Then
        ArgumentCaptor<BrandCar> argument = ArgumentCaptor.forClass(BrandCar.class);
        verify(brandCarRepository).save(argument.capture());
        assertEquals(argument.getValue().getBrandName(), brandCarDto.getBrandName());
        assertEquals(argument.getValue().getId(), brandCarDto.getId());
    }

    @Test
    public void addNewBrandById() {
        // Given
        BrandCarDto brandCarDto = new BrandCarDto(1, "VOLVO");

        // When
        when(brandCarRepository.existsById(brandCarDto.getId()))
                .thenReturn(true);

        // Then
        assertThrows(MyException.class, () -> targetObject.addBrand(brandCarDto));
    }

}
