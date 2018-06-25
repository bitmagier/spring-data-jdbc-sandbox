package org.purevalue;

import org.purevalue.crud.Car;
import org.purevalue.crud.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    void insertCars() {
        final List<Car> cars = new ArrayList<>();
        cars.add( carRepository.save(new Car("BMW", "5")) );
        cars.add( carRepository.save(new Car("BMW", "3")) );
        cars.add( carRepository.save(new Car("Audi", "R8")) );
        cars.add( carRepository.save(new Car("Nissan", "370Z")) );

        log.info("saved car: {}", cars);
    }

    void listCarsByRepoFunction() {
        final List<Car> cars = carRepository.getAll();
        log.info("Repo: found cars: {}", cars);
    }

    void listCarsByQuery() {
        final Iterable<Car> cars = carRepository.findAll();
        log.info("Repo findAll(): {}", cars);
    }

    void findCarsByBrand(String brand) {
        final List<Car> cars = carRepository.findByBrand(brand);
        log.info("Repo findByBrand(): {}", cars);
    }

    void findBrandsLike(String s) {
        final List<String> brands = carRepository.findBrandsLike(s);
        log.info("Repo findBrandsLike({}): {}", s, brands);
    }
}
