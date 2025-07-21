package db.converter;

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
        return convertToLocalDate(aInt);
    }

    public static LocalDate convertToLocalDate(Integer aInt) {
        if (aInt == null) {
            return null;
        }
        String strNumber = String.valueOf(aInt);
        if (strNumber.length() < 7) {
            return null;
        }
        int year = Integer.parseInt(strNumber.substring(0, 3)) + 1800;
        int month = Integer.parseInt(strNumber.substring(3, 5));
        int dayOfMonth = Integer.parseInt(strNumber.substring(5, 7));
        return LocalDate.of(year, month, dayOfMonth);
    }

}
