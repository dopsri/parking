package com.dopsri.parking.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dopsri.parking.domain.ParkingLot;
import com.dopsri.parking.domain.ParkingLotStructure;
import com.dopsri.parking.domain.ParkingLotType;

@Component
public class ParkingLotDataSeeder implements CommandLineRunner {

	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
		parkingLots.add(new ParkingLot(2, 1, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(1, 1, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(3, 1, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(1, 2, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(2, 2, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(1, 3, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(1, 4, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(2, 4, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLots.add(new ParkingLot(3, 4, ParkingLotStructure.MLCP3, ParkingLotType.S));	
		parkingLotRepository.saveAll(parkingLots);	
		
	}

}
