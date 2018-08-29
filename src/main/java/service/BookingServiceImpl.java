package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.BookDao;
import model.Book;

@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService{

  @Autowired
  private BookDao bookDao;

  @Transactional
  public long save(Book book) {
    return bookDao.save(book);
  }

  public Book get(long id) {
    return bookDao.get(id);
  }

  public List<Book> list() {
    return bookDao.list();
  }

  @Transactional
  public void update(long id, Book book) {
    bookDao.update(id, book);
  }

  @Transactional
  public void delete(long id) {
    bookDao.delete(id);
  }
}
