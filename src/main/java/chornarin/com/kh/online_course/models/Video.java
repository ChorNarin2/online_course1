package chornarin.com.kh.online_course.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "videos")
@Data
@RequiredArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "file_url", nullable = false)
    private String file_url;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
