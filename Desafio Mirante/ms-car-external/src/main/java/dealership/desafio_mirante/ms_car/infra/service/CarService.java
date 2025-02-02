package dealership.desafio_mirante.ms_car.infra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dealership.desafio_mirante.ms_car.application.dto.CarDTO;
import dealership.desafio_mirante.ms_car.domain.Car;
import dealership.desafio_mirante.ms_car.domain.CarMapper;
import dealership.desafio_mirante.ms_car.infra.persistence.CarEntity;
import dealership.desafio_mirante.ms_car.infra.repository.CarRepository;

@Service
public class CarService {
  
  private final CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<CarDTO> findAll() {

    List<CarEntity> cars = carRepository.findAll();
    return cars.stream().map(car -> CarDTO.builder()
      .id(car.getId())
      .carname(car.getCarname())
      .brand(car.getBrand())
      .model(car.getModel())
      .caryear(car.getCaryear())
      .color(car.getColor())
      .price(car.getPrice())
      .cardescription(car.getCardescription()).build()).collect(Collectors.toList());
  }

  public CarDTO findById(Long id) {
    CarEntity car = carRepository.findById(id).orElseThrow();
    return CarDTO.builder()
      .carname(car.getCarname())
      .brand(car.getBrand())
      .model(car.getModel())
      .caryear(car.getCaryear())
      .color(car.getColor())
      .price(car.getPrice())
      .cardescription(car.getCardescription()).build(); 
  }

  public CarDTO create(Car car){
    CarEntity entity = CarMapper.toEntity(car);
    carRepository.save(entity);
    return CarDTO.builder()
      .carname(entity.getCarname())
      .brand(entity.getBrand())
      .model(entity.getModel())
      .caryear(entity.getCaryear())
      .color(entity.getColor())
      .price(entity.getPrice())
      .cardescription(entity.getCardescription()).build();
  }

}
