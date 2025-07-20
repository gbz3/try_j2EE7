package db.converter;

import db.entity.EmployeesEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateIntConverter implements AttributeConverter<LocalDate, Integer> {

    @Override
    public Integer convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        int ymd = localDate.getDayOfMonth();
        ymd += localDate.getMonthValue() * 100;
        ymd += (localDate.getYear() - 1800) * 100_00;
        return ymd;
    }

    @Override
    public LocalDate convertToEntityAttribute(Integer aInt) {
        return EmployeesEntity.convertToLocalDate(aInt);
    }
}
