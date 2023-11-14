package bg.softuni.quizkids.services.impl;

import bg.softuni.quizkids.models.entity.Category;
import bg.softuni.quizkids.models.enums.CategoryName;
import bg.softuni.quizkids.repository.CategoryRepository;
import bg.softuni.quizkids.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void init() {
        if (categoryRepository.count() == 0){
            List<Category> categories = new ArrayList<>();

            Category biology = new Category();
            biology.setName(CategoryName.BIOLOGY);
            biology.setDescription("Q&A for animals and plants");
            categories.add(biology);

            Category geography = new Category();
            geography.setName(CategoryName.GEOGRAPHY);
            geography.setDescription("Q&A for most popular and local geography objects");
            categories.add(geography);

            Category math = new Category();
            math.setName(CategoryName.MATH);
            math.setDescription("Simple maths problems");
            categories.add(math);

            Category astronomy = new Category();
            astronomy.setName(CategoryName.ASTRONOMY_AND_SPACE);
            astronomy.setDescription("Simple Q&A for astronomy and space");
            categories.add(astronomy);

            Category humanBody = new Category();
            humanBody.setName(CategoryName.HUMAN_BODY);
            humanBody.setDescription("Simple Q&A for human systems and organs");
            categories.add(humanBody);

            Category saveTheEarth = new Category();
            saveTheEarth.setName(CategoryName.SAVE_THE_EARTH);
            saveTheEarth.setDescription("Q&A How to save the Earth");
            categories.add(saveTheEarth);

            categoryRepository.saveAll(categories);

        }

    }
}
