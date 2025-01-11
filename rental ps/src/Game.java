import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Kelas Game
class Game {
    private static int idCounter = 0; // Counter untuk ID game
    private int id; // ID unik untuk setiap game
    private String title; // Judul game
    private String genre; // Genre game

    // Konstruktor untuk membuat objek Game
    public Game(String title, String genre) {
        this.id = ++idCounter; // Mengatur ID dengan increment
        this.title = title; // Mengatur judul
        this.genre = genre; // Mengatur genre
    }

    // Getter untuk ID
    public int getId() {
        return id;
    }

    // Getter untuk judul
    public String getTitle() {
        return title;
    }

    // Getter untuk genre
    public String getGenre() {
        return genre;
    }

    // Setter untuk judul
    public void setTitle(String title) {
        this.title = title;
    }

    // Setter untuk genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Metode untuk menampilkan informasi game
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Genre: " + genre;
    }
}
