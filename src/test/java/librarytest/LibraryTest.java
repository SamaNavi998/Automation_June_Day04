package librarytest;

import library.Book;
import library.Library;
import library.Member;
import org.testng.Assert;
import org.testng.annotations.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Member member1;
    private Member member2;
    private long timeoutStart;

    @DataProvider(name = "bookProvider")
    public Object[][] bookProvider() {
        return new Object[][]{
                {new Book("Book3", "Author3")},
                {new Book("Book4", "Author4")},
                {new Book("Book5", "Author5")}

        };
    }

    @BeforeClass
    public void setupLibrary() {
        library = new Library();
        book1 = new Book("Book1", "Author1");
        book2 = new Book("Book2", "Author2");
        member1 = new Member("Member1");
        member2 = new Member("Member2");
        library.addBook(book1);
        library.addBook(book2);
        library.registerMember(member1);
        library.registerMember(member2);


    }

    @BeforeMethod
    public void setupBooksAndMembers() {

    }

    @AfterMethod
    public void clearBooksAndMembers() {

    }

    @AfterClass
    public void makeLibraryNull() {
        library.getBookList().clear();
        library.getMemberList().clear();
        library = null;

    }

    @Test(groups = "addRemoveBook")
    public void testAddBook() {
        Book book6 = new Book("Book6", "Author6");
        library.addBook(book6);
        Assert.assertTrue(library.getBookList().contains(book6));
    }

    @Test(groups = "addRemoveBook")
    public void testRemoveBook() {
        library.removeBook(book1);
        Assert.assertFalse(library.getBookList().contains(book1));
    }

    @Test(groups = "lendReturn", dependsOnGroups = "addRemoveBook")
    public void testLendBook() {
        boolean lentBookStatus = library.lendBook(book2,member2);
        Assert.assertTrue(lentBookStatus);
        Assert.assertTrue(library.getBookList().get(0).isLent());
    }

    @Test(groups = "lendReturn", dependsOnMethods = "testLendBook")
    public void testReturnBook() {
        boolean lentBookStatus = library.returnBook(book2,member2);
        Assert.assertTrue(lentBookStatus);
        Assert.assertFalse(library.getBookList().get(0).isLent());
    }

    @Test(dependsOnMethods = "testReturnBook", dataProvider = "bookProvider")
    public void testAddBookWithDataProvider(Book book) {
        library.addBook(book);
        Assert.assertTrue(library.getBookList().contains(book));
    }

    @Test(enabled = false)
    public void disabledTest() {
        System.out.println("This is disabled test");
    }

    @Test
    public void setTimeoutStart() {
        timeoutStart = System.currentTimeMillis();
    }

    @Test(dependsOnMethods = "setTimeoutStart", timeOut = 3000)
    public void testTimeout() {
        Assert.assertTrue(System.currentTimeMillis()>timeoutStart);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testExpectedException() {
        library.getBookList().get(10);
    }
}
