package com.example.lightnovelrecommendation.Repository;

import com.example.lightnovelrecommendation.Models.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Cacheable(value = "findAllBooks", key = "#root.methodName")
    @Query(value = "SELECT * FROM MostPopular", nativeQuery = true)
    List<Object[]> findAllBooks();

    @Cacheable(value = "findAllBooksByRating", key = "#root.methodName")
    @Query(value = "SELECT * FROM MostPopular mb ORDER BY mb.Ratings DESC, mb.NumRatings DESC", nativeQuery = true)
    List<Object[]> findAllBooksByRating();

    @Cacheable(value = "findAllCompletedBooks", key = "#root.methodName")
    @Query(value = "SELECT * FROM MostPopular mb WHERE mb.status = 'Completed'", nativeQuery = true)
    List<Object[]> findAllCompletedBooks();

    @Cacheable(value = "findAllOngoingBooks", key = "#root.methodName")
    @Query(value = "SELECT * FROM MostPopular mb WHERE mb.status = 'Ongoing'", nativeQuery = true)
    List<Object[]> findAllOngoingBooks();

    @Cacheable("findAllBooksByGenre")
    @Query(value = "SELECT * FROM MostPopular mb WHERE mb.genre LIKE %:genre%", nativeQuery = true)
    List<Object[]> findAllBooksByGenre(String genre);

    @Cacheable("findByTitleContaining")
    @Query(value = "SELECT * FROM MostPopular mb WHERE mb.title LIKE %:search%", nativeQuery = true)
    List<Object[]> findByTitleContaining(String search);

}

