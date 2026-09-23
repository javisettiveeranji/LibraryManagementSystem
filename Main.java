
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// ============================================================
// PERSON
// ============================================================

abstract class Person {

    private int id;
    private String name;
    private String phone;
    private String email;

    protected Person(
            int id,
            String name,
            String phone,
            String email
    ) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void displayDetails();
}


// ============================================================
// MEMBER
// ============================================================

class Member extends Person {

    private int booksIssued;

    public Member(
            int id,
            String name,
            String phone,
            String email
    ) {
        super(id, name, phone, email);
        this.booksIssued = 0;
    }

    public int getBooksIssued() {
        return booksIssued;
    }

    public void incrementBooksIssued() {
        booksIssued++;
    }

    public void decrementBooksIssued() {
        if (booksIssued > 0) {
            booksIssued--;
        }
    }

    @Override
    public void displayDetails() {

        System.out.printf(
                "%-10d %-25s %-18s %-35s %-10d%n",
                getId(),
                getName(),
                getPhone(),
                getEmail(),
                booksIssued
        );
    }
}


// ============================================================
// LIBRARIAN
// ============================================================

class Librarian extends Person {

    private String employeeId;

    public Librarian(
            int id,
            String name,
            String phone,
            String email,
            String employeeId
    ) {
        super(id, name, phone, email);
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public void displayDetails() {

        System.out.println();
        System.out.println("Librarian Details");
        System.out.println("-----------------");
        System.out.println("ID          : " + getId());
        System.out.println("Name        : " + getName());
        System.out.println("Phone       : " + getPhone());
        System.out.println("Email       : " + getEmail());
        System.out.println("Employee ID : " + employeeId);
    }
}


// ============================================================
// BOOK
// ============================================================

class Book {

    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean available;

    public Book(
            int bookId,
            String title,
            String author,
            String category
    ) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}


// ============================================================
// ISSUE RECORD
// ============================================================

class IssueRecord {

    private int issueId;
    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate dueDate;

    private double fine;
    private boolean finePaid;

    public IssueRecord(
            int issueId,
            Book book,
            Member member,
            LocalDate issueDate,
            LocalDate dueDate
    ) {
        this.issueId = issueId;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;

        this.fine = 0.0;
        this.finePaid = false;
    }

    public int getIssueId() {
        return issueId;
    }

    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public boolean isFinePaid() {
        return finePaid;
    }

    public void setFinePaid(boolean finePaid) {
        this.finePaid = finePaid;
    }

    public void displayIssueRecord() {

        String paymentStatus;

        if (fine <= 0) {
            paymentStatus = "NO FINE";
        } else if (finePaid) {
            paymentStatus = "PAID";
        } else {
            paymentStatus = "PENDING";
        }

        System.out.printf(
                "%-8d %-8d %-25s %-10d %-20s %-15s %-15s %-10.2f %-12s%n",
                issueId,
                book.getBookId(),
                book.getTitle(),
                member.getId(),
                member.getName(),
                issueDate,
                dueDate,
                fine,
                paymentStatus
        );
    }
}


// ============================================================
// CONSOLE COLORS
// ============================================================

final class ConsoleColors {

    private ConsoleColors() {
    }

    public static final String RESET = "\u001B[0m";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BOLD = "\u001B[1m";
}


// ============================================================
// INPUT UTIL
// ============================================================

final class InputUtil {

    private static final Scanner SCANNER =
            new Scanner(System.in);

    private InputUtil() {
    }


