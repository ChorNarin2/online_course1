package chornarin.com.kh.online_course.models;


import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Rate> ratings;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "create_at")
    @CreationTimestamp
    private Date create_at;

    @Override
    public String toString() {
        return this.name; // Return the course name when the course is printed
    }

}
