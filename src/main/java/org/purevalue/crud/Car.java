package org.purevalue.crud;

import org.springframework.data.annotation.Id;

/**
 * create table car(
 *   id bigserial,
 *   brand varchar,
 *   model varchar
 * );
 */
public class Car {
    @Id
    private Long id;
    private String brand;
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
