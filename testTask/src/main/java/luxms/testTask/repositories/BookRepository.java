package luxms.testTask.repositories;

import luxms.testTask.model.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findTop10ByPublicationDateYearOrderByPublicationDateAsc(Integer year, Sort sorting);
    List<Book> findTop10(Sort sorting);
}
