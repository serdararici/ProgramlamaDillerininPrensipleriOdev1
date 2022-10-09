/**
*
* Serdar Arýcý - serdar.arici1@ogr.sakarya.edu.tr
* 29.03.2022
* <p>
* Bu sýnýfta komut satýrýndan verilecek olan dosya scanner sýnýfý ile okunuyor.
* Yorum satýrýndaki verilein sayýlmasý istenmediði için yorum satýrlarý siliniyor.
* Lexical sýnýfýndaki fonksiyonlar kullanýlarak sonuçlar ekrana yazdýrýlýyor.
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
		
		//istenen verilerin sayýlarýný tutmak için sayaçlarý oluþturdum.
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
			Scanner reader = new Scanner(new File("../src/"+args[0]));  // komut satýrýndan verilecek dosyayý okur.
			Lexical lexical = new Lexical();  //Lexical sýnýfýndan nesne oluþturuluyor.
			
			while(reader.hasNext())
            {
                String satir = reader.nextLine();  //dosya satýr satýr okunuyor.
                String dizi[]= satir.split("//");  // "//" ile yapýlan yorum satýrlarýný engellemek için
                yazi+=dizi[0];
                yazi=yazi.replaceAll("((?<=\\/\\*)(.*?)(?=\\*\\/))","");    // "/*   */" ile yapýlan yorum satýrlarýný engellemek için
                yazi = yazi.replaceAll("(\\/\\*\\*\\/)","");
            }
            tekliOperator = lexical.tekli(yazi);
            sayisalOperatorKontrol = lexical.sayisalKontrol(yazi); // sayýsal operator sayýsýný bozan operatorleri kontrol amaçlý
            sayisalOperator = lexical.sayisal(yazi);
            ikiliOperator = lexical.ikili();
            iliskiselOperator = lexical.iliskisel(yazi);
            mantiksalOperatorKontrol = lexical.mantiksalKontrol(yazi);  // mantýksal operator sayýsýný bozan operatorleri kontrol amaçlý
            mantiksalOperator = lexical.mantiksal(yazi);
            toplamOperand = lexical.operand(yazi);
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}
		
		// elde edilen bilgiler ekrana yazdýrýlýyor
		System.out.println("Operator Bilgisi: ");
        System.out.println("         Tekli Operatör Sayýsý: " + tekliOperator);
        System.out.println("         Ýkili Operatör Sayýsý: " + ikiliOperator);
        System.out.println("         Sayýsal Operatör Sayýsý: " + sayisalOperator);
        System.out.println("         Ýliþkisel Operatör Sayýsý: " + iliskiselOperator);
        System.out.println("         Mantýksal Operatör Sayýsý: " + mantiksalOperator);
        System.out.println("Operand Bilgisi: ");
        System.out.println("        Toplam Operand Sayýsý: " + toplamOperand);
	}
}