    // --------------------------------------------------------
    // INTEGER VALIDATION
    // --------------------------------------------------------

    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = SCANNER.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        ConsoleColors.RED +
                        "Oops! Please enter a valid number." +
                        ConsoleColors.RESET
                );
            }
        }
    }


    // --------------------------------------------------------
    // STRING VALIDATION
    // --------------------------------------------------------

    public static String readString(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    SCANNER.nextLine().trim();

            if (!input.isEmpty()) {

                return input;
            }

            System.out.println(
                    ConsoleColors.RED +
                    "Input cannot be empty. Please try again." +
                    ConsoleColors.RESET
            );
        }
    }


    // --------------------------------------------------------
    // PHONE VALIDATION
    // --------------------------------------------------------

    public static String readPhoneNumber() {

        while (true) {

            System.out.print(
                    "Enter phone number: "
            );

            String phone =
                    SCANNER.nextLine().trim();

            if (phone.matches("\\d{10}")) {

                return phone;
            }

            System.out.println();

            System.out.println(
                    ConsoleColors.RED +
                     "Enter valid phone number " +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.YELLOW +
                    "Please enter exactly 10 digits." +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.CYAN +
                    "Example: 8186018362" +
                    ConsoleColors.RESET
            );

            System.out.println();
        }
    }


    // --------------------------------------------------------
    // GMAIL VALIDATION
    // --------------------------------------------------------

    public static String readGmail() {

        while (true) {

            System.out.print(
                    "Enter Gmail address: "
            );

            String email =
                    SCANNER.nextLine().trim();

            if (
                    email.matches(
                            "^[A-Za-z0-9._%+-]+@gmail\\.com$"
                    )
            ) {

                return email;
            }

            System.out.println();

            System.out.println(
                    ConsoleColors.RED +
                    "Oops! That doesn't look like a valid Gmail address." +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.YELLOW +
                    "Please enter your Gmail in this format:" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.CYAN +
                    "Example: yourname@gmail.com" +
                    ConsoleColors.RESET
            );

            System.out.println();
        }
    }


    // --------------------------------------------------------
    // PHONE VALIDATION WITHOUT INPUT
    // --------------------------------------------------------

    public static boolean isValidPhone(String phone) {

        return phone != null &&
                phone.matches("\\d{10}");
    }


    // --------------------------------------------------------
    // GMAIL VALIDATION WITHOUT INPUT
    // --------------------------------------------------------

    public static boolean isValidGmail(String email) {

        return email != null &&
                email.matches(
                        "^[A-Za-z0-9._%+-]+@gmail\\.com$"
                );
    }


    // --------------------------------------------------------
    // PAUSE
    // --------------------------------------------------------

    public static void pause() {

        System.out.println();

        System.out.print(
                ConsoleColors.YELLOW +
                "Press ENTER to continue..." +
                ConsoleColors.RESET
        );

        SCANNER.nextLine();
    }


    // --------------------------------------------------------
    // CLOSE
    // --------------------------------------------------------

    public static void close() {

        SCANNER.close();
    }
}


// ============================================================
// LIBRARY SERVICE
// ============================================================

class LibraryService {

    private final List<Book> books;
    private final List<Member> members;
    private final List<IssueRecord> issueRecords;

    private int nextBookId;
    private int nextMemberId;
    private int nextIssueId;

    private static final int MAX_BOOKS_PER_MEMBER = 3;

    private static final int LOAN_PERIOD_DAYS = 14;

    private static final double FINE_PER_DAY = 5.0;


    // ========================================================
    // CONSTRUCTOR
    // ========================================================

    public LibraryService() {

        books = new ArrayList<>();
        members = new ArrayList<>();
        issueRecords = new ArrayList<>();

        nextBookId = 1001;
        nextMemberId = 501;
        nextIssueId = 1;

        loadSampleData();
    }


    // ========================================================
    // SAMPLE DATA
    // ========================================================

    private void loadSampleData() {

        addBook(
                "Java Programming",
                "Herbert Schildt",
                "Programming"
        );

        addBook(
                "Clean Code",
                "Robert C. Martin",
                "Programming"
        );

        addBook(
                "Data Structures",
                "Seymour Lipschutz",
                "Computer Science"
        );

        addBook(
                "The Alchemist",
                "Paulo Coelho",
                "Novel"
        );

        addBook(
                "Atomic Habits",
                "James Clear",
                "Self Help"
        );

        addBook(
                "Think and Grow Rich",
                "Napoleon Hill",
                "Self Help"
        );

        addBook(
                "Zero to One",
                "Peter Thiel",
                "Business"
        );

        addBook(
                "Introduction to Algorithms",
                "Thomas H. Cormen et al",
                "Computer Science"
        );

        addBook(
                "Power of Now",
                "Eckhart Tolle",
                "Self Help"
        );


        addMember(
                "anji",
                "9876543210",
                "anji@gmail.com"
        );

        addMember(
                "Xyz",
                "9876501234",
                "Xyz78@gmail.com"
        );

        addMember(
                "veeranji",
                "9875677475",
                "veeranji@gmail.com"
        );

        addMember(
                "Nithin",
                "9893457143",
                "Nithin767@gmail.com"
        );

        addMember(
                "vignesh",
                "7653498966",
                "vig8687@gmail.com"
        );
    }


    // ========================================================
    // BOOK OPERATIONS
    // ========================================================

    public Book addBook(
            String title,
            String author,
            String category
    ) {

        Book book =
                new Book(
                        nextBookId++,
                        title,
                        author,
                        category
                );

        books.add(book);

        return book;
    }


    public Book findBookById(int bookId) {

        for (Book book : books) {

            if (book.getBookId() == bookId) {

                return book;
            }
        }

        return null;
    }


    public boolean removeBook(int bookId) {

        Book book =
                findBookById(bookId);

        if (
                book == null ||
                !book.isAvailable()
        ) {

            return false;
        }

        return books.remove(book);
    }


