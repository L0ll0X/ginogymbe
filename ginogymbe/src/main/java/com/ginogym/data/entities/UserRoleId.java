package com.ginogym.data.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;


//chiave composta
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {
    private Long userId;
    private Long roleId;
}
