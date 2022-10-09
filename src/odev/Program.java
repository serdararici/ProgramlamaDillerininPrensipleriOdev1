/**
*
* Serdar Ar�c� - serdar.arici1@ogr.sakarya.edu.tr
* 29.03.2022
* <p>
* Bu s�n�fta komut sat�r�ndan verilecek olan dosya scanner s�n�f� ile okunuyor.
* Yorum sat�r�ndaki verilein say�lmas� istenmedi�i i�in yorum sat�rlar� siliniyor.
* Lexical s�n�f�ndaki fonksiyonlar kullan�larak sonu�lar ekrana yazd�r�l�yor.
* </p>
*/


package odev;

import odev.Lexical;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//istenen verilerin say�lar�n� tutmak i�in saya�lar� olu�turdum.
		int tekliOperator = 0;
        int ikiliOperator = 0;
        int sayisalOperator = 0; // + , ++ , - , -- , * , / , % , & , | , ^ , = , += , -= , /= , *= , %= , &= , |= , ^=
        int sayisalOperatorKontrol = 0;
        int iliskiselOperator = 0; // < , <= , > , >= , == , !=
        int mantiksalOperator = 0; // && , || , !
        int mantiksalOperatorKontrol = 0; //!=
        int toplamOperand = 0;
		
        String yazi=""; // Dosyanan okunacak olan veriyi tutacak olan string
		
		try {
			Scanner reader = new Scanner(new File("../src/"+args[0]));  // komut sat�r�ndan verilecek dosyay� okur.
			Lexical lexical = new Lexical();  //Lexical s�n�f�ndan nesne olu�turuluyor.
			
			while(reader.hasNext())
            {
                String satir = reader.nextLine();  //dosya sat�r sat�r okunuyor.
                String dizi[]= satir.split("//");  // "//" ile yap�lan yorum sat�rlar�n� engellemek i�in
                yazi+=dizi[0];
                yazi=yazi.replaceAll("((?<=\\/\\*)(.*?)(?=\\*\\/))","");    // "/*   */" ile yap�lan yorum sat�rlar�n� engellemek i�in
                yazi = yazi.replaceAll("(\\/\\*\\*\\/)","");
            }
            tekliOperator = lexical.tekli(yazi);
            sayisalOperatorKontrol = lexical.sayisalKontrol(yazi); // say�sal operator say�s�n� bozan operatorleri kontrol ama�l�
            sayisalOperator = lexical.sayisal(yazi);
            ikiliOperator = lexical.ikili();
            iliskiselOperator = lexical.iliskisel(yazi);
            mantiksalOperatorKontrol = lexical.mantiksalKontrol(yazi);  // mant�ksal operator say�s�n� bozan operatorleri kontrol ama�l�
            mantiksalOperator = lexical.mantiksal(yazi);
            toplamOperand = lexical.operand(yazi);
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}
		
		// elde edilen bilgiler ekrana yazd�r�l�yor
		System.out.println("Operator Bilgisi: ");
        System.out.println("         Tekli Operat�r Say�s�: " + tekliOperator);
        System.out.println("         �kili Operat�r Say�s�: " + ikiliOperator);
        System.out.println("         Say�sal Operat�r Say�s�: " + sayisalOperator);
        System.out.println("         �li�kisel Operat�r Say�s�: " + iliskiselOperator);
        System.out.println("         Mant�ksal Operat�r Say�s�: " + mantiksalOperator);
        System.out.println("Operand Bilgisi: ");
        System.out.println("        Toplam Operand Say�s�: " + toplamOperand);
	}
}

