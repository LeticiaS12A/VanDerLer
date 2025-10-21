package com.tagivan.vandeler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tagivan.vandeler.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
