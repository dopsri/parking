package com.dopsri.parking.service.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dopsri.parking.domain.ParkingLot;
import com.dopsri.parking.domain.ParkingLotStructure;
import com.dopsri.parking.repository.ParkingLotRepository;

@RestController
@RequestMapping("parking-service")
public final class ParkingServiceResource {
	
	private static final BiConsumer<ArrayList<Integer>, ParkingLot> PARKINGlOT_ACCUMULATOR = (result,
			parkingLot) -> result.add(parkingLot.getNumber());

	private static final BinaryOperator<ArrayList<Integer>> PARKINGLOT_COMBINER = (result1, result2) -> {
		result1.addAll(result2);
		return result1;
	};

	private static final Function<ArrayList<Integer>, ArrayList<Integer>> PARKINGLOT_FINISHER = (result) -> {
		ArrayList<Integer> values = (ArrayList<Integer>) result.stream().collect(Collectors.toList());
		Collections.sort(values);
		return values;
	};

	@Autowired
	private ParkingLotRepository parkingLotRepository;

	@GetMapping(path = "/parking-lots")
	public List<ParkingLot> getParkingLotForStructure() {
		return parkingLotRepository.findAll();
	}

	@GetMapping(path = "/parking-lots/structure/{parkingLotStructure}")
	public List<ParkingLot> getParkingLotForStructure(
			@PathVariable(value = "parkingLotStructure") String parkingLotStructure) {
		return parkingLotRepository
				.findByParkingLotStructure(ParkingLotStructure.valueOf(parkingLotStructure.toUpperCase()));
	}
	
	
	
	@GetMapping(path = "/parking-lots/structure/{parkingLotStructure}/available")
	public Map<Integer, ArrayList<Integer>> getAvailableParkingLots(
			@PathVariable(value = "parkingLotStructure") String parkingLotStructure) {

		return parkingLotRepository
				.findByParkingLotStructure(ParkingLotStructure.valueOf(parkingLotStructure.toUpperCase())).stream()
				.collect(Collectors.groupingBy(ParkingLot::getLevel, Collector.of(
					ArrayList<Integer>::new,
					PARKINGlOT_ACCUMULATOR, 
					PARKINGLOT_COMBINER, 
					PARKINGLOT_FINISHER, 
					Characteristics.UNORDERED)));

	}
	
	
	@GetMapping(path = "/parking-lots/structure/{parkingLotStructure}/count")
	public Map<Integer, Long> getAvailableParkingLotCount(
			@PathVariable(value = "parkingLotStructure") String parkingLotStructure) {	
		
		return parkingLotRepository
		.findByParkingLotStructure(ParkingLotStructure.valueOf(parkingLotStructure.toUpperCase()))
		.stream().collect(
				Collectors.groupingBy(
						ParkingLot::getLevel, Collectors.counting()
				));		
	}
	
	@GetMapping(path = "/parking-lots/structure/{parkingLotStructure}/available1")
	public Map<Integer, ArrayList<ParkingLot>> getAvailableParkingLots1(
			@PathVariable(value = "parkingLotStructure") String parkingLotStructure) {	
		
		return parkingLotRepository
		.findByParkingLotStructure(ParkingLotStructure.valueOf(parkingLotStructure.toUpperCase()))
		.stream().collect(
				Collectors.groupingBy(
						ParkingLot::getLevel, 
						Collector.of(
							ArrayList<ParkingLot>::new,
							ArrayList<ParkingLot>::add,
							(ArrayList<ParkingLot> result1, ArrayList<ParkingLot> result2) -> {result1.addAll(result2); return result1;}
							, Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT)
				));		
	}
	
	
	
	/*private void getMap(String parkingLotStructure) {
		parkingLotRepository
		.findByParkingLotStructure(ParkingLotStructure.valueOf(parkingLotStructure.toUpperCase()))
		.stream().forEach(parkingLot -> {
			int level = parkingLot.getLevel();
			List<Integer> slotList = levelMap.get(level);
			if(slotList == null) {
				slotList = new ArrayList<>();
			}
			slotList.add(parkingLot.getNumber());
			levelMap.put(level, slotList);
		});
		
	}*/

}
