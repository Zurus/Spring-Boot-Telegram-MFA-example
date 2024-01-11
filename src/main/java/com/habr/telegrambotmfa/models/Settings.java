package com.habr.telegrambotmfa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings extends BaseEntity {

    @Column(name = "key", nullable = false)
    @NotBlank
    private String key;

    @Column(name="value", nullable = false)
    @NotBlank
    private String value;
}
