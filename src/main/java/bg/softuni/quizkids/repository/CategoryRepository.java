package bg.softuni.quizkids.repository;

import bg.softuni.quizkids.models.entity.Category;
import bg.softuni.quizkids.models.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(CategoryName category);
}
