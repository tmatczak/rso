package com.example.ResponseObjects;

import com.example.util.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Maciej on 2016-05-26.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FieldOfStudyResponse {

    private String name;
    private int val;
}
