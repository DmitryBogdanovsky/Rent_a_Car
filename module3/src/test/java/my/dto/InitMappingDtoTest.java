package my.dto;


import my.model.car.*;
import my.model.order.Order;
import my.model.order.OrderStatus;
import my.model.user.RoleEnum;
import my.model.user.User;
import my.model.user.UserDetails;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class InitMappingDtoTest {

    protected ModelMapper modelMapper;

    protected User user;
    protected UserDetails userDetails;
    protected Car car;
    protected BrandCar brandCar;
    protected ModelCar modelCar;
    protected Order order;

    public InitMappingDtoTest() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setUseOSGiClassLoaderBridging(true)
                .setPreferNestedProperties(false)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        userDetails = new UserDetails(
                1,
                "Dimon",
                "DIMON",
                "+375(29)111-22-33",
                Date.valueOf("1980-02-04"),
                null
        );

        user = new User(
                userDetails.getId(),
                "abx@gmail.com",
                "password",
                RoleEnum.USER,
                null,
                new ArrayList<>()
        );

        user.addUserDetails(userDetails);


        brandCar = new BrandCar(1, "BMW", new ArrayList<>());
        modelCar = new ModelCar(1, "X5", null);
        brandCar.addModelCar(modelCar);


        car = new Car(
                1,
                2020,
                "RED",
                TypeCarBody.CUV,
                FuelCarType.PETROL,
                true,
                true,
                BigDecimal.valueOf(150),
                modelCar,
                null,
                new ArrayList<>()
        );


        order = new Order(
                1,
                LocalDate.of(2023, 2, 3),
                LocalDate.of(2023, 2, 3),
                LocalDate.of(2023, 2, 6),
                BigDecimal.valueOf(100),
                OrderStatus.CLOSED,
                user,
                car
        );

        order.addUser(user);
        order.addCar(car);
    }

}
