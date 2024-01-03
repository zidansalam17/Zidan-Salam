
import json
from tkinter import Tk, Label, Button, Entry, Listbox, messagebox
from datetime import datetime

class ZidanCarsRentalApp:
    def __init__(self, master):
        self.master = master
        master.title("Aplikasi Rental Zidan Car's")

        self.transaksi_file = "transaksi.json"
        self.transaksi = []

        self.load_transaksi()

        self.label = Label(master, text="Selamat datang di Zidan Car's Rental")
        self.label.pack()

        self.listbox = Listbox(master, height=10, width=50)
        self.listbox.pack()

        self.tampilkan_transaksi()

        self.button_tambah = Button(master, text="Tambah Transaksi", command=self.tambah_transaksi)
        self.button_tambah.pack()

        self.button_keluar = Button(master, text="Keluar", command=master.quit)
        self.button_keluar.pack()

    def load_transaksi(self):
        try:
            with open(self.transaksi_file, 'r') as file:
                self.transaksi = json.load(file)
        except FileNotFoundError:
            self.transaksi = []

    def save_transaksi(self):
        with open(self.transaksi_file, 'w') as file:
            json.dump(self.transaksi, file, indent=2)

    def tampilkan_transaksi(self):
        self.listbox.delete(0, 'end')
        for idx, transaksi in enumerate(self.transaksi, 1):
            self.listbox.insert('end', f"{idx}. Tanggal: {transaksi['tanggal']}, Mobil: {transaksi['mobil']}, Biaya: {transaksi['biaya']}")

    def tambah_transaksi(self):
        self.toplevel = Tk()
        self.toplevel.title("Tambah Transaksi")

        label_mobil = Label(self.toplevel, text="Nama Mobil:")
        label_mobil.grid(row=0, column=0)
        self.entry_mobil = Entry(self.toplevel)
        self.entry_mobil.grid(row=0, column=1)

        label_biaya = Label(self.toplevel, text="Biaya Sewa:")
        label_biaya.grid(row=1, column=0)
        self.entry_biaya = Entry(self.toplevel)
        self.entry_biaya.grid(row=1, column=1)

        button_simpan = Button(self.toplevel, text="Simpan", command=self.simpan_transaksi)
        button_simpan.grid(row=2, columnspan=2)

    def simpan_transaksi(self):
        mobil = self.entry_mobil.get()
        biaya = self.entry_biaya.get()

        if mobil and biaya:
            tanggal = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            transaksi_baru = {"tanggal": tanggal, "mobil": mobil, "biaya": biaya}
            self.transaksi.append(transaksi_baru)
            self.save_transaksi()
            self.toplevel.destroy()
            self.tampilkan_transaksi()
        else:
            messagebox.showwarning("Peringatan", "Mohon isi semua kolom!")

if __name__ == "__main__":
    root = Tk()
    app = ZidanCarsRentalApp(root)
    root.mainloop()
