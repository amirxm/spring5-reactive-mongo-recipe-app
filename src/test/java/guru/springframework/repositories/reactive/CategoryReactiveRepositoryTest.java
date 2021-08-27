package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();

        Category category = new Category();
        category.setDescription("Mexican");
        categoryReactiveRepository.save(category).block();

    }

    @Test
    public void findByDescription() {
        assertNotNull(categoryReactiveRepository.findByDescription("Mexican").block().getId());
    }

    @Test
    public void findByDescriptionNull() {
        assertNull(categoryReactiveRepository.findByDescription("Foo").block());
    }
}
