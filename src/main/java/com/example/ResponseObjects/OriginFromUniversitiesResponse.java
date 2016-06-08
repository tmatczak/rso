package com.example.ResponseObjects;

import com.example.util.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by Maciej on 2016-05-26.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OriginFromUniversitiesResponse {

    private UniversityResponse universityDto;
    private ArrayList<ComeFromResponse> comeFromDtos;
}
