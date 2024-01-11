package kdu.backend4;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    static Logging logger = new Logging();

    public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books;
        if (comparator == null) {
            books = new TreeSet<>(); // natural ordering if no comparator
        } else {
            books = new TreeSet<>(comparator);
        }
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);


        for (Book book : books) {
           logger.logInfo(String.valueOf(book));
        }

        return books;
    }

    public static void main(String[] args) {
        treeSetDemo(null);

        // Test with PubDateAscComparator
        logger.logInfo("\n Ascending Order :");
        treeSetDemo(new PubDateAscComparator());

        // Test with PubDateDescComparator
        logger.logInfo("\n Descending Order :");
        treeSetDemo(new PubDateDescComparator());
    }

}
