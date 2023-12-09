package com.example.maincinedrive.model.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maincinedrive.model.entity.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    Horario findByDiaSemanaAndHoraInicio(DayOfWeek diaSemana, LocalTime horaInicio);
}
