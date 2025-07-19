package service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SalesErrorInfoGetResponse {

    private int resultCode;

    private List<SalesErrorInfoRecord> records;

}
