package chornarin.com.kh.online_course.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RateDto {
    private Long ratingValue;
    private String userEmail;
    private String courseName;

}