    public void displayAllBooks() {

        if (books.isEmpty()) {

            System.out.println(
                    ConsoleColors.RED +
                    "No books found." +
                    ConsoleColors.RESET
            );

            return;
        }

        System.out.println();

        printBookHeader();

        for (Book book : books) {

            displayBook(book);
        }
    }


    public void searchBook(String keyword) {

        boolean found = false;

        String searchTerm =
                keyword.toLowerCase();

        for (Book book : books) {

            boolean matches =

                    book.getTitle()
                            .toLowerCase()
                            .contains(searchTerm)

                    ||

                    book.getAuthor()
                            .toLowerCase()
                            .contains(searchTerm)

                    ||

                    book.getCategory()
                            .toLowerCase()
                            .contains(searchTerm);

            if (matches) {

                if (!found) {

                    printBookHeader();
                }

                displayBook(book);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    ConsoleColors.RED +
                    "No matching books found." +
                    ConsoleColors.RESET
            );
        }
    }


    private void printBookHeader() {

        System.out.println(
                ConsoleColors.CYAN +
                "==================== BOOKS ====================" +
                ConsoleColors.RESET
        );

        System.out.printf(
                "%-8s %-30s %-25s %-18s %-12s%n",
                "ID",
                "TITLE",
                "AUTHOR",
                "CATEGORY",
                "STATUS"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );
    }


    private void displayBook(Book book) {

        String status =

                book.isAvailable()

                        ? ConsoleColors.GREEN +
                          "AVAILABLE" +
                          ConsoleColors.RESET

                        : ConsoleColors.RED +
                          "ISSUED" +
                          ConsoleColors.RESET;

        System.out.printf(
                "%-8d %-30s %-25s %-18s %s%n",
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                status
        );
    }


    // ========================================================
    // MEMBER OPERATIONS
    // ========================================================

    public Member addMember(
            String name,
            String phone,
            String email
    ) {

        Member member =
                new Member(
                        nextMemberId++,
                        name,
                        phone,
                        email
                );

        members.add(member);

        return member;
    }


    public boolean findMemberByName(String name) {

        for (Member member : members) {

            if (
                    member.getName()
                            .equalsIgnoreCase(name)
            ) {

                return true;
            }
        }

        return false;
    }


    public Member findMemberById(int memberId) {

        for (Member member : members) {

            if (member.getId() == memberId) {

                return member;
            }
        }

        return null;
    }


    public Member findMemberByPhone(String phone) {

        for (Member member : members) {

            if (member.getPhone().equals(phone)) {

                return member;
            }
        }

        return null;
    }


    public boolean removeMember(int memberId) {

        Member member =
                findMemberById(memberId);

        if (
                member == null ||
                member.getBooksIssued() > 0
        ) {

            return false;
        }

        return members.remove(member);
    }


    public void displayAllMembers() {

        if (members.isEmpty()) {

            System.out.println(
                    ConsoleColors.RED +
                    "No members found." +
                    ConsoleColors.RESET
            );

            return;
        }

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "==================== MEMBERS ====================" +
                ConsoleColors.RESET
        );

        System.out.printf(
                "%-10s %-25s %-18s %-35s %-10s%n",
                "ID",
                "NAME",
                "PHONE",
                "EMAIL",
                "BOOKS"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (Member member : members) {

            member.displayDetails();
        }
    }


    // ========================================================
    // ISSUE BOOK
    // ========================================================

    public IssueRecord issueBook(
            int bookId,
            int memberId
    ) {

        Book book =
                findBookById(bookId);

        if (book == null) {

            return null;
        }

        Member member =
                findMemberById(memberId);

        if (member == null) {

            return null;
        }

        if (!book.isAvailable()) {

            return null;
        }

        if (
                member.getBooksIssued()
                        >= MAX_BOOKS_PER_MEMBER
        ) {

            return null;
        }

        LocalDate issueDate =
                LocalDate.now();

        LocalDate dueDate =
                issueDate.plusDays(
                        LOAN_PERIOD_DAYS
                );

        IssueRecord record =

                new IssueRecord(
                        nextIssueId++,
                        book,
                        member,
                        issueDate,
                        dueDate
                );

        issueRecords.add(record);

        book.setAvailable(false);

        member.incrementBooksIssued();

        return record;
    }


    // ========================================================
    // RETURN BOOK
    // ========================================================

