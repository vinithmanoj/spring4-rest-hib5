package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Book;
import service.BookingService;

@RestController
public class BookController {

  @Autowired
  private BookingService bookService;

  @PostMapping("/book")
  public ResponseEntity<?> save(@RequestBody Book book){
    return ResponseEntity.ok().body("");
  }

  @GetMapping("/book/{id}")
  public ResponseEntity<Book> get(@PathVariable("id") long id){
    return ResponseEntity.ok().body(new Book());
  }


  /*---get all books---*/
  @GetMapping("/book")
  public ResponseEntity<List<Book>> list() {
    List<Book> books = bookService.list();
    return ResponseEntity.ok().body(books);
  }

  /*---Update a book by id---*/
  @PutMapping("/book/{id}")
  public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Book book) {
    bookService.update(id, book);
    return ResponseEntity.ok().body("Book has been updated successfully.");
  }

  /*---Delete a book by id---*/
  @DeleteMapping("/book/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    bookService.delete(id);
    return ResponseEntity.ok().body("Book has been deleted successfully.");
  }
}
