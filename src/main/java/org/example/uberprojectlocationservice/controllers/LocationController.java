package org.example.uberprojectlocationservice.controllers;

import org.example.uberprojectlocationservice.dto.DriverLocationDto;
import org.example.uberprojectlocationservice.dto.NearbyDriversRequestDto;
import org.example.uberprojectlocationservice.dto.SaveDriverLocationRequestDto;
import org.example.uberprojectlocationservice.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService=locationService;
    }

    @PostMapping("/drivers")
    public ResponseEntity<Boolean> saveDriverLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto){

        try{
            Boolean response = locationService.saveDriverLocation(
                    saveDriverLocationRequestDto.getDriverId(),
                    saveDriverLocationRequestDto.getLatitude(),
                    saveDriverLocationRequestDto.getLongitude());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nearby/drivers/{searchRadius}")
    public ResponseEntity<List<DriverLocationDto>> getNearByDrivers(@PathVariable Double searchRadius, @RequestBody NearbyDriversRequestDto nearbyDriversRequestDto){

        try {
            List<DriverLocationDto> drivers = locationService.getNearByDrivers(searchRadius,nearbyDriversRequestDto.getLatitude(),nearbyDriversRequestDto.getLongitude());
            return new ResponseEntity<>(drivers,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