    public double returnBook(int bookId) {

        IssueRecord record =
                findIssueByBookId(bookId);

        if (record == null) {

            return -1;
        }

        LocalDate returnDate =
                LocalDate.now();

        long lateDays = 0;

        if (
                returnDate.isAfter(
                        record.getDueDate()
                )
        ) {

            lateDays =
                    ChronoUnit.DAYS.between(
                            record.getDueDate(),
                            returnDate
                    );
        }

        double fine =
                lateDays * FINE_PER_DAY;

        record.setFine(fine);

        record.getBook()
                .setAvailable(true);

        record.getMember()
                .decrementBooksIssued();

        if (fine <= 0) {

            issueRecords.remove(record);
        }

        return fine;
    }


    // ========================================================
    // CALCULATE FINE
    // ========================================================

    public double calculateFine(int bookId) {

        IssueRecord record =
                findIssueByBookId(bookId);

        if (record == null) {

            return -1;
        }

        if (
                record.getFine() > 0 &&
                !record.isFinePaid()
        ) {

            return record.getFine();
        }

        LocalDate today =
                LocalDate.now();

        if (
                !today.isAfter(
                        record.getDueDate()
                )
        ) {

            return 0;
        }

        long lateDays =
                ChronoUnit.DAYS.between(
                        record.getDueDate(),
                        today
                );

        return lateDays * FINE_PER_DAY;
    }


    // ========================================================
    // PAY FINE
    // ========================================================

    public boolean payFine(int bookId) {

        IssueRecord record =
                findIssueByBookId(bookId);

        if (record == null) {

            return false;
        }

        if (record.getFine() <= 0) {

            double calculatedFine =
                    calculateFine(bookId);

            if (calculatedFine <= 0) {

                return false;
            }

            record.setFine(calculatedFine);
        }

        if (record.isFinePaid()) {

            return false;
        }

        record.setFinePaid(true);

        if (
                record.getBook()
                        .isAvailable()
        ) {

            issueRecords.remove(record);
        }

        return true;
    }


    // ========================================================
    // FIND ISSUE RECORD
    // ========================================================

    private IssueRecord findIssueByBookId(
            int bookId
    ) {

        for (
                IssueRecord record :
                issueRecords
        ) {

            if (
                    record.getBook()
                            .getBookId()
                            == bookId
            ) {

                return record;
            }
        }

        return null;
    }


    // ========================================================
    // DISPLAY ISSUED BOOKS
    // ========================================================

    public void displayIssuedBooks() {

        if (issueRecords.isEmpty()) {

            System.out.println(
                    ConsoleColors.YELLOW +
                    "No active issue records." +
                    ConsoleColors.RESET
            );

            return;
        }

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "================ ISSUED BOOKS ================" +
                ConsoleColors.RESET
        );

        System.out.printf(
                "%-8s %-8s %-25s %-10s %-20s %-15s %-15s %-10s %-12s%n",
                "ISSUE ID",
                "BOOK ID",
                "BOOK",
                "MEMBER ID",
                "MEMBER",
                "ISSUE DATE",
                "DUE DATE",
                "FINE",
                "PAYMENT"
        );

        System.out.println(
                "---------------------------------------------------------------------------------------------------------------"
        );

        for (
                IssueRecord record :
                issueRecords
        ) {

            record.displayIssueRecord();
        }
    }


    // ========================================================
    // STATISTICS
    // ========================================================

    public void displayStatistics() {

        int availableBooks = 0;

        int issuedBooks = 0;

        for (Book book : books) {

            if (book.isAvailable()) {

                availableBooks++;

            } else {

                issuedBooks++;
            }
        }

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "============================================" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.BOLD +
                ConsoleColors.YELLOW +
                "           LIBRARY STATISTICS" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.CYAN +
                "============================================" +
                ConsoleColors.RESET
        );

        System.out.println(
                "Total Books     : " +
                ConsoleColors.YELLOW +
                books.size() +
                ConsoleColors.RESET
        );

        System.out.println(
                "Available Books : " +
                ConsoleColors.GREEN +
                availableBooks +
                ConsoleColors.RESET
        );

        System.out.println(
                "Issued Books    : " +
                ConsoleColors.RED +
                issuedBooks +
                ConsoleColors.RESET
        );

        System.out.println(
                "Total Members   : " +
                ConsoleColors.BLUE +
                members.size() +
                ConsoleColors.RESET
        );

        System.out.println(
                "Active Issues   : " +
                ConsoleColors.PURPLE +
                issueRecords.size() +
                ConsoleColors.RESET
        );
    }
}


// ============================================================
// MAIN CLASS
// ============================================================

public class Main {

    private static final LibraryService library =
            new LibraryService();


    /*
     * Default librarian details.
     *
     * These details are also validated when the
     * librarian tries to log in.
     */
    private static final Librarian librarian =
            new Librarian(
                    1,
                    "manishi",
                    "8186018352",
                    "adminlibrary@gmail.com",
                    "LIB001"
            );


    // ========================================================
    // MAIN METHOD
    // ========================================================

