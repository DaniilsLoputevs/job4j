package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book white = new Book();
        Book red = new Book();
        Book yellow = new Book();
        Book green = new Book();

        Book[] books = new Book[4];

        white.setName("Clean code");
        red.setName("Red");
        yellow.setName("Yellow");
        green.setName("Green");

        books[0] = white;
        books[1] = red;
        books[2] = yellow;
        books[3] = green;


        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            System.out.println(bk.getName() + " - " + bk.getPages());
        }

        System.out.println();
        books[0] = green;
        books[3] = white;

        for (int index = 0; index < books.length; index++) {
            Book bk = books[index];
            String bookName = books[index].getName();
            if (bookName.equals("Clean code")) {
                System.out.println(bk.getName() + " - " + bk.getPages());
            }
        }



    }
//    private Book replace(Book books, Book first, Book second) {
//        Book temp = first;
//        books[]
//        return ;
//    }
}