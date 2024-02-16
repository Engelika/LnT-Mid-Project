package Main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner (System.in);
	Random rand = new Random();
	ArrayList<DataKaryawan> arrData = new ArrayList<>();
	
	
	public boolean checkUnique (String kodeKaryawan) {
		boolean checkUnique = true;
		
		for (int i = 0; i < arrData.size(); i++) {
			if (arrData.get(i).kodeKaryawan.equals(kodeKaryawan)) {
				checkUnique = false;
			}
		}
		if (checkUnique == true) {
			return true;
		}else  {
			return false;
		}
	}
	
	public String kodeKar() {
		
		char firstRandChar = (char) ('A' + rand.nextInt(26));
		char secondRandChar = (char) ('A' + rand.nextInt(26));
		String randNum = String.format("%04d", rand.nextInt(10000));
		
		String result = "" + firstRandChar + secondRandChar + "-" + randNum;
		
		return result;
	}

	
	
	public void insertDataKaryawan() { 
		String kodeKaryawan;
			while (true) {
				kodeKaryawan = kodeKar();
				if (checkUnique(kodeKaryawan) == true) {
					break;
				}
			}		
			
		String namaKaryawan = "";
		while (true) {
			System.out.print("Input nama karyawan [>=3] : ");
			namaKaryawan = sc.nextLine();
			if (namaKaryawan.length() >=3) {
				break;
			}
		}	
		
		String jenisKelamin = "";
		while (true) {
			System.out.print("Input jenis kelamin [Laki-Laki | Perempuan] : ");
			jenisKelamin = sc.nextLine();
			if (jenisKelamin.equals("Laki-Laki") || jenisKelamin.equals("Perempuan")) {
				break;
			}
		}
		
		String jabatan = "";
		int gajiKaryawan = 0;
		while (true) {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] : ");
			jabatan = sc.nextLine();
			if (jabatan.equals("Manager")) {
				gajiKaryawan = 8000000;
				break;
			}
			else if (jabatan.equals("Supervisor")) {
				gajiKaryawan = 6000000;
				break;
			}
			else if (jabatan.equals("Admin")) {
				gajiKaryawan = 4000000;
				break;
			}
			
		}
		
		
		
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + kodeKaryawan);
		
		applyBonus(jabatan, gajiKaryawan);
		
		
		System.out.println("ENTER to return");
		sc.nextLine();
			
		
		DataKaryawan dataKaryawan = new DataKaryawan(kodeKaryawan, namaKaryawan, jenisKelamin, jabatan, gajiKaryawan);
		arrData.add(dataKaryawan);		
	}
	
	public void applyBonus (String jabatan, int gajiKaryawan) {
		double newGaji = 0;
		String bonus = "";
		if (jabatan.equals("Manager")) {
			bonus = "10%";
			newGaji = gajiKaryawan * 1.10;
		}else if (jabatan.equals("Supervisor")) {
			bonus = "7,5%";
			newGaji = gajiKaryawan * 1.075;
		}else if (jabatan.equals("Admin")) {
			bonus = "5%";
			newGaji = gajiKaryawan * 1.05;
		}
		
		int count = 0;
		for (DataKaryawan bonuskaryawan : arrData) {
			if (bonuskaryawan.jabatan.equals(jabatan)) {
				count++;
			}
		}
		for (DataKaryawan bonuskaryawan : arrData) {
			if (bonuskaryawan.jabatan.equals(jabatan)) {
				if (count % 3 == 0) {
				bonuskaryawan.gajiKaryawan = (int) newGaji;
				System.out.println("Bonus sebesar " + bonus + " telah diberikan kepada karyawan dengan id " + bonuskaryawan.kodeKaryawan);
				}
			}
		}
		
	}
	
	
	public void bubblesort() {
		for (int i = 0; i < arrData.size(); i++) {
			for (int j = 0; j < arrData.size() - 1; j++) {
				
				String stName = arrData.get(j).namaKaryawan;
				String ndName = arrData.get(j + 1).namaKaryawan;
				
				if (stName.compareTo(ndName) > 0) {
					DataKaryawan temp = arrData.get(j);
					arrData.set(j, arrData.get(j+1));
					arrData.set((j+1), temp);
				}
			}
		}
	}
	
	void data () {
		System.out.println("==========================================================================================");
		System.out.printf("| %-2s | %-8s | %-18s | %-5s | %-10s | %-5s | \n", "No", "Kode Karyawan", "Nama Karyawan", "Jenis Kelamin", " Jabatan", " Gaji Karyawan");
		System.out.println("==========================================================================================");
		for (int i = 0; i < arrData.size(); i++) {
			DataKaryawan dataKaryawan = arrData.get(i);
			System.out.printf("| %-2s | %-13s | %-18s | %-13s | %-10s | %-13s | \n", (i+1), dataKaryawan.kodeKaryawan, dataKaryawan.namaKaryawan, dataKaryawan.jenisKelamin, dataKaryawan.jabatan, dataKaryawan.gajiKaryawan);
		}
		System.out.println("==========================================================================================");
	}
	
	void seeder () {
		bubblesort();
		arrData.add(new DataKaryawan("AA-5512", "Aaron Sebastian", "Laki-Laki", "Supervisor", 6000000));
		arrData.add(new DataKaryawan("GJ-1901", "Alicia Gabrielle", "Perempuan", "Supervisor", 6450000));
		arrData.add(new DataKaryawan("AL-0991", "Calvin Nicholas", "Laki-Laki", "Supervisor", 6450000));
		arrData.add(new DataKaryawan("ZZ-1123", "Felix Jonathan", "Laki-Laki", "Manager", 8000000));
		arrData.add(new DataKaryawan("JO-9912", "Herman Kuding", "Laki-Laki", "Supervisor", 6450000));
		
	}
	
	public void viewDataKaryawan() {
		while (true) {
			if (arrData.isEmpty()) {
				System.out.println("There is no data");
				System.out.println("Press Enter To Continue...");
				sc.nextLine();
				break;
			}else {
				bubblesort();
				data();
				break;
			}
		}
	}
	
	public void updateDataKaryawan() {
		while (true) {
			if (arrData.isEmpty()) {
				System.out.println("There is no data");
				System.out.println("Press Enter To Continue...");
				sc.nextLine();
				break;
			}else {
				bubblesort();
				data();
				while (true) {
					System.out.print("Input nomor urutan karyawan yang ingin diupdate : ");
					int chooseUpdate = sc.nextInt();
					sc.nextLine();
					
					String newNama = "";
					String newJenisKelamin = "";
					String newJabatan = "";
					
					if (chooseUpdate > 0 && chooseUpdate <= arrData.size()) {
						DataKaryawan dataKaryawan = arrData.get(chooseUpdate - 1);
						
						while (true) {
							System.out.print("Input nama karyawan [>=3] : ");
							newNama = sc.nextLine();
							
							if (newNama.length() >=3) {
								break;
							}else if (newNama.equals("0")) {
								newNama = arrData.get(chooseUpdate - 1).namaKaryawan;
								break;
							}
						}
						
						while (true) {
							System.out.print("Input jenis kelamin [Laki-Laki | Perempuan] : ");
							newJenisKelamin = sc.nextLine();
							if (newJenisKelamin.equals("Laki-Laki") || newJenisKelamin.equals("Perempuan")) {
								break;
							}else if (newJenisKelamin.equals("0")) {
								newJenisKelamin = arrData.get(chooseUpdate - 1).jenisKelamin;
								break;
							}
						}
						
						while (true) {
							System.out.print("Input jabatan [Manager | Supervisor | Admin] : ");
							newJabatan = sc.nextLine();
							if (newJabatan.equals("Manager") || newJabatan.equals("Supervisor") || newJabatan.equals("Admin")) {
								break;
							}else if (newJabatan.equals("0")) {
								newJabatan = arrData.get(chooseUpdate - 1).jabatan;
								break;
							}
						}
						
						DataKaryawan updateData = new DataKaryawan(dataKaryawan.kodeKaryawan, newNama, newJenisKelamin, newJabatan, dataKaryawan.gajiKaryawan);
						arrData.set(chooseUpdate - 1, updateData);
						
						System.out.println("Berhasil update karyawan dengan id " + arrData.get(chooseUpdate - 1).kodeKaryawan);
						
						System.out.println("ENTER to return");
						sc.nextLine();
						
						break;
						
					}else if (chooseUpdate == 0) {
						System.out.println("Update data karyawan dibatalkan");
						break;
					}
				}
				break;
			}	
		}
	}
	
	public void deleteDataKaryawan() {
		while (true) {
			if (arrData.isEmpty()) {
				System.out.println("There is no data");
				System.out.println("Press Enter To Continue...");
				sc.nextLine();
				break;
			}else {
				bubblesort();
				data();
				while (true) {
					System.out.print("Input nomor urutan karyawan yang ingin dihapus : ");
					int chooseDelete = sc.nextInt();
					sc.nextLine();
					
					if (chooseDelete > 0 && chooseDelete <= arrData.size()) {
						System.out.println("Karyawan dengan kode " + arrData.get(chooseDelete -1).kodeKaryawan + " berhasil dihapus");
						arrData.remove(chooseDelete -1);
						
						System.out.println("ENTER to return");
						sc.nextLine();
						break;
					}		
				}
			break;
			}
		}
	}
	
	public void printMenu() {
		System.out.println("================");
		System.out.println("PT ChipiChapa");
		System.out.println("================");
		System.out.println("1. Insert Data Karyawan");
		System.out.println("2. View Data Karyawan");
		System.out.println("3. Update Data Karyawan");
		System.out.println("4. Delete Data Karyawan");
		System.out.print(">>");
	}
	
	public Main() {
		seeder();
		while (true) {
			printMenu();
			int choose = sc.nextInt();
			sc.nextLine();
			
			if (choose == 1 ) {
				insertDataKaryawan();
			}else if (choose == 2) {
				viewDataKaryawan();
			}else if (choose == 3) {
				updateDataKaryawan();
			}else if (choose == 4) {
				deleteDataKaryawan();
			}
		}
	}
	
	public static void main(String[] args) {
		new Main();
		
		
	}

}