    public static void main(String[] args) {

        boolean applicationRunning = true;


        // ====================================================
        // APPLICATION LOOP
        // ====================================================

        while (applicationRunning) {

            System.out.println();

            System.out.println(
                    ConsoleColors.BLUE +
                    "==================== ROLE SELECTION ====================" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    "1. Visitor"
            );

            System.out.println(
                    "2. Librarian"
            );

            System.out.println(
                    "3. Exit Application"
            );

            System.out.println(
                    ConsoleColors.BLUE +
                    "--------------------------------------------------" +
                    ConsoleColors.RESET
            );


            int roleChoice =
                    InputUtil.readInt(
                            "Choose your role: "
                    );


            switch (roleChoice) {

                case 1:

                    visitorOperations();

                    break;


                case 2:

                    librarianOperations();

                    break;


                case 3:

                    applicationRunning = false;

                    System.out.println();

                    System.out.println(
                            ConsoleColors.GREEN +
                            "Thank you for using Library Management System!" +
                            ConsoleColors.RESET
                    );

                    System.out.println(
                            ConsoleColors.CYAN +
                            "Application closed successfully." +
                            ConsoleColors.RESET
                    );

                    break;


                default:

                    System.out.println();

                    System.out.println(
                            ConsoleColors.RED +
                            "Invalid choice." +
                            ConsoleColors.RESET
                    );

                    System.out.println(
                            ConsoleColors.YELLOW +
                            "Please select 1, 2 or 3." +
                            ConsoleColors.RESET
                    );
            }
        }

        InputUtil.close();
    }


    // ========================================================
    // VISITOR OPERATIONS
    // ========================================================

    private static void visitorOperations() {

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "================ VISITOR =================" +
                ConsoleColors.RESET
        );


        System.out.println();

        System.out.println(
                "Are you a first-time visitor?"
        );

        System.out.println(
                "1. Yes"
        );

        System.out.println(
                "2. No"
        );


        int visitorType =
                InputUtil.readInt(
                        "Enter choice: "
                );


        if (visitorType == 1) {

            addMember();

        }

        else if (visitorType == 2) {

            String name =
                    InputUtil.readString(
                            "Enter your name: "
                    );

            if (
                    !library.findMemberByName(name)
            ) {

                System.out.println();

                System.out.println(
                        ConsoleColors.YELLOW +
                        "We couldn't find your details in our records." +
                        ConsoleColors.RESET
                );

                System.out.println(
                        ConsoleColors.CYAN +
                        "Let's register you as a new visitor." +
                        ConsoleColors.RESET
                );

                addMember();
            }

            else {

                System.out.println();

                System.out.println(
                        ConsoleColors.GREEN +
                        "Welcome back, " +
                        name +
                        "!" +
                        ConsoleColors.RESET
                );
            }

        }

        else {

            System.out.println(
                    ConsoleColors.RED +
                    "Invalid option. choose coorect option " +
                    ConsoleColors.RESET
            );

            return;
        }


        boolean running = true;


        // ====================================================
        // VISITOR MENU LOOP
        // ====================================================

