package org.example.uberprojectlocationservice.services;

import org.example.uberprojectlocationservice.dto.DriverLocationDto;
import java.util.List;

public interface LocationService {

    Boolean saveDriverLocation(String driverId, Double latitude, Double longitude);

    List<DriverLocationDto> getNearByDrivers(Double SEARCH_RADIUS,Double latitude, Double longitude);
}
