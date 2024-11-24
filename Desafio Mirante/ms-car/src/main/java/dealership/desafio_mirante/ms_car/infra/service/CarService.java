package dealership.desafio_mirante.ms_car.infra.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dealership.desafio_mirante.ms_car.application.dto.CarDTO;
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
      .name(car.getName())
      .brand(car.getBrand())
      .model(car.getModel())
      .year(car.getYear())
      .color(car.getColor())
      .price(car.getPrice())
      .description(car.getDescription()).build()).collect(Collectors.toList());
  }

  public CarDTO findById(Long id) {
    return CarDTO.builder()
      .id(findById(id).getId())
      .name(findById(id).getName())
      .brand(findById(id).getBrand())
      .model(findById(id).getModel())
      .year(findById(id).getYear())
      .color(findById(id).getColor())
      .price(findById(id).getPrice())
      .description(findById(id).getDescription()).build(); 
  }
}