        while (running) {

            showMenu();


            int choice =
                    InputUtil.readInt(
                            "Enter your choice: "
                    );


            switch (choice) {

                case 1:

                    library.displayAllBooks();

                    InputUtil.pause();

                    break;


                case 2:

                    searchBook();

                    break;


                case 3:

                    library.displayAllMembers();

                    InputUtil.pause();

                    break;


                case 4:

                    issueBook();

                    break;


                case 5:

                    returnBook();

                    break;


                case 6:

                    library.displayIssuedBooks();

                    InputUtil.pause();

                    break;


                case 7:

                    library.displayStatistics();

                    InputUtil.pause();

                    break;


                case 8:

                    showLibrarian();

                    break;


                case 9:

                    payFine();

                    break;


                case 10:

                    running = false;

                    System.out.println();

                    System.out.println(
                            ConsoleColors.GREEN +
                            "Visitor session ended." +
                            ConsoleColors.RESET
                    );

                    System.out.println(
                            ConsoleColors.CYAN +
                            "Returning to role selection..." +
                            ConsoleColors.RESET
                    );

                    break;


                default:

                    System.out.println();

                    System.out.println(
                            ConsoleColors.RED +
                            "Invalid choice." +
                            ConsoleColors.RESET
                    );

                    System.out.println(
                            ConsoleColors.YELLOW +
                            "Please select a number between 1 and 10." +
                            ConsoleColors.RESET
                    );
            }
        }
    }


    // ========================================================
    // LIBRARIAN OPERATIONS
    // ========================================================

    private static void librarianOperations() {

        System.out.println();

        System.out.println(
                ConsoleColors.PURPLE +
                "================ LIBRARIAN LOGIN ================" +
                ConsoleColors.RESET
        );


        // ----------------------------------------------------
        // LIBRARIAN NAME
        // ----------------------------------------------------

        String name =
                InputUtil.readString(
                        "Enter librarian name: "
                );


        // ----------------------------------------------------
        // LIBRARIAN PHONE VALIDATION
        // ----------------------------------------------------

        String phone =
                InputUtil.readPhoneNumber();


        // ----------------------------------------------------
        // LIBRARIAN GMAIL VALIDATION
        // ----------------------------------------------------

        String email =
                InputUtil.readGmail();


        // ----------------------------------------------------
        // CHECK LIBRARIAN DETAILS
        // ----------------------------------------------------

        if (
                !librarian.getName()
                        .equalsIgnoreCase(name)
        ) {

            System.out.println();

            System.out.println(
                    ConsoleColors.RED +
                    "Librarian name does not match our records." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        if (
                !librarian.getPhone()
                        .equals(phone)
        ) {

            System.out.println();

            System.out.println(
                    ConsoleColors.RED +
                    " Phone number does not match our records." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        if (
                !librarian.getEmail()
                        .equalsIgnoreCase(email)
        ) {

            System.out.println();

            System.out.println(
                    ConsoleColors.RED +
                    " Gmail address does not match our records." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        System.out.println();

        System.out.println(
                ConsoleColors.GREEN +
                " Librarian verification successful!" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.CYAN +
                "Welcome, " +
                librarian.getName() +
                "!" +
                ConsoleColors.RESET
        );


        boolean running = true;


        // ====================================================
        // LIBRARIAN MENU LOOP
        // ====================================================

        while (running) {

            System.out.println();

            System.out.println(
                    ConsoleColors.PURPLE +
                    "================ LIBRARIAN MENU ================" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    "1. Add Book"
            );

            System.out.println(
                    "2. Remove Book"
            );

            System.out.println(
                    "3. Remove Member"
            );

            System.out.println(
                    "4. View All Books"
            );

            System.out.println(
                    "5. View All Members"
            );

            System.out.println(
                    "6. View Statistics"
            );

            System.out.println(
                    "7. View My Details"
            );

            System.out.println(
                    "8. Exit Librarian Session"
            );


            int choice =
                    InputUtil.readInt(
                            "Enter your choice: "
                    );


            switch (choice) {

                case 1:

                    addBook();

                    break;


                case 2:

                    removeBook();

                    break;


                case 3:

                    removeMember();

                    break;


                case 4:

                    library.displayAllBooks();

                    InputUtil.pause();

                    break;


                case 5:

                    library.displayAllMembers();

                    InputUtil.pause();

                    break;


                case 6:

                    library.displayStatistics();

                    InputUtil.pause();

                    break;


                case 7:

                    showLibrarian();

                    break;


                case 8:

                    running = false;

                    System.out.println();

                    System.out.println(
                            ConsoleColors.GREEN +
                            "Librarian session ended." +
                            ConsoleColors.RESET
                    );

                    System.out.println(
                            ConsoleColors.CYAN +
                            "Returning to role selection..." +
                            ConsoleColors.RESET
                    );

                    break;


                default:

                    System.out.println();

                    System.out.println(
                            ConsoleColors.RED +
                            "Invalid choice." +
                            ConsoleColors.RESET
                    );

                    System.out.println(
                            ConsoleColors.YELLOW +
                            "Please select a number between 1 and 8." +
                            ConsoleColors.RESET
                    );
            }
        }
    }


    // ========================================================
    // ADD BOOK
    // ========================================================

    private static void addBook() {

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "--------------- ADD BOOK ---------------" +
                ConsoleColors.RESET
        );


        String title =
                InputUtil.readString(
                        "Enter book title: "
                );


        String author =
                InputUtil.readString(
                        "Enter author name: "
                );


        String category =
                InputUtil.readString(
                        "Enter category: "
                );


        Book book =
                library.addBook(
                        title,
                        author,
                        category
                );


        System.out.println();

        System.out.println(
                ConsoleColors.GREEN +
                "Book added successfully!" +
                ConsoleColors.RESET
        );

        System.out.println(
                "Book ID: " +
                ConsoleColors.YELLOW +
                book.getBookId() +
                ConsoleColors.RESET
        );


        InputUtil.pause();
    }


    // ========================================================
    // SEARCH BOOK
    // ========================================================

    private static void searchBook() {

        System.out.println();

        String keyword =
                InputUtil.readString(
                        "Enter title, author or category: "
                );

        library.searchBook(keyword);

        InputUtil.pause();
    }


    // ========================================================
    // REMOVE BOOK
    // ========================================================

    private static void removeBook() {

        System.out.println();

        int bookId =
                InputUtil.readInt(
                        "Enter Book ID to remove: "
                );


        Book book =
                library.findBookById(bookId);


        if (book == null) {

            System.out.println(
                    ConsoleColors.RED +
                    "Book not found." +
                    ConsoleColors.RESET
            );

        }

        else if (!book.isAvailable()) {

            System.out.println(
                    ConsoleColors.RED +
                    "Book is currently issued." +
                    ConsoleColors.RESET
            );

        }

        else {

            library.removeBook(bookId);

            System.out.println(
                    ConsoleColors.GREEN +
                    "Book removed successfully!" +
                    ConsoleColors.RESET
            );
        }


        InputUtil.pause();
    }


    // ========================================================
    // ADD MEMBER
    // ========================================================

    private static void addMember() {

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "--------------- ADD MEMBER ---------------" +
                ConsoleColors.RESET
        );


        String name =
                InputUtil.readString(
                        "Enter member name: "
                );


        // ----------------------------------------------------
        // PHONE VALIDATION
        // ----------------------------------------------------

        String phone =
                InputUtil.readPhoneNumber();


        // ----------------------------------------------------
        // GMAIL VALIDATION
        // ----------------------------------------------------

        String email =
                InputUtil.readGmail();


        Member member =
                library.addMember(
                        name,
                        phone,
                        email
                );


        System.out.println();

        System.out.println(
                ConsoleColors.GREEN +
                "✅ Member added successfully!" +
                ConsoleColors.RESET
        );

        System.out.println(
                "Member ID: " +
                ConsoleColors.YELLOW +
                member.getId() +
                ConsoleColors.RESET
        );


        InputUtil.pause();
    }


    // ========================================================
    // REMOVE MEMBER
    // ========================================================

    private static void removeMember() {

        System.out.println();

        int memberId =
                InputUtil.readInt(
                        "Enter Member ID to remove: "
                );


        Member member =
                library.findMemberById(memberId);


        if (member == null) {

            System.out.println(
                    ConsoleColors.RED +
                    "Member not found." +
                    ConsoleColors.RESET
            );

        }

        else if (
                member.getBooksIssued() > 0
        ) {

            System.out.println(
                    ConsoleColors.RED +
                    "Member has issued books. Return them first." +
                    ConsoleColors.RESET
            );

        }

        else {

            library.removeMember(memberId);

            System.out.println(
                    ConsoleColors.GREEN +
                    "Member removed successfully." +
                    ConsoleColors.RESET
            );
        }


        InputUtil.pause();
    }


    // ========================================================
    // ISSUE BOOK
    // ========================================================

    private static void issueBook() {

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "--------------- ISSUE BOOK ---------------" +
                ConsoleColors.RESET
        );


        int bookId =
                InputUtil.readInt(
                        "Enter Book ID: "
                );


        int memberId =
                InputUtil.readInt(
                        "Enter Member ID: "
                );


        Book book =
                library.findBookById(bookId);


        if (book == null) {

            System.out.println(
                    ConsoleColors.RED +
                    "Book not found." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        if (!book.isAvailable()) {

            System.out.println(
                    ConsoleColors.RED +
                    "Book is already issued." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        Member member =
                library.findMemberById(memberId);


        if (member == null) {

            System.out.println(
                    ConsoleColors.RED +
                    "Member not found." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        if (member.getBooksIssued() >= 3) {

            System.out.println(
                    ConsoleColors.RED +
                    "This member has already reached the maximum limit of 3 books." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        IssueRecord record =
                library.issueBook(
                        bookId,
                        memberId
                );


        if (record == null) {

            System.out.println(
                    ConsoleColors.RED +
                    "Unable to issue the book." +
                    ConsoleColors.RESET
            );

        }

        else {

            System.out.println();

            System.out.println(
                    ConsoleColors.GREEN +
                    "============================================" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.GREEN +
                    "        BOOK ISSUED SUCCESSFULLY" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.GREEN +
                    "============================================" +
                    ConsoleColors.RESET
            );


            System.out.println(
                    "Book       : " +
                    book.getTitle()
            );

            System.out.println(
                    "Member     : " +
                    member.getName()
            );

            System.out.println(
                    "Issue Date : " +
                    record.getIssueDate()
            );

            System.out.println(
                    "Due Date   : " +
                    record.getDueDate()
            );
        }


        InputUtil.pause();
    }


    // ========================================================
    // RETURN BOOK
    // ========================================================

    private static void returnBook() {

        System.out.println();

        int bookId =
                InputUtil.readInt(
                        "Enter Book ID: "
                );


        Book book =
                library.findBookById(bookId);


        if (book == null) {

            System.out.println(
                    ConsoleColors.RED +
                    "Book not found." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        if (book.isAvailable()) {

            System.out.println(
                    ConsoleColors.RED +
                    "This book is not currently issued." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        double fine =
                library.returnBook(bookId);


        System.out.println();

        System.out.println(
                ConsoleColors.GREEN +
                "============================================" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.GREEN +
                "       BOOK RETURNED SUCCESSFULLY" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.GREEN +
                "============================================" +
                ConsoleColors.RESET
        );


        System.out.println(
                "Book: " +
                book.getTitle()
        );


        if (fine > 0) {

            System.out.println(
                    ConsoleColors.RED +
                    "Fine: ₹" +
                    String.format(
                            "%.2f",
                            fine
                    ) +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.YELLOW +
                    "Please select option 9 to pay the fine." +
                    ConsoleColors.RESET
            );

        }

        else {

            System.out.println(
                    ConsoleColors.GREEN +
                    "Fine: ₹0.00" +
                    ConsoleColors.RESET
            );
        }


        InputUtil.pause();
    }


    // ========================================================
    // PAY FINE
    // ========================================================

    private static void payFine() {

        System.out.println();

        System.out.println(
                ConsoleColors.CYAN +
                "--------------- PAY FINE ---------------" +
                ConsoleColors.RESET
        );


        int bookId =
                InputUtil.readInt(
                        "Enter Book ID: "
                );


        double fine =
                library.calculateFine(bookId);


        if (fine < 0) {

            System.out.println(
                    ConsoleColors.RED +
                    "No issue record found for this book." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        if (fine <= 0) {

            System.out.println(
                    ConsoleColors.GREEN +
                    "There is no pending fine for this book." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        System.out.println();

        System.out.println(
                "Fine Amount : " +
                ConsoleColors.RED +
                "₹" +
                String.format(
                        "%.2f",
                        fine
                ) +
                ConsoleColors.RESET
        );


        System.out.println();

        System.out.println(
                "Confirm payment? yes/no"
        );


        String confirmation =
                InputUtil.readString(
                        "Enter choice: "
                );


        if (
                !confirmation.equalsIgnoreCase(
                        "yes"
                )
        ) {

            System.out.println(
                    ConsoleColors.YELLOW +
                    "Payment cancelled." +
                    ConsoleColors.RESET
            );

            InputUtil.pause();

            return;
        }


        boolean paid =
                library.payFine(bookId);


        if (paid) {

            System.out.println();

            System.out.println(
                    ConsoleColors.GREEN +
                    "============================================" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.GREEN +
                    "             PAYMENT SUCCESSFUL" +
                    ConsoleColors.RESET
            );

            System.out.println(
                    ConsoleColors.GREEN +
                    "============================================" +
                    ConsoleColors.RESET
            );


            System.out.println(
                    "Book ID     : " +
                    bookId
            );

            System.out.println(
                    "Fine Paid   : ₹" +
                    String.format(
                            "%.2f",
                            fine
                    )
            );

            System.out.println(
                    ConsoleColors.GREEN +
                    "Fine status : PAID" +
                    ConsoleColors.RESET
            );

        }

        else {

            System.out.println(
                    ConsoleColors.RED +
                    "Unable to process payment." +
                    ConsoleColors.RESET
            );
        }


        InputUtil.pause();
    }


    // ========================================================
    // LIBRARIAN DETAILS
    // ========================================================

    private static void showLibrarian() {

        System.out.println();

        /*
         * POLYMORPHISM
         *
         * Person reference points to Librarian object.
         */

        Person person =
                librarian;

        person.displayDetails();

        InputUtil.pause();
    }


    // ========================================================
    // VISITOR MENU
    // ========================================================

    private static void showMenu() {

        System.out.println();

        System.out.println(
                ConsoleColors.BLUE +
                "==================== VISITOR MENU ====================" +
                ConsoleColors.RESET
        );

        System.out.println(
                "1.  View All Books"
        );

        System.out.println(
                "2.  Search Book"
        );

        System.out.println(
                "3.  View Members"
        );

        System.out.println(
                "4.  Issue Book"
        );

        System.out.println(
                "5.  Return Book"
        );

        System.out.println(
                "6.  View Issued Books"
        );

        System.out.println(
                "7.  Library Statistics"
        );

        System.out.println(
                "8.  View Librarian Details"
        );

        System.out.println(
                ConsoleColors.YELLOW +
                "9.  Pay Fine" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.RED +
                "10. Exit Visitor Session" +
                ConsoleColors.RESET
        );

        System.out.println(
                ConsoleColors.BLUE +
                "======================================================" +
                ConsoleColors.RESET
        );
    }
}
