package com.ginogym.presentation.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyExerciseRequest extends CreateExerciseRequest{

    private Long id;
}
