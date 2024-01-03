import json
from datetime import datetime

class ZidanCarsRental:
    def __init__(self):
        self.transaksi_file = "transaksi.json"
        self.transaksi = []

    def load_transaksi(self):
        try:
            with open(self.transaksi_file, 'r') as file:
                self.transaksi = json.load(file)
        except FileNotFoundError:
            self.transaksi = []

    def save_transaksi(self):
        with open(self.transaksi_file, 'w') as file:
            json.dump(self.transaksi, file, indent=2)

    def tampilkan_menu(self):
        print("\n===== Aplikasi Rental Zidan Car's =====")
        print("1. Tampilkan Transaksi")
        print("2. Tambah Transaksi")
        print("3. Keluar")

    def tampilkan_transaksi(self):
        print("\n===== Daftar Transaksi =====")
        for idx, transaksi in enumerate(self.transaksi, 1):
            print(f"{idx}. Tanggal: {transaksi['tanggal']}, Mobil: {transaksi['mobil']}, Biaya: {transaksi['biaya']}")

    def tambah_transaksi(self):
        print("\n===== Tambah Transaksi =====")
        tanggal = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        mobil = input("Nama Mobil: ")
        biaya = input("Biaya Sewa: ")

        transaksi_baru = {
            "tanggal": tanggal,
            "mobil": mobil,
            "biaya": biaya
        }

        self.transaksi.append(transaksi_baru)
        self.save_transaksi()
        print("Transaksi berhasil ditambahkan!")

    def run(self):
        self.load_transaksi()

        while True:
            self.tampilkan_menu()
            pilihan = input("Pilih menu (1/2/3): ")

            if pilihan == "1":
                self.tampilkan_transaksi()
            elif pilihan == "2":
                self.tambah_transaksi()
            elif pilihan == "3":
                print("Terima kasih!")
                break
            else:
                print("Pilihan tidak valid. Silakan coba lagi.")

if __name__ == "__main__":
    aplikasi = ZidanCarsRental()
    aplikasi.run()
