import java.util.Scanner;

abstract class Library {
    static Scanner sc=new Scanner(System.in);
    abstract void display();
}

class Student extends Library {
    private String StudentName;
    private String StudentId;

    Student(String StudentName, String StudentId) {
        this.StudentName = StudentName;
        this.StudentId = StudentId;
    }

    String getStudentId() {
        return this.StudentId;
    }

    String getStudentName() {
        return this.StudentName;
    }

    Boolean isMatch(String StudentName, String StudentId) {
        return this.StudentName.equals(StudentName) && this.StudentId.equals(StudentId);
    }

    void display() {
        System.out.println("\n\033[1;34mStudent Login Details:\033[0m");
        System.out.println("Student Name: " + getStudentName());
        System.out.println("Student Id: " + getStudentId());
    }
}

class Librarian {
    private Student[] students;

    Librarian(Student[] students) {
        this.students = students;
    }

    boolean isAuthorizedStudent(String StudentName, String StudentId) {
        for (Student student : students) {
            if (student.isMatch(StudentName, StudentId)) {
                return true;
            }
        }
        return false;
    }
}

class Books extends Library {
    String[][] CSE = {
        {"CSE101", "Data Structure & Algorithms", "Alfred V.Aho", "Available"},
        {"CSE102", "Artificial Intelligence", "Russell & Peter Novig", "Available"},
        {"CSE103", "Clean Code", "Robert", "Available"},
        {"CSE104", "The Pragmatic Programmer", "David Thomas", "Available"}
    };

    String[][] ECE = {
        {"ECE101", "Principles of Management", "Dr.S.Umamaheshwari", "Available"},
        {"ECE102", "Medical Electronics", "r.K.R.Valluvan", "Available"},
        {"ECE103", "VLSI Design", "Lakshman", "Available"},
        {"ECE104", "Circuit Analysis", "L.Malliga", "Available"}
    };

    String[][] Civil = {
        {"CIV101", "Structural Analysis", "Hibbeler", "Available"},
        {"CIV102", "Strength Of Materials", "R.S.Khurmi", "Available"},
        {"CIV103", "Civil Drawing", "B.P.verma", "Available"},
        {"CIV104", "Water Supply Engineering", "Santosh Kumar", "Available"}
    };

    String[][] Mech = {
        {"MECH101", "Thermodynamics", "Mike Pauken ", "Available"},
        {"MECH102", "Fluid Mechanics", "Frank M.White", "Available"},
        {"MECH103", "Robotics", "John J.Craig", "Available"},
        {"MECH104", "Machine Design", "L.Norton", "Available"}
    };

    String[][] EEE = {
        {"EEE101", "Control System", "Williams", "Available"},
        {"EEE102", "Semiconductor Device", "Jerry", "Available"},
        {"EEE103", "CD ROM", "Richard", "Available"},
        {"EEE104", "Trouble Shooting", "Roy Parks", "Available"}
    };

    void displayBooks(String department) {
        String[][] books;
        switch (department) {
            case "1":
                books = CSE;
                break;
            case "2":
                books = ECE;
                break;
            case "3":
                books = Civil;
                break;
            case "4":
                books = Mech;
                break;
            case "5":
                books = EEE;
                break;
            default:
                System.out.println("\033[1;31mInvalid department.\033[0m");
                return;
        }

        System.out.println("\n\033[1;34m" + getDepartmentName(department) + " Books:\033[0m");
        System.out.printf("\u001b[35;1m%-10s %-30s %-30s %-30s%n", "Book ID", "Book Name", "Author", "Status");
        System.out.println("\u001b[35;0m-----------------------------------------------------------------------------------\u001b[0m");
        for (String[] book : books) {
            System.out.printf("%-10s %-30s %-30s %-30s%n", book[0], book[1], book[2], book[3]);
        }
    }

    boolean borrowBook(String department, String bookId, String Title, String Author) {
        String[][] books;
        switch (department) {
            case "1":
                books = CSE;
                break;
            case "2":
                books = ECE;
                break;
            case "3":
                books = Civil;
                break;
            case "4":
                books = Mech;
                break;
            case "5":
                books = EEE;
                break;
            default:
                System.out.println("\033[1;31mInvalid department.\033[0m");
                return false;
        }
        for (String[] book : books) {
            if (book[0].equals(bookId) && book[1].equals(Title) && book[2].equals(Author) && book[3].equals("Available")) {
                System.out.println("\n\033[1;32mYou have successfully borrowed the book:\033[0m");
                System.out.println("Book ID: " + book[0]);
                System.out.println("Title: " + book[1]);
                System.out.println("Author: " + book[2]);
                book[3] = "Borrowed";
                return true;
            }
        }
        System.out.println("\n\033[1;31mBook ID is either not found or already borrowed in the " + getDepartmentName(department) + " department.\033[0m");
        return false;
    }

