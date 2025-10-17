package com.ginogym.presentation.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyMachineRequest extends CreateMachineRequest{

    private Long id;
}
