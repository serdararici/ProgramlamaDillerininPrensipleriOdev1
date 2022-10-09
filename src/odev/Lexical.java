/**
*
* Serdar Ar�c� - serdar.arici1@ogr.sakarya.edu.tr
* 29.03.2022
* <p>
* Bu s�n�fta istenilen verileri bulmaya yarayan regular expression ifadeleri olu�turuldu ve 
* istenilen verileri d�ng� kullan�larak saymaya yarayan fonksiyonlar olu�turuldu.
* </p>
*/

package odev;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexical {
	// programda kullan�lan sayaclar
	int tekliOperator = 0;
    int ikiliOperator = 0;
    int sayisalOperator = 0; // + , ++ , - , -- , * , / , % , & , | , ^ , = , += , -= , /= , *= , %= , &= , |= , ^=
    int sayisalOperatorKontrol = 0;
    int iliskiselOperator = 0; // < , <= , > , >= , == , !=
    int mantiksalOperator = 0; // && , || , !
    int mantiksalOperatorKontrol = 0; // !=
    int toplamOperand = 0;
	
    // programda kullan�lacak regEx kodlar�
    String sayisalRegex = "(\\+)|(\\+{2})|(\\-)|(\\-{2})|(\\*)|(\\/)|(\\%)|(\\&)|(\\|)|(\\^)|(\\=)|(\\+\\=)|(\\-\\=)|(\\/\\=)|(\\*\\=)|(\\%\\=)|(\\&\\=)|(\\|\\=)|(\\^=)";
    String sayisalRegexKontrol = "(\\&{2})|(\\={2})|(\\|{2})|(!=)|(<=)|(>=)";
    String tekliRegex = "(\\+{2})|(\\-{2})";
    String iliskiselRegex = "(\\<)|(\\<\\=)|(\\>)|(\\>\\=)|(\\={2})|(\\!\\=)"; 
    String mantiksalregex = "((\\|{2})|(!)|(\\&{2}))";
    String mantiksalRegexKontrol = "(!=)";
    
 // say�sal operatorleri bulmaya yaran ve her buldu�unda say�y� bir arrt�ran fonksiyon
    public int sayisal(String satir) {
        // + , ++ , - , -- , * , / , % , & , | , ^ , = , += , -= , /= , *= , %= , &= , |= , ^=
        Pattern pattern = Pattern.compile(sayisalRegex);  // "(\\+)|[+]{2}|(-)|[-]{2}|(\\*)|(/)|(%)|(&)|(|)|(/^)|(=)|(\\+=)|(-=)|(/=)|(\\*=)|(%=)|(&=)|(|=)|(^=)"
        Matcher matcher = pattern.matcher(satir);

        while (matcher.find()) {
                sayisalOperator++;
        }

        sayisalOperator -= sayisalOperatorKontrol;
        return sayisalOperator;

    }
    
    // say�sal operator say�s�n�n yanl�� olmamas� i�in baz� kontrollerin yap�ld��� fonksiyon
    public int sayisalKontrol(String satir){
        Pattern pattern = Pattern.compile(sayisalRegexKontrol);
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
            sayisalOperatorKontrol++;
        }
        return sayisalOperatorKontrol;
    }

    // tekli operatorleri bulmaya yaran ve her buldu�unda say�y� bir arrt�ran fonksiyon
    public int tekli(String satir){
        Pattern pattern = Pattern.compile(tekliRegex);
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
                tekliOperator++;
        }
        return tekliOperator;
    }
    
 // ikili operator say�s�n� bulmaya yarayan fonksiyon
    public int ikili(){
        ikiliOperator = sayisalOperator-tekliOperator;
        return ikiliOperator;
    }
    
 // iliskisel operatorleri bulmaya yaran ve her buldu�unda say�y� bir arrt�ran fonksiyon
    public int iliskisel(String satir){   // < , <= , > , >= , == , !=
        Pattern pattern = Pattern.compile(iliskiselRegex);   
        Matcher matcher = pattern.matcher(satir);

            while (matcher.find()) {
                iliskiselOperator++;
            }
        return iliskiselOperator;
    }
    
 // mant�ksal operatorleri bulmaya yaran ve her buldu�unda say�y� bir arrt�ran fonksiyon
    public int mantiksal(String satir){
        Pattern pattern = Pattern.compile(mantiksalregex);
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
            mantiksalOperator++;
        }
        mantiksalOperator -= mantiksalOperatorKontrol;
        return mantiksalOperator;
    }

    // mant�ksal operator say�s�n�n yanl�� olmamas� i�in baz� kontrollerin yap�ld��� fonksiyon
    public int mantiksalKontrol(String satir){
        Pattern pattern = Pattern.compile(mantiksalRegexKontrol); 
        Matcher matcher = pattern.matcher(satir);
        while (matcher.find()) {
            mantiksalOperatorKontrol++;
        }
        return mantiksalOperatorKontrol;
    }

    // operand say�s�n� bulmaya yarayan fonksiyon
    public int operand(String satir){
        toplamOperand = ikiliOperator*2 + tekliOperator + iliskiselOperator*2 + mantiksalOperator*2;
        return  toplamOperand;
    }
	
}
	