package com.UFJF.planejaai.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PARTICIPANTE")
public class Participante extends Usuario {

}
