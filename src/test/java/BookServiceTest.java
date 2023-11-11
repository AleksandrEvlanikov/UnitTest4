import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepositoryMock;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepositoryMock = mock(BookRepository.class);
        bookService = new BookService(bookRepositoryMock);
    }

    @Test
    void testFindBookById(){

        when(bookRepositoryMock.findById("99")).thenReturn(new Book("99", "Скотный двор", "Джорджа Оруэлла"));
        Book result = bookService.findBookById("99");

        assertNotNull(result);
        assertEquals("99", result.getId());
        assertEquals("Скотный двор", result.getTitle());
        assertEquals("Джорджа Оруэлла", result.getAuthor());

        verify(bookRepositoryMock).findById("99");


    }

    @Test
    void testFindAllBooks(){
        when(bookRepositoryMock.findAll()).thenReturn(List.of(
                        new Book("99", "Скотный двор", "Джорджа Оруэлла"),
                        new Book("22", "Как завоёвывать друзей и оказывать влияние на людей", "Дейл Карнеги")));

        List<Book> result = bookService.findAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(bookRepositoryMock).findAll();

    }
}