package com.ginogym.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.ginogym.data.entities.DayOfWeek;
import com.ginogym.data.repositories.DayOfWeekRepository;
import com.ginogym.enumeration.DaysOfWeek;


@Component
public class DayOfWeekInit implements CommandLineRunner{

    private final DayOfWeekRepository dayOfWeekRepository;

    public DayOfWeekInit(DayOfWeekRepository dayOfWeekRepository) { 
        this.dayOfWeekRepository = dayOfWeekRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (dayOfWeekRepository.count() == 0) {
            for (DaysOfWeek giorno : DaysOfWeek.values()) {
                DayOfWeek entity = new DayOfWeek();
                entity.setDescrizione(giorno.name());
                dayOfWeekRepository.save(entity);
            }
        }
    }

}
