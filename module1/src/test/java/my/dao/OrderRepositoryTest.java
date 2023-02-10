package my.dao;


import lombok.SneakyThrows;
import my.config.ConfigSources;

import my.model.order.OrderStatus;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigSources.class)
@TestPropertySource(value = {
        "classpath:/database-test.properties",
        "classpath:/hibernate-test.properties"
})
public class OrderRepositoryTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    OrderRepository orderRepository;

    IDatabaseConnection iDatabaseConnection;

    @Before
    @SneakyThrows
    public void setUp() {
        iDatabaseConnection = new MySqlConnection(dataSource.getConnection(), "Rent_a_car_test");
    }

    @Test
    @SneakyThrows
    public void countByOrderStatusTest() {

        // Given

        IDataSet dataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, dataSet);

        // When
        long carPaid = orderRepository.countByOrderStatus(OrderStatus.PAID);//4
        long carInUseCount = orderRepository.countByOrderStatus(OrderStatus.CAR_IN_USE);//4
        long closedCount = orderRepository.countByOrderStatus(OrderStatus.CLOSED);//2

        // Then
        assertEquals(4,carPaid);
        assertEquals(4, carInUseCount);
        assertEquals(2, closedCount);

        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, dataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByCarInDateInterval() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderRepository.countActiveOrdersByCarInDateInterval(
                1,
                LocalDate.parse("2023-01-22"),
                LocalDate.parse("2023-01-23")
        );

        // Then
        assertEquals(3, countedOrders);
              DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByCarInDateIntervalShouldSkipClosedOrders() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderRepository.countActiveOrdersByCarInDateInterval(
                2,
                LocalDate.parse("2023-01-22"),
                LocalDate.parse("2023-01-23")
        );

        // Then
        assertEquals(2, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByUserInDateInterval() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderRepository.countActiveOrdersByUserInDateInterval(
                2,
                LocalDate.parse("2023-01-22"),
                LocalDate.parse("2023-01-23")
        );

        // Then
        assertEquals(4, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByUserInDateIntervalShouldSkipClosedOrders() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderRepository.countActiveOrdersByUserInDateInterval(
               1,
                LocalDate.parse("2023-01-25"),
                LocalDate.parse("2023-01-25")
        );

        // Then
        assertEquals(0, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }


    @Test
    @SneakyThrows
    public void countActiveOrdersByCarInDateIntervalExceptOrderWithId() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderRepository.countActiveOrdersByCarInDateIntervalExceptOrderWithId(
                5,
               2,
                LocalDate.parse("2023-01-23"),
                LocalDate.parse("2023-01-23")
        );

        // Then
        assertEquals(2, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByUserInDateIntervalExceptOrderWithId() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderRepositoryTest.class.getResourceAsStream("/init/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderRepository.countActiveOrdersByUserInDateIntervalExceptOrderWithId(
                3,
                2,
                LocalDate.parse("2023-01-22"),
                LocalDate.parse("2023-01-22")
        );

        // Then
        assertEquals(4, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

}