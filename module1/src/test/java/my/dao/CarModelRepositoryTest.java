package my.dao;

import my.config.ConfigSources;
import my.model.car.BrandCar;
import my.model.car.ModelCar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigSources.class)
@TestPropertySource(value = {
        "classpath:/database-test.properties",
        "classpath:/hibernate-test.properties"
})
public class CarModelRepositoryTest {
    @Autowired
    ModelCarRepository modelCarRepository;
    @Autowired
    BrandCarRepository brandCarRepository;

    @Before
    public void setUp() {
        // FIXME: should only run once before all tests
        BrandCar brand1 = new BrandCar(1, "AUDI", new ArrayList<>());
        ModelCar model1 = new ModelCar(1, "Q3", null);
        ModelCar model2 = new ModelCar(2, "Q5", null);
        ModelCar model3 = new ModelCar(3, "Q7", null);
        brand1.addModelCar(model1);
        brand1.addModelCar(model2);
        brand1.addModelCar(model3);
        brandCarRepository.save(brand1);
        modelCarRepository.save(model1);
        modelCarRepository.save(model2);
        modelCarRepository.save(model3);

        BrandCar brand2 = new BrandCar(2, "KIA", new ArrayList<>());
        ModelCar model4 = new ModelCar(4, "RIO", null);
        brand2.addModelCar(model4);
        brandCarRepository.save(brand2);
        modelCarRepository.save(model4);


    }

    @Test
    public void countByCarBrand_Id() {
        // Given
        // Database initialized with entities in setUp() method of this class

        // When
        int numberOfModels = modelCarRepository.countByBrandCar_Id(1);


        // Then
        assertEquals(3, numberOfModels);


    }

    @Test
    public void findAllByBrand() {
        // Given

        // When
        Page<ModelCar> models = modelCarRepository.findAllByBrand(1, PageRequest.of(0, 20));

        // Then
        assertEquals(3, models.getContent().size());
    }
}