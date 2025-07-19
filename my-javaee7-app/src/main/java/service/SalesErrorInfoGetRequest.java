package service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class SalesErrorInfoGetRequest {

    private List<String> items;

    private List<String> fruits;

    private LocalDate startDate;

    private LocalDate endDate;

}
