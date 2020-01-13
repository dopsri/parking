package com.dopsri.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dopsri.parking.domain.ParkingLot;
import com.dopsri.parking.domain.ParkingLotId;
import com.dopsri.parking.domain.ParkingLotStructure;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, ParkingLotId>{

	List<ParkingLot> findByParkingLotStructure(ParkingLotStructure parkingLotStructure);

}
