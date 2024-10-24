package chornarin.com.kh.online_course.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import chornarin.com.kh.online_course.repository.CategoryRepository;
import chornarin.com.kh.online_course.models.Category;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        this.CategoriesSeeder();
    }

    private void CategoriesSeeder(){    
        if (categoryRepository.count() == 0) { 

            Category webDevelopment = new Category();
            webDevelopment.setName("Web Development");
            categoryRepository.save(webDevelopment);

            Category videoEditing = new Category();
            videoEditing.setName("Video Editing");
            categoryRepository.save(videoEditing);

            Category photography = new Category();
            photography.setName("Photography");
            categoryRepository.save(photography);

            Category graphicDesign = new Category();
            graphicDesign.setName("Graphic Design");
            categoryRepository.save(graphicDesign);
        }
    }
}
