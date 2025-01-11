import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Kelas GameRentalGUI
public class GameRentalGUI extends JFrame {
    private GameRental rental; // Instance dari GameRental
    private JTextField titleField; // Field untuk judul game
    private JTextField genreField; // Field untuk genre game
    private JTextArea gameListArea; // Area untuk menampilkan daftar game
    private JTextField idField; // Field untuk ID game

    public GameRentalGUI() {
        rental = new GameRental(); // Inisialisasi GameRental
        setTitle("Game Rental"); // Set judul jendela
        setSize(400, 400); // Set ukuran jendela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Operasi saat jendela ditutup
        setLayout(new BorderLayout()); // Set layout

        // Panel Input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2)); // Grid layout dengan 4 baris dan 2 kolom

        inputPanel.add(new JLabel("Title:")); // Label untuk judul
        titleField = new JTextField(); // Field untuk input judul
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Genre:")); // Label untuk genre
        genreField = new JTextField(); // Field untuk input genre
        inputPanel.add(genreField);

        inputPanel.add(new JLabel("Game ID (for update/delete):")); // Label untuk ID game
        idField = new JTextField(); // Field untuk input ID game
        inputPanel.add(idField);

        add(inputPanel, BorderLayout.NORTH); // Tambahkan panel input ke bagian atas

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Game"); // Tombol untuk menambah game
        JButton updateButton = new JButton("Update Game"); // Tombol untuk memperbarui game
        JButton deleteButton = new JButton("Delete Game"); // Tombol untuk menghapus game
        JButton listButton = new JButton("List Games"); // Tombol untuk menampilkan daftar game

        buttonPanel.add(addButton); // Tambahkan tombol ke panel
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(listButton);

        add(buttonPanel, BorderLayout.CENTER); // Tambahkan panel tombol ke tengah

        // Area Daftar Game
        gameListArea = new JTextArea(); // Area teks untuk menampilkan daftar game
        gameListArea.setEditable(false); // Tidak dapat diedit
        add(new JScrollPane(gameListArea), BorderLayout.SOUTH); // Tambahkan scroll pane

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim(); // Ambil judul
                String genre = genreField.getText().trim(); // Ambil genre
                if (title.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Title and Genre cannot be empty."); // Validasi input
                    return;
                }
                rental.addGame(title, genre); // Tambah game
                titleField.setText(""); // Kosongkan field judul
                genreField.setText(""); // Kosongkan field genre
                listGames(); // Tampilkan daftar game
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idField.getText()); // Ambil ID
                    String title = titleField.getText().trim(); // Ambil judul
                    String genre = genreField.getText().trim(); // Ambil genre
                    if (title.isEmpty() || genre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Title and Genre cannot be empty."); // Validasi input
                        return;
                    }
                    rental.updateGame(id, title, genre); // Perbarui game
                    titleField.setText(""); // Kosongkan field judul
                    genreField.setText(""); // Kosongkan field genre
                    idField.setText(""); // Kosongkan field ID
                    listGames(); // Tampilkan daftar game
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID."); // Pesan kesalahan
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idField.getText()); // Ambil ID
                    rental.deleteGame(id); // Hapus game berdasarkan ID
                    titleField.setText(""); // Kosongkan field judul
                    genreField.setText(""); // Kosongkan field genre
                    idField.setText(""); // Kosongkan field ID
                    listGames(); // Tampilkan daftar game
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID."); // Pesan kesalahan
                }
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listGames(); // Tampilkan daftar game
            }
        });
    }

    private void listGames() {
        gameListArea.setText(""); // Kosongkan area teks
        for (Game game : rental.listGames()) {
            gameListArea.append(game.toString() + "\n"); // Tambahkan setiap game ke area teks
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameRentalGUI gui = new GameRentalGUI(); // Buat instance GUI
            gui.setVisible(true); // Tampilkan GUI
        });
    }
}