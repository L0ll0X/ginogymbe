package com.ginogym.presentation.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMachineRequest {
       private String name;
       private String description;
       private String imageBase64;
}
