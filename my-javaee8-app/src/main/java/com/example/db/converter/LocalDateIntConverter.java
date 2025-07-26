package com.example.db.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter
public class LocalDateIntConverter implements AttributeConverter<LocalDate, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        int result = (localDate.getYear() - 1800) * 100_00;
        result += localDate.getMonthValue() * 100;
        result += localDate.getDayOfMonth();
        return result;
    }

    @Override
    public LocalDate convertToEntityAttribute(Integer aInteger) {
        if (aInteger == null) {
            return null;
        }
        String ymd = String.valueOf(aInteger);
        if (ymd.length() != 7) {
            return null;
        }
        int year = Integer.parseInt(ymd.substring(0, 3)) + 1800;
        int month = Integer.parseInt(ymd.substring(3, 5));
        int day = Integer.parseInt(ymd.substring(5, 7));
        return LocalDate.of(year, month, day);
    }

}
