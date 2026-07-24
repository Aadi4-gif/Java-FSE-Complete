import java.util.Arrays;

class Book implements Comparable<Book> {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "ID: " + bookId + " | Title: \"" + title + "\" | Author: " + author;
    }
}

class LibrarySearch {

    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equalsIgnoreCase(targetTitle)) {
                return books[i];
            }
        }
        return null;
    }

    public static Book binarySearchByTitle(Book[] sortedBooks, String targetTitle) {
        int low = 0;
        int high = sortedBooks.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedBooks[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comparison == 0) {
                return sortedBooks[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}