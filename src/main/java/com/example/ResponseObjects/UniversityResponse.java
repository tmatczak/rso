package com.example.ResponseObjects;

import com.example.types.UniversityType;
import com.example.util.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Maciej on 2016-05-26.
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class UniversityResponse {

    private String name;
    private String yerOfFundation;
    private Location location;
    private UniversityType universityType;
}