    boolean returnBook(String department, String bookId, String Title, String Author) {
        String[][] books;
        switch (department) {
            case "1":
                books = CSE;
                break;
            case "2":
                books = ECE;
                break;
            case "3":
                books = Civil;
                break;
            case "4":
                books = Mech;
                break;
            case "5":
                books = EEE;
                break;
            default:
                System.out.println("\033[1;31mInvalid department.\033[0m");
                return false;
        }
        for (String[] book : books) {
            if (book[0].equals(bookId) && book[1].equals(Title) && book[2].equals(Author) && book[3].equals("Borrowed")) {
                System.out.println("\n\033[1;32mYou have successfully returned the book:\033[0m");
                System.out.println("Book ID: " + book[0]);
                System.out.println("Title: " + book[1]);
                System.out.println("Author: " + book[2]);
                book[3] = "Available";
                return true;
            }
        }
        System.out.println("\n\033[1;31mBook is either not found or not borrowed in the " + getDepartmentName(department) + " department.\033[0m");
        return false;
    }

    String getDepartmentName(String department) {
        switch (department) {
            case "1":
                return "CSE";
            case "2":
                return "ECE";
            case "3":
                return "Civil";
            case "4":
                return "Mech";
            case "5":
                return "EEE";
            default:
                return "Unknown";
        }
    }

    void display() {
        while (true) {
            System.out.println("\n\033[1;34m1. CSE\033[0m");
            System.out.println("\033[1;34m2. ECE\033[0m");
            System.out.println("\033[1;34m3. Civil\033[0m");
            System.out.println("\033[1;34m4. Mech\033[0m");
            System.out.println("\033[1;34m5. EEE\033[0m");
            System.out.println("\033[1;34m6. Exit\033[0m");
            System.out.print("\033[1;35mSelect one of the above: \033[0m");
            String department = sc.nextLine();
            if (department.equals("6")) {
                System.out.println("\033[1;32mSuccessfully Exit from the ProjectLMS\033[0m");
                break;
            }

            displayBooks(department);

            while (true) {
                System.out.println("\n\033[1;34m1. Borrow a book\033[0m");
                System.out.println("\033[1;34m2. Return a book\033[0m");
                System.out.println("\033[1;34m3. Back to choose another Department\033[0m");
                System.out.println("\033[1;34m4. Exit from the application\033[0m");
                System.out.print("\033[1;35mSelect one of the above: \033[0m");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    System.out.print("\n\033[1;35mEnter the Book ID : \033[0m");
                    String bookId = sc.nextLine();
                    System.out.print("\n\033[1;35mEnter the Book Name: \033[0m");
                    String Title = sc.nextLine();
                    System.out.print("\n\033[1;35mEnter the Author Name: \033[0m");
                    String Author = sc.nextLine();
                    borrowBook(department, bookId, Title, Author);
                } else if (choice.equals("2")) {
                    System.out.print("\n\033[1;35mEnter the Book ID : \033[0m");
                    String bookId = sc.nextLine();
                    System.out.print("\n\033[1;35mEnter the Book Name: \033[0m");
                    String Title = sc.nextLine();
                    System.out.print("\n\033[1;35mEnter the Author Name: \033[0m");
                    String Author = sc.nextLine();
                    returnBook(department, bookId, Title, Author);
                } else if (choice.equals("3")) {
                    break;
                } else if (choice.equals("4")) {
                    System.out.println("\n\033[1;32mSuccessfully Exit from the ProjectLMS.\033[0m");
                    return;
                } else {
                    System.out.println("\n\033[1;31mInvalid choice.\033[0m");
                }
            }
        }
    }
}

class Main {
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        Student[] students = {
            new Student("Varalakshmi", "CV101"),
            new Student("Bhargavi", "CV102"),
            new Student("Rajeswari", "CV103"),
            new Student("Shivaram", "CV104"),
            new Student("Vishwateja", "CV105"),
            new Student("Siri", "CV106"),
            new Student("Akhila", "CV107")
        };

        Librarian librarian = new Librarian(students);

        while (true) {
            System.out.print("\n\033[1;35mEnter Student Name: \033[0m");
            String studentName = SC.next();
            System.out.print("\033[1;35mEnter Student Id: \033[0m");
            String studentId = SC.next();

            if (librarian.isAuthorizedStudent(studentName, studentId)) {
                Library library = new Student(studentName, studentId);
                library.display();
                System.out.println("\033[1;32mAuthentication Successful.\033[0m");
                System.out.println("\n\033[7m\033[1;32m\033[1mWelcome to ProjectLMS " + studentName + "!\033[0m");
                Library books = new Books();
                books.display();
                break;
            } else {
                System.out.println("\n\033[1;31mSorry! Authentication failed.\033[0m");
                System.out.println("\033[1;31mInvalid student Name or student Id\033[0m");
            }
        }

        SC.close();
    }
}