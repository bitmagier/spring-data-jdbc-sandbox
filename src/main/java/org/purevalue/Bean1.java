package org.purevalue;

import org.purevalue.crud.Car;
import org.purevalue.crud.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class Bean1 {
    private static final Logger log = LoggerFactory.getLogger(Bean1.class);

    private final CarRepository carRepository;

    @Autowired
    public Bean1(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void insertCar() {
        final Car car = carRepository.save(new Car("BMW", "5"));
        log.info("saved car: {}", car);
    }

    public void listCarsByRepoFunction() {
        final List<Car> cars = carRepository.getAll();
        log.info("Repo: found cars: {}", cars);
    }

    public void listCarsByQuery() {
        final Iterable<Car> cars = carRepository.findAll();
        log.info("Repo findAll(): {}", cars);
    }
}
